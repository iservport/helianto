package org.helianto.core.repository;

import org.helianto.core.domain.KeyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Key type repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface KeyTypeRepository extends JpaRepository<KeyType, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param contextName
	 * @param keyCode
	 */
	KeyType findByContextNameAndKeyCode(String contextName, String keyCode);
	
}
