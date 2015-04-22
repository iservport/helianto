package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PublicAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Public address repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicAddressRepository extends JpaRepository<PublicAddress, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param postalCode
	 */
	PublicAddress findByOperatorAndPostalCode(Operator operator, String postalCode);
	
}
