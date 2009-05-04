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

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.User;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.SecureUserDetails;
import org.helianto.core.security.UserDetailsAdapter;

/**
 * Default implementation for <code>SecurityMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SecurityMgrImpl implements SecurityMgr {
    
	public Credential findCredentialByIdentity(Identity identity) {
		return credentialDao.findUnique(identity);
	}

	public Credential findCredentialByPrincipal(String principal) {
		Identity identity = identityDao.findUnique(principal);
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

    public PublicUserDetails findSecureUser() {
        return UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
    }
    
    public Set<UserRole> prepareAllUserRoles(User user) {
		User managedUser = (User) userGroupDao.merge(user);
		Set<UserRole> allRoles = managedUser.getAllRoles();
		if (allRoles!=null && logger.isDebugEnabled()) {
			logger.debug("Found "+allRoles.size()+" role(s)");
		}
		userGroupDao.evict(managedUser);
		return allRoles;
	}

    // collabs

    private FilterDao<Identity, IdentityFilter> identityDao;
    private BasicDao<Credential> credentialDao;
    private FilterDao<UserGroup, UserFilter> userGroupDao;

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity, IdentityFilter> identityDao) {
        this.identityDao = identityDao;
    }

    @Resource(name="credentialDao")
    public void setCredentialDao(BasicDao<Credential> credentialDao) {
        this.credentialDao = credentialDao;
    }

    @Resource(name="userGroupDao")
	public void setUserGroupDao(FilterDao<UserGroup, UserFilter> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

    private final static Log logger = LogFactory.getLog(SecurityMgrImpl.class);
    
}