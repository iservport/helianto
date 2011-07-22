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

import javax.annotation.Resource;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.DuplicateIdentityException;
import org.helianto.core.Identity;
import org.helianto.core.PersonalAddress;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default <code>IdentityMgr</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@org.springframework.stereotype.Service("identityMgr")
public class IdentityMgrImpl implements IdentityMgr {
    
    public Identity findIdentityByPrincipal(String principal) {
        logger.debug("Finding unique with principal {}", principal);
        return (Identity) identityDao.findUnique(principal);
    }
    
    public Identity loadIdentity(long id) {
    	Identity sample = new Identity();
    	sample.setId(id);
    	Identity identity = identityDao.load(sample);
    	if (identity!=null && identity.getPhoto()!=null) {
    		logger.debug("Identity {} photo size is {}.", identity, identity.getPhoto().length);
    	}
    	return identity;
    }
    
    public byte[] loadIdentityPhoto(Identity identity) {
    	if (identity!=null && identity.getPhoto()!=null) {
    		logger.debug("Identity {} photo size is {}.", identity, identity.getPhoto().length);
    		return identity.getPhoto();
    	}
    	logger.debug("Identity {} photo not available.", identity);
    	return null;
    }

    public List<Identity> findIdentities(Filter filter, Collection<Identity> exclusions) {
        List<Identity> identityList = (List<Identity>) identityDao.find(filter);
        logger.debug("Found {} item(s).", identityList.size());
        if (exclusions!=null) {
            identityList.removeAll(exclusions);
            logger.debug("Removed {} item(s)", exclusions.size());
        }
        return identityList ;
    }

    /**
     * Store the given <code>Identity</code>.
     * 
     * @param identity
     */
	public Identity storeIdentity(Identity identity) {
		identityDao.saveOrUpdate(identity);
		return identity;
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
	public Identity storeIdentity(Identity identity, boolean generate) {
		if (generate) {
			int attemptCount = 0;
			principalGenerationStrategy.generatePrincipal(identity, attemptCount);
			if (identity.getId()==0) {
				logger.debug("Identity with principal {} is likely new.", identity.getPrincipal());
				Identity checkForPreviousIdentity = (Identity) identityDao.findUnique(identity.getPrincipal());
				if (checkForPreviousIdentity!=null) {
					logger.warn("Found previous identity with same principal as new indentity: {}, rejecting.", checkForPreviousIdentity);
					throw new DuplicateIdentityException(checkForPreviousIdentity, "Found previous identity with same principal as new indentity");
				}
			}
		}
		identityDao.saveOrUpdate(identity);
		return identity;
	}
	
	public List<PersonalAddress> findPersonalAddresses(Filter filter) {
		List<PersonalAddress> personalAddressList = (List<PersonalAddress>) personalAddressDao.find(filter);
		if (personalAddressList!=null) {
			logger.debug("Found {} personal addresses.", personalAddressList.size());
		}
		return personalAddressList;
	}
	
	public PersonalAddress storePersonalAddress(PersonalAddress personalAddress) {
		personalAddressDao.saveOrUpdate(personalAddress);
		return personalAddress;
	}
	
	public Credential installIdentity(String principal) {
		Identity identity = identityDao.findUnique(principal);
		if (identity==null) {
			logger.info("Will install identity for {}.", principal);
			identity = new Identity(principal);
			identityDao.saveOrUpdate(identity);
		}
		else {
			logger.debug("Found existing identity for {}.", principal);
		}
		return installCredential(identity);
	}
	
	public Credential installCredential(Identity identity) {
		Credential credential = credentialDao.findUnique(identity);
		if (credential==null) {
			logger.info("Will install credential for {}.", identity);
			credential = new Credential(identity);
			// TODO make it INITIAL
			credential.setCredentialState(ActivityState.ACTIVE);
			credentialDao.saveOrUpdate(credential);
			credentialDao.flush();
		}
		else {
			logger.debug("Found existing credential for {}.", identity);
		}
		return credential;
	}
	
    //- collaborators
    
    private FilterDao<Identity> identityDao;
    private FilterDao<Credential> credentialDao;
    private FilterDao<PersonalAddress> personalAddressDao;
    private PrincipalGenerationStrategy principalGenerationStrategy;
	

    @Resource(name="identityDao")
    public void setIdentityDao(FilterDao<Identity> identityDao) {
        this.identityDao = identityDao;
    }
    
    @Resource(name="credentialDao")
    public void setCredentialDao(FilterDao<Credential> credentialDao) {
		this.credentialDao = credentialDao;
	}
    
    @Resource(name="personalAddressDao")
    public void setPersonalAddressDao(FilterDao<PersonalAddress> personalAddressDao) {
		this.personalAddressDao = personalAddressDao;
	}

    @Resource
	public void setPrincipalGenerationStrategy(PrincipalGenerationStrategy principalGenerationStrategy) {
		this.principalGenerationStrategy = principalGenerationStrategy;
	}

    private static final Logger logger = LoggerFactory.getLogger(IdentityMgrImpl.class);


}
