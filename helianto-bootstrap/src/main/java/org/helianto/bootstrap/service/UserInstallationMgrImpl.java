package org.helianto.bootstrap.service;

import org.helianto.bootstrap.CoreInstallationMgr;
import org.helianto.bootstrap.UserInstallationMgr;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Service;
import org.helianto.core.repository.EntityRepository;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserAssociationRepository;
import org.helianto.user.repository.UserRepository;
import org.helianto.user.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 *
 */
public class UserInstallationMgrImpl 
	implements UserInstallationMgr {
	
	@Autowired
    private EntityRepository entityRepository;

	@Autowired
    private UserRepository userRepository;

	@Autowired
	private UserAssociationRepository userAssociationRepository;
    
	@Autowired
	private UserRoleRepository userRoleRepository;
    
	@Autowired
    private CoreInstallationMgr installationMgr;


	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall) {

		defaultEntity = entityRepository.save(defaultEntity);
		
		logger.debug("Check user (group) {} installation with 'reinstall={}'", userGroupName, reinstall);
		UserGroup userGroup = null;
		if (!reinstall) {
			userGroup = userRepository.findByEntityAndUserKey(defaultEntity, userGroupName);
		}
		if (userGroup==null) {
			logger.debug("Will install user (group) {} ...", userGroupName);
			userGroup = new UserGroup(defaultEntity, userGroupName);
			userRepository.save(userGroup);
		}
		logger.debug("UserGroup AVAILABLE as {}.", userGroup);
		return userGroup;
	}
	
	public UserAssociation installUser(UserGroup parent, String principal, boolean accountNonExpired) {
		
		logger.info("Check user installation with 'principal={}' as member of {}.", principal, parent);
		User user = (User) userRepository.findByEntityAndUserKey(parent.getEntity(), principal);
		if (user==null) {
			Credential credential = installationMgr.installIdentity(principal);
			user = new User(parent.getEntity(), credential);
		}
		
		user.setAccountNonExpired(true);
		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");

		userRepository.save(user);
		logger.info("User AVAILABLE as {}.", user);
		
		UserAssociation association = userAssociationRepository.findByParentAndChild(parent, user);
		if(association==null) {
			logger.info("Will install user association for user group {} and {}.", parent, user);
			association = new UserAssociation(parent, user);
			userAssociationRepository.save(association);
		}
		logger.info("User {} available as part of association {}.", user, association);
		return association;
	}
	
	public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired) {
		
		logger.info("Check user installation with 'principal={}' as member of {}.", credential.getPrincipal(), parent);
		User user = (User) userRepository.findByEntityAndUserKey(parent.getEntity(), credential.getPrincipal());
		if (user==null) {
			user = new User(parent.getEntity(), credential);
		}
		
		user.setAccountNonExpired(true);
		logger.warn("User {} set to {} expired.", user, accountNonExpired ? "non" : "");

		userRepository.save(user);
		logger.info("User AVAILABLE as {}.", user);
		
		UserAssociation association = userAssociationRepository.findByParentAndChild(parent, user);
		if(association==null) {
			logger.info("Will install user association for user group {} and {}.", parent, user);
			association = new UserAssociation(parent, user);
			userAssociationRepository.save(association);
		}
		logger.info("User {} available as part of association {}.", user, association);
		return association;
	}
	
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension) {
		
		UserRole userRole = userRoleRepository.findByUserGroupAndServiceAndServiceExtension(userGroup, service, extension);
		if (userRole==null) {
			userRole = new UserRole(userGroup, service, extension);
			logger.debug("Will install required user role {} for user group {} ...", userRole, userGroup);
			userRoleRepository.save(userRole);
		}
		logger.debug("User role AVAILABLE as {}.", userRole);
		return userRole;
	}
	
    private final Logger logger = LoggerFactory.getLogger(UserInstallationMgrImpl.class);

}
