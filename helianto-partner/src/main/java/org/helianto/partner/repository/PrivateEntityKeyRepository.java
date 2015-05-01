package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Private entity key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityKeyRepository extends JpaRepository<PrivateEntityKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param keyType
	 */
	PrivateEntityKey findByPrivateEntityAndKeyType(PrivateEntity privateEntity, KeyType keyType);
	
}
