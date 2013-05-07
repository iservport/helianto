package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;

/**
 * Key type repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface KeyTypeRepository extends FilterRepository<KeyType, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param keyCode
	 */
	KeyType findByOperatorAndKeyCode(Operator operator, String keyCode);
	
	/**
	 * Find by operator.
	 * 
	 * @param operator
	 */
	List<KeyType> findByOperator(Operator operator);
	
}
