package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Partner phone repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerPhoneRepository extends FilterRepository<PartnerPhone, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param sequence
	 */
	PartnerPhone findByPrivateEntityAndSequence(PrivateEntity privateEntity, int sequence);
	
}
