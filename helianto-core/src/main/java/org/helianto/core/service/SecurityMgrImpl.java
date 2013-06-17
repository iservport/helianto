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

import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.SecurityMgr;
import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.filter.IdentitySecurityFilterAdapter;
import org.helianto.core.form.IdentitySecurityForm;
import org.helianto.core.repository.IdentitySecurityRepository;
import org.helianto.core.repository.CredentialRepository;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;
import org.helianto.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation for <code>SecurityMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("securityMgr")
public class SecurityMgrImpl implements SecurityMgr {
    
	@Transactional(readOnly=true)
	public Credential findCredentialByIdentity(Identity identity) {
		return credentialRepository.findByIdentity(identity);
	}

	@Transactional(readOnly=true)
	public Credential findCredentialByPrincipal(String principal) {
		Identity identity = identityRepository.findByPrincipal(principal);
		logger.debug("Found {}", identity);
		return credentialRepository.findByIdentity(identity);
	}
	
	@Transactional(readOnly=true)
	public Credential loadCredential(Credential credential) {
		return credentialRepository.findOne(credential.getId());
	}

	@Transactional(readOnly=false)
	public Credential storeCredential(Credential credential) throws PasswordNotVerifiedException {
		if (credential.isPasswordVerified()) {
	        return credentialRepository.saveAndFlush(credential);
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

	@Transactional(readOnly=true)
	public Set<UserRole> findRoles(UserGroup userGroup, boolean recursively) {
		Set<UserRole> roles = new HashSet<UserRole>(userGroup.getRoles());
		if (recursively) {
			Set<UserAssociation> parents = userGroup.getParentAssociations();
			for (UserAssociation parentAssociation: parents) {
				UserGroup parent = parentAssociation.getParent();
				if (parent!=null) {
//					userRepository.saveOrUpdate(parent);
					// TODO SORRY, for the moment only one level recursion available...
					roles.addAll(parent.getRoles());
				}
			}
		}
		logger.debug("Found {} role(s) FOR {}.", roles.size(), userGroup);
		return roles;
	}
	
//	public List<UserRole> findRoles(Filter filter, boolean recursively) {
//		List<UserRole> userRoleList = (List<UserRole>) UserRoleRepository.find(filter);
//		if (recursively) {
//			UserGroup userGroup = ((UserRoleFilterAdapter) filter).getForm().getUserGroup();
//			if (userGroup==null) {
//				throw new IllegalArgumentException("UserGroup required.");
//			}
//			userRepository.saveOrUpdate(userGroup);
//			Set<UserAssociation> parents = userGroup.getParentAssociations();
//			for (UserAssociation parentAssociation: parents) {
//				UserGroup parent = parentAssociation.getParent();
//				if (parent!=null) {
////					userRepository.saveOrUpdate(parent);
//					// TODO SORRY, for the moment only one level recursion available...
//					UserRoleFilterAdapter parentFilter = (UserRoleFilterAdapter) ((UserRoleFilterAdapter) filter).clone();
//					parentFilter.getForm
//					List<UserRole> userParentRoleList = (List<UserRole>) UserRoleRepository.find(filter);
//					roles.addAll(parent.getRoles());
//				}
//			}
//		}
//		logger.debug("Found {} role(s) FOR {}.", roles.size(), userGroup);
//		return roles;
//	}
	
	public void authenticate(User user, Set<UserRole> roles) {
		authenticate(new UserDetailsAdapter(user, (IdentitySecurity) null, roles));
	}
	
	public void authenticate(UserDetailsAdapter userDetails) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "any", userDetails.getAuthorities());   //   PreAuthenticatedAuthenticationToken(aPrincipal, aCredentials, anAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.debug("Authenticated {}.", authentication);
	}
	
	public void clearAuthentication() {
		SecurityContextHolder.clearContext();
	}
	
	@Transactional(readOnly=true)
	public PublicUserDetails findAuthenticatedUser() {
		try {
			UserDetailsAdapter userDetails = UserDetailsAdapter.getUserDetailsFromContext();
			User user = userRepository.findOne(userDetails.getUser().getId());
			userDetails.updateUser(user);
			return userDetails;
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to find authenticated user.", e);
		}
	}
	
	@Transactional(readOnly=true)
	public List<IdentitySecurity> findIdentitySecurity(IdentitySecurityForm form) {
		return (List<IdentitySecurity>) identitySecurityRepository.find(new IdentitySecurityFilterAdapter(form));
	}

	@Transactional(readOnly=true)
	public IdentitySecurity findIdentitySecurity(String consumerKey) {
		return identitySecurityRepository.findByConsumerKey(consumerKey);
	}

	@Transactional
	public IdentitySecurity storeIdentitySecurity(IdentitySecurity identitySecurity) {
		if (identitySecurity.isPasswordVerified()) {
			String encodedPassword = passwordEncoder.encode(identitySecurity.getPassword());
			identitySecurity.setConsumerSecret(encodedPassword);
			return identitySecurityRepository.saveAndFlush(identitySecurity);
		}
		throw new PasswordNotVerifiedException();
	}

    // collabs

    private IdentityRepository identityRepository;
    private UserRepository userRepository;
    private CredentialRepository credentialRepository;
    private IdentitySecurityRepository identitySecurityRepository;
    private PasswordEncoder passwordEncoder;

    @Resource
    public void setIdentityRepository(IdentityRepository identityRepository) {
		this.identityRepository = identityRepository;
	}
    
    @Resource
    public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    @Resource
    public void setCredentialRepository(CredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}
    
    @Resource
    public void setIdentitySecurityRepository(IdentitySecurityRepository identitySecurityRepository) {
		this.identitySecurityRepository = identitySecurityRepository;
	}

    private final static Logger logger = LoggerFactory.getLogger(SecurityMgrImpl.class);

}
