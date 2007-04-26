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

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.helianto.core.Identity;
import org.helianto.core.service.SecurityMgr;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

public class SimpleIdentityResolver implements IdentityResolutionStrategy {

    private SecurityMgr securityMgr;
    
    /**
     * Load and validate an <code>Identity</code>
     * 
     * @param principal
     * @return
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
    
    //- collabs

    @Required
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr = securityMgr;
    }
    
}
