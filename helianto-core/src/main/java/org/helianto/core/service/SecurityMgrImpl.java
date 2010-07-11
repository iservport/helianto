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
import org.helianto.core.IdentityFilter;
import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.SecureUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	        return credentialDao.merge(credential);
		}
		throw new PasswordNotVerifiedException();
	}

	public void storeCredential(SecureUserDetails secureUser) {
		Credential credential = credentialDao.merge(secureUser.getCredential());
		secureUser.setCredential(credential);
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

    public PublicUserDetails findSecureUser() {
        return UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
    }
    
    // collabs

    private FilterDao<Identity, IdentityFilter> identityDao;
    private BasicDao<Credential> credentialDao;

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity, IdentityFilter> identityDao) {
        this.identityDao = identityDao;
    }

    @Resource(name="credentialDao")
    public void setCredentialDao(BasicDao<Credential> credentialDao) {
        this.credentialDao = credentialDao;
    }

    private final static Logger logger = LoggerFactory.getLogger(SecurityMgrImpl.class);
    
}
