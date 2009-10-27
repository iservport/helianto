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

package org.helianto.core.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.UserRole;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Custom implementation for the {@link org.springframework.security.userdetails.UserDetailsService}
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl extends AbstractUserDetailsServiceTemplate {
    
    /**
     * Load and validate an <code>Identity</code>
     * 
     * @param principal
     */
    @Override
    public Identity loadAndValidateIdentity(String principal) {
        Identity identity = null;
        try {
            identity = userMgr.findIdentityByPrincipal(principal);
            Assert.notNull(identity, "Null Identity");
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username "+principal, e);
        }
        return identity;
    }
    
    /**
     * Load and validate a <code>Credential</code>
     * 
     * @param identity
     */
    @Override
    public Credential loadAndValidateCredential(Identity identity) {
        try {
            //TODO find only active credential
            Credential credential = securityMgr.findCredentialByIdentity(identity);
            if (credential!=null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("User credential loaded");
                }
                return credential;
            } else {
                throw new DataRetrievalFailureException("Bad credential");
            }
        } catch (Exception e) {
            throw new DataRetrievalFailureException("General login failure", e);
        }
    }
    
    @Override
    public List<UserGroup> listUsers(Identity identity) {
    	return userMgr.findUsers(identity);
    }
    
    /**
     * Log this visit, next login this user will be selected
     */
    @Override
	public User storeUser(User user) {
        UserLog managedUserLog = userMgr.storeUserLog(user, new Date());
        return managedUserLog.getUser();
	}

	@Override
	protected Collection<UserRole> loadAndValidateRoles(User user) {
		return userMgr.prepareUserGroup(user).getRoleList();
	}

	//- collabs

    private SecurityMgr securityMgr;
    private UserMgr userMgr;
    
    @Resource
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }

    @Resource
    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    private static Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);
    
}
