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

package org.helianto.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.security.UserDetailsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Default implementation for <code>SecurityMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("securityMgr")
public class SecurityMgrImpl implements SecurityMgr {
    
	public Credential findCredentialByIdentity(Identity identity) {
		return credentialDao.findUnique(identity);
	}

	public Credential findCredentialByPrincipal(String principal) {
		Identity identity = identityDao.findUnique(principal);
		logger.debug("Found {}", identity);
		return credentialDao.findUnique(identity);
	}

	public Credential storeCredential(Credential credential) throws PasswordNotVerifiedException {
		if (credential.isPasswordVerified()) {
	        credentialDao.saveOrUpdate(credential);
	        return credential;
		}
		throw new PasswordNotVerifiedException();
	}

	protected Collection<UserRole> loadAndValidateRoles(User user) {
    	// role list
		List<UserRole> roleList = new ArrayList<UserRole>(recurseUserRoles(user.getRoles(), user.getParentAssociations()));
		user.setRoleList(roleList);
		return user.getRoleList();
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

	public Set<UserRole> findRoles(UserGroup userGroup, boolean recursively) {
		userGroupDao.saveOrUpdate(userGroup);
		Set<UserRole> roles = new HashSet<UserRole>(userGroup.getRoles());
		if (recursively) {
			Set<UserAssociation> parents = userGroup.getParentAssociations();
			for (UserAssociation parentAssociation: parents) {
				UserGroup parent = parentAssociation.getParent();
				if (parent!=null) {
//					userGroupDao.saveOrUpdate(parent);
					// TODO SORRY, for the moment only one level recursion available...
					roles.addAll(parent.getRoles());
				}
			}
		}
		logger.debug("Found {} role(s) FOR {}.", roles.size(), userGroup);
		return roles;
	}
	
//	public List<UserRole> findRoles(Filter filter, boolean recursively) {
//		List<UserRole> userRoleList = (List<UserRole>) userRoleDao.find(filter);
//		if (recursively) {
//			UserGroup userGroup = ((UserRoleFilterAdapter) filter).getForm().getUserGroup();
//			if (userGroup==null) {
//				throw new IllegalArgumentException("UserGroup required.");
//			}
//			userGroupDao.saveOrUpdate(userGroup);
//			Set<UserAssociation> parents = userGroup.getParentAssociations();
//			for (UserAssociation parentAssociation: parents) {
//				UserGroup parent = parentAssociation.getParent();
//				if (parent!=null) {
////					userGroupDao.saveOrUpdate(parent);
//					// TODO SORRY, for the moment only one level recursion available...
//					UserRoleFilterAdapter parentFilter = (UserRoleFilterAdapter) ((UserRoleFilterAdapter) filter).clone();
//					parentFilter.getForm
//					List<UserRole> userParentRoleList = (List<UserRole>) userRoleDao.find(filter);
//					roles.addAll(parent.getRoles());
//				}
//			}
//		}
//		logger.debug("Found {} role(s) FOR {}.", roles.size(), userGroup);
//		return roles;
//	}
	
	public void authenticate(User user, Set<UserRole> roles) {
		authenticate(new UserDetailsAdapter(user, null, roles));
	}
	
	public void authenticate(UserDetailsAdapter userDetails) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "any", userDetails.getAuthorities());   //   PreAuthenticatedAuthenticationToken(aPrincipal, aCredentials, anAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.debug("Authenticated {}.", authentication);
	}
	
	public void clearAuthentication() {
		SecurityContextHolder.clearContext();
	}
    
    // collabs

    private FilterDao<Identity> identityDao;
    private FilterDao<UserGroup> userGroupDao;
    private FilterDao<Credential> credentialDao;

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity> identityDao) {
        this.identityDao = identityDao;
    }
    
    @Resource(name="userGroupDao")
    public void setUserGroupDao(FilterDao<UserGroup> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

    @Resource(name="credentialDao")
    public void setCredentialDao(FilterDao<Credential> credentialDao) {
        this.credentialDao = credentialDao;
    }

    private final static Logger logger = LoggerFactory.getLogger(SecurityMgrImpl.class);

}
