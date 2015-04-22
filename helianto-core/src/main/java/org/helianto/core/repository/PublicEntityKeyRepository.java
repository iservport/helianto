package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.PublicEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Public entity key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityKeyRepository extends JpaRepository<PublicEntityKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param publicEntity
	 * @param keyType
	 */
	PublicEntityKey findByPublicEntityAndKeyType(PublicEntity publicEntity, KeyType keyType);
	
}
