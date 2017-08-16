/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.user.service;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.user.UserMgr;
import org.helianto.user.domain.*;
import org.helianto.user.domain.enums.EventType;
import org.helianto.user.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Default <code>UserMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("userMgr")
public class UserMgrImpl 
	implements UserMgr {
    
	@Transactional(readOnly=true)
	public UserGroup findRootGroup(Entity entity) {
        return userGroupRepository.findByEntityAndUserKey(entity, "USER");
	}

	@Transactional(readOnly=true)
	public UserGroup findAdminGroup(Entity entity) {
        return userGroupRepository.findByEntityAndUserKey(entity, "ADMIN");
	}

    /**
	 * Recurse into parent user groups to create a complete userRole List.
	 */
	protected Set<UserRole> recurseUserRoles(Set<UserRole> roles, Set<UserAssociation> parentAssociations) {
		Set<UserRole> localRoles = new HashSet<UserRole>(roles);
		logger.debug("Found {} local role(s).", localRoles.size());
		for (UserAssociation parentAssociation: parentAssociations) {
			UserGroup parent = parentAssociation.getParent();
			localRoles.addAll(recurseUserRoles(parent.getRoles(), parent.getParentAssociations()));
		}
		return localRoles;
	}

	/**
	 * This implementation is able to detect if the owning entity has changed its nature and
	 * will create a public entity accordingly.
	 */
	@Transactional
	public UserGroup storeUserGroup(UserGroup userGroup) {
    	userGroupRepository.saveAndFlush(userGroup);
//    	publicEntityMgr.installPublicEntity(userGroup.getEntity());
        return userGroup;
    }
    
    /**
     * Assure each user have a valid identity.
     * 
     * <p>Validation is ready to detect identity (with principal 
     * equals user key) not found.</p>
     * <p>If the create flag is set, a candidate principal may be used
     * to create a new identity.</p>
     * 
     * @param user
     * @deprecated
     */
    protected Identity validateIdentity(User user) {
    	if (user.getIdentity().getId()==0) {
    		logger.debug("Looking for a candidate identity");
    		String principal = user.getUserKey();
    		logger.debug("Using principal {}", principal);
    		Identity identity = identityRepository.findByPrincipal(principal);
//    		if (identity!=null) {
//    			logger.debug("Identity found: {}", identity);
//    			user.setIdentity(identity);
//    		}
//    		else if (user.getCreateIdentity()==CreateIdentity.AUTO.getValue()) {
//        		identity = Identity.identityFactory(principal);
//        		logger.debug("Identity created: {}", identity);
//        		Credential credential = Credential.credentialFactory(identity, "default");
//        		identity.getCredentials().add(credential);
//        		logger.debug("Credential created: {}", credential);
//    			user.setIdentity(identity);
//    		}
//    		else {
//    			logger.debug("Unable to validate identity, identity not found and not created.");			
//    		}
    		return identity;
    	}
		logger.debug("User identified with {}", user.getIdentity());
		return user.getIdentity();
    }

	@Transactional(readOnly=true)
	public List<? extends UserGroup> findUsers(String userKey) {
		List<UserGroup> userList = userGroupRepository.findByUserKeyOrderByLastEventDesc(userKey);
    	logger.debug("Found user list of size {}.", userList.size());
        return userList;
	}

	@Transactional(readOnly=true)
	public List<User> findUsers(String parent, String userKey) {
		List<User> userList = userRepository.findByParentAndPrincipal(parent, userKey, new Sort(new Order(Direction.DESC, "lastEvent")));
    	logger.debug("Found user list of size {} from parent {}.", userList.size(), parent);
        return userList;
	}

	@Transactional(readOnly=true)
	public List<User> findUsers(String parent, String userKey, char entityType) {
		List<User> userList = userRepository.findByParentAndPrincipalAndEntityType(parent, userKey, entityType, new Sort(new Order(Direction.DESC, "lastEvent")));
    	logger.debug("Found user list of size {} from parent {}.", userList.size(), parent);
        return userList;
	}

	@Transactional(readOnly=true)
	public List<UserAssociation> findUserAssociations(UserGroup parent) {
		List<UserAssociation> userAssociationList = userAssociationRepository.findByParent(parent, new Sort(new Order(Direction.ASC, "child.userKey")));
    	logger.debug("Found user association list of size {} from parent {}.", userAssociationList.size(), parent);
        return userAssociationList;
	}

	@Transactional
    public UserAssociation storeUserAssociation(UserAssociation parentAssociation) {
    	return userAssociationRepository.saveAndFlush(parentAssociation);
    }
    
    /**
     * Limited (so far) to one level.
     * 
     * @param userGroup
     */
	@Transactional
    public List<UserGroup> findParentChain(UserGroup userGroup) {
//		userGroupRepository.refresh(userGroup);
//    	List<UserGroup> parentList = new ArrayList<UserGroup>();
////    	parentList.add(userGroup);
//    	if(userGroup.getParentAssociations()!=null) {
//    		for (UserAssociation association: userGroup.getParentAssociations()) {
//    			if (association.getParent()!=null) {
//        			parentList.add(association.getParent());
//            		logger.debug("{} is child of {}.", userGroup,  association.getParent());
//    			}
//    		}
//    	}
//    	else {
//    		logger.warn("Parent list for {} is null.", userGroup);
//    	}
    	return userGroupRepository.findParentsByChild(userGroup);
    }

	@Transactional
	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall) {

		logger.debug("Check user (group) {} installation with 'reinstall={}'", userGroupName, reinstall);
		UserGroup userGroup = null;
		if (!reinstall) {
			userGroup = userRepository.findByEntityAndUserKey(defaultEntity, userGroupName);
		}
		if (userGroup==null) {
			logger.debug("Will install user (group) {} ...", userGroupName);
			userGroup = userGroupRepository.save(new UserGroup(defaultEntity, userGroupName));
		}
		logger.debug("UserGroup AVAILABLE as {}.", userGroup);
		userRepository.flush();
		return userGroup;
	}
	
	@Transactional
	public UserAssociation installUser(UserGroup parent, Identity identity, boolean accountNonExpired) {

		logger.info("Check user installation with 'principal={}' as member of {}.", identity.getPrincipal(), parent);

		User user = (User) userRepository.findByEntityAndUserKey(parent.getEntity(), identity.getPrincipal());
		if (user==null) {
			user = (User) userRepository.save(new User(parent.getEntity(), identity));
			logger.debug("New user created as {}.", user);
		}
		
		UserAssociation association = userAssociationRepository.findByParentAndChild(parent, user);
		if(association==null) {
			association = userAssociationRepository.saveAndFlush(new UserAssociation(parent, user));
			logger.info("New association for user group {} and {}.", parent, user);
		}
		
		return association;
		
	}
	
//	/**
//	 * @deprecated
//	 */
//	@Transactional
//	public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired) {
//
//		logger.debug("Check user installation with 'principal={}' as member of {}.", credential.getPrincipal(), parent);
//		User user = (User) userRepository.findByEntityAndUserKey(parent.getEntity(), credential.getPrincipal());
//		if (user==null) {
//			user = (User) userRepository.save(new User(parent.getEntity(),credential.getIdentity()));
//			logger.debug("New user created asf {}.", user);
//		}
//
//		user.setAccountNonExpired(true);
//		userRepository.flush();
//		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");
//
//
//		logger.info("User AVAILABLE as {}.", user);
//
//		UserAssociation association = userAssociationRepository.findByParentAndChild(parent, user);
//		if(association==null) {
//			logger.info("Will install user association for user group {} and {}.", parent, user);
//			association = userAssociationRepository.saveAndFlush(new UserAssociation(parent, user));
//		}
//		logger.info("User {} available as part of association {}.", user, association);
//		return association;
//	}
	
	public List<UserGroup> findUsersByRole(Entity entity, String serviceName, String extension) {
		return userRoleRepository.findChildrenByEntityAndUserRoleServiceName(entity, serviceName, extension);
	}
	
	@Transactional
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension) {
		
		UserRole userRole = userRoleRepository.findByUserGroupAndServiceAndServiceExtension(userGroup, service, extension);
		if (userRole==null) {
			logger.debug("Will install required user role {} for user group {} ...", userRole, userGroup);
			userRole = userRoleRepository.saveAndFlush(new UserRole(userGroup, service, extension));
		}
		logger.debug("User role AVAILABLE as {}.", userRole);
		return userRole;
	}
	
	@Transactional
    public UserLog storeUserLog(User user, Date date) {
        if (user.getIdentity()==null) {
            throw new IllegalArgumentException("Must have an identity");
        }
        if (date==null) {
            date = new Date();
        }
        UserLog userLog = new UserLog(user, date, EventType.LOGIN_ATTEMPT);
        return userLogRepository.saveAndFlush(userLog);
    }
    
    /**
     * Helper method to convert principal to lower case.
     */
    static String convertToLowerCase(Locale locale, String principal) {
        if ((principal != null && principal.length() > 0)) {
            return principal.toLowerCase(locale);
        }
        throw new RuntimeException("Principal should not be null or empty.");
    }
    
	@Transactional
	public UserRole storeUserRole(UserRole userRole) {
		return userRoleRepository.saveAndFlush(userRole);
	}
	
	@Transactional
	public void removeUserRole(UserRole userRole, UserGroup userGroup) {
		if (userGroup!=null ) {
			UserRole[] rr = (UserRole[]) userGroup.getRoles().toArray();
			for (UserRole r: rr) {
				System.out.println("User role: "+r);
				if (r.getUserGroup().equals(userGroup)) {
					userRoleRepository.delete(userRole);
					userGroup.getRoles().remove(userRole);
					System.out.println("= "+userRole);
				}
				else {
					System.out.println("!= "+userRole);
				}
			}
		}
		throw new IllegalArgumentException("User role " +
				userRole+"does not belong to user group " + userGroup+".");
	}

    //- collaborators
    
    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;
    private UserAssociationRepository userAssociationRepository;
    private UserRoleRepository userRoleRepository;
    private UserLogRepository userLogRepository;
	private IdentityRepository identityRepository;
	

    @Resource
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    @Resource
    public void setUserGroupRepository(UserGroupRepository userGroupRepository) {
		this.userGroupRepository = userGroupRepository;
	}

    @Resource
	public void setUserAssociationRepository(UserAssociationRepository userAssociationRepository) {
		this.userAssociationRepository = userAssociationRepository;
	}

	@javax.annotation.Resource
	public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}
	
    @Resource
    public void setUserLogRepository(UserLogRepository userLogRepository) {
		this.userLogRepository = userLogRepository;
	}
    
	@Resource
	public void setIdentityRepository(IdentityRepository identityRepository) {
		this.identityRepository = identityRepository;
	}
    private static final Logger logger = LoggerFactory.getLogger(UserMgrImpl.class);


}
