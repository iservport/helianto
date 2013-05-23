package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Private address repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateAddressRepository extends FilterRepository<PrivateAddress, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param sequence
	 */
	PrivateAddress findByPrivateEntityAndSequence(PrivateEntity privateEntity, int sequence);
	
}
