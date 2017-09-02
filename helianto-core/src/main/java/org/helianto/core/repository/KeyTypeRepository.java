package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
