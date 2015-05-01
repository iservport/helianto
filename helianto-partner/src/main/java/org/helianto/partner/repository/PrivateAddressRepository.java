package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Private address repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateAddressRepository extends JpaRepository<PrivateAddress, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param sequence
	 */
	PrivateAddress findByPrivateEntityAndSequence(PrivateEntity privateEntity, int sequence);
	
}
