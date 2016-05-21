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

import javax.annotation.Resource;

import org.helianto.core.DuplicateIdentityException;
import org.helianto.core.IdentityMgr;
import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.ContactInfo;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;
import org.helianto.core.domain.Phone;
import org.helianto.core.repository.CredentialRepository;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.repository.PersonalAddressRepository;
import org.helianto.core.service.internal.PrincipalGenerationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default <code>IdentityMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
@org.springframework.stereotype.Service("identityMgr")
public class IdentityMgrImpl implements IdentityMgr {
    
	@Transactional(readOnly=true)
    public Identity findIdentityByPrincipal(String principal) {
        logger.debug("Finding unique with principal {}", principal);
        return (Identity) identityRepository.findByPrincipal(principal);
    }
    
    /**
     * Store the given <code>Identity</code>.
     * 
     * @param identity
     */
	@Transactional
	public Identity storeIdentity(Identity identity) {
		return identityRepository.saveAndFlush(identity);
	}
	
    /**
     * Store the given <code>Identity</code>.
     * 
     * <p>
     * This implementation also checks for a previous identity with the same 
     * principal and raises an <code>DuplicateIdentityException</code> accordingly.
     * Note that the persistence context (or Hibernate session) is not affected by the latter
     * because no integrity violation exception is allowed.</p>
     * 
     * @param identity
     * @param generate
     */
	@Transactional
	public Identity storeIdentity(Identity identity, boolean generate) {
		if (generate) {
			int attemptCount = 0;
			principalGenerationStrategy.generatePrincipal(identity, attemptCount);
			if (identity.getId()==0) {
				logger.debug("Identity with principal {} is likely new.", identity.getPrincipal());
				Identity checkForPreviousIdentity = (Identity) identityRepository.findByPrincipal(identity.getPrincipal());
				if (checkForPreviousIdentity!=null) {
					logger.warn("Found previous identity with same principal as new indentity: {}, rejecting.", checkForPreviousIdentity);
					throw new DuplicateIdentityException(checkForPreviousIdentity, "Found previous identity with same principal as new indentity");
				}
			}
		}
		return identityRepository.saveAndFlush(identity);
	}
	
	@Transactional
	public PersonalAddress storePersonalAddress(PersonalAddress personalAddress) {
		return personalAddressRepository.saveAndFlush(personalAddress);
	}
	
	@Transactional
	public Identity storeIdentityPhone(Phone identityPhone, Identity identity) {
		identity.getPhones().add(identityPhone);
		return identityRepository.saveAndFlush(identity);
	}
	
	@Transactional
	public Identity storeIdentityContactInfo(ContactInfo contactInfo, Identity identity) {
		identity.getContactInfos().add(contactInfo);
		return identityRepository.saveAndFlush(identity);
	}
	
	@Transactional
	public Credential installCredential(Identity identity) {
		Credential credential = credentialRepository.findByIdentity(identity);
		if (credential==null) {
			logger.info("Will install credential for {}.", identity);
			credential = credentialRepository.saveAndFlush(new Credential(identity, ActivityState.ACTIVE.getValue()));
		}
		else {
			logger.debug("Found existing credential for {}.", identity);
		}
		return credential;
	}
	
    //- collaborators
    
    private IdentityRepository identityRepository;
    private CredentialRepository credentialRepository;
    private PersonalAddressRepository personalAddressRepository;
    private PrincipalGenerationStrategy principalGenerationStrategy;
	

    @Resource
    public void setIdentityRepository(IdentityRepository identityRepository) {
		this.identityRepository = identityRepository;
	}
    
    @Resource
    public void setCredentialRepository(CredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}
    
    @Resource
    public void setPersonalAddressRepository(
			PersonalAddressRepository personalAddressRepository) {
		this.personalAddressRepository = personalAddressRepository;
	}

    @Resource
	public void setPrincipalGenerationStrategy(PrincipalGenerationStrategy principalGenerationStrategy) {
		this.principalGenerationStrategy = principalGenerationStrategy;
	}

    private static final Logger logger = LoggerFactory.getLogger(IdentityMgrImpl.class);


}
