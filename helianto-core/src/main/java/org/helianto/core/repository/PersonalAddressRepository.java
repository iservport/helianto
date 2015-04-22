package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Personal address repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PersonalAddressRepository extends JpaRepository<PersonalAddress, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 * @param addressType
	 */
	PersonalAddress findByIdentityAndAddressType(Identity identity, char addressType);
	
}
