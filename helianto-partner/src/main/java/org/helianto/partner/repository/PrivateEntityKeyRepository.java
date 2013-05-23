package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.KeyType;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;

/**
 * Private entity key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityKeyRepository extends FilterRepository<PrivateEntityKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param keyType
	 */
	PrivateEntityKey findByPrivateEntityAndKeyType(PrivateEntity privateEntity, KeyType keyType);
	
}
