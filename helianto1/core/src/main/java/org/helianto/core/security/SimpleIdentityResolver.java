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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.service.SecurityMgr;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * Strategy to resolve an <code>Identity</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SimpleIdentityResolver implements IdentityResolutionStrategy {

    private SecurityMgr securityMgr;
    
    /**
     * Load and validate an <code>Identity</code>
     * 
     * @param principal
     */
    public Identity loadAndValidateIdentity(String principal) {
        Identity identity = null;
        try {
            identity = securityMgr.findIdentityByPrincipal(principal);
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
    
    //- collabs

    @Required
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }
    
    private static Log logger = LogFactory.getLog(SimpleIdentityResolver.class);
    
}
