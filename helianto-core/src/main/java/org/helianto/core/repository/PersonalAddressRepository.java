package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;

/**
 * Personal address repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PersonalAddressRepository extends FilterRepository<PersonalAddress, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 * @param addressType
	 */
	PersonalAddress findByIdentityAndAddressType(Identity identity, char addressType);
	
}
