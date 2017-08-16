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

package org.helianto.classic;

import org.helianto.core.domain.ContactInfo;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;
import org.helianto.core.domain.Phone;

/**
 * Default identity service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface IdentityMgr {
	
    /**
     * Find <core>Identity</core> by principal.
     * 
     * @param principal
     */
    public Identity findIdentityByPrincipal(String principal);
    
    /**
     * Store the given <code>Identity</code>.
     * 
     * @param identity
     */
    public Identity storeIdentity(Identity identity);
    
    /**
     * Store the given <code>Identity</code>.
     * 
     * @param identity
     * @param generate
     */
	public Identity storeIdentity(Identity identity, boolean generate);
	
    /**
     * Store the given <code>PersonalAddress</code>.
     * 
     * @param personalAddress
     */
    public PersonalAddress storePersonalAddress(PersonalAddress personalAddress);
    
    /**
     * Store the given <code>Phone</code>.
     * 
     * @param identityPhone
     * @param identity
     */
    public Identity storeIdentityPhone(Phone identityPhone, Identity identity);
    
    /**
     * Store the given <code>ContactInfo</code>.
     * 
     * @param contactInfo
     * @param identity
     */
    public Identity storeIdentityContactInfo(ContactInfo contactInfo, Identity identity);
    
}
