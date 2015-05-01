package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Partner phone repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerPhoneRepository extends JpaRepository<PartnerPhone, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param sequence
	 */
	PartnerPhone findByPrivateEntityAndSequence(PrivateEntity privateEntity, int sequence);
	
}
