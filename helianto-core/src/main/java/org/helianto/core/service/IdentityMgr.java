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

import java.util.Collection;
import java.util.List;

import org.helianto.core.ContactInfo;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.PersonalAddress;
import org.helianto.core.Phone;
import org.helianto.core.filter.Filter;

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
     * Load <core>Identity</core> by id.
     * 
     * @param id
     */
    public Identity loadIdentity(long id);
    
    /**
     * Load the <core>Identity</core> photo.
     * 
     * @param identity
     */
    public byte[] loadIdentityPhoto(Identity identity);
    
    /**
     * <p>Selects an <code>Identity</code> list.
     * 
     * @param filter
     * @param exclusions list to be removed after selection
     */
    public List<Identity> findIdentities(Filter filter, Collection<Identity> exclusions);
    
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
     * Find a <code>PersonalAddress</code> list.
     * 
     * @param filter
     */
    public List<PersonalAddress> findPersonalAddresses(Filter filter);
    
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
    
    /**
     * <p>Create <code>Credential</code> and <code>Identity</code>.</p>
     * 
     * @param principal
     */
    public Credential installIdentity(String principal);
    
    /**
     * <p>Create <code>Credential</code>.</p>
     * 
     * @param identity
     */
    public Credential installCredential(Identity identity);
    
}
