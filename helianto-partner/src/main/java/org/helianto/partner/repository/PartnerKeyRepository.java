package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Partner key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerKeyRepository extends JpaRepository<PartnerKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param partner
	 * @param keyType
	 */
	PartnerKey findByPartnerAndKeyType(Partner partner, KeyType keyType);
	
}
