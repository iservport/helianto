package org.helianto.core.repository;

import org.helianto.core.domain.PublicAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Public address repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicAddressRepository extends JpaRepository<PublicAddress, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param contextName
	 * @param postalCode
	 */
	PublicAddress findByContextNameAndPostalCode(String contextName, String postalCode);
	
}
