package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PublicSequence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Public sequence repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicSequenceRepository extends JpaRepository<PublicSequence, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param typeName
	 */
	PublicSequence findByOperatorAndTypeName(Operator operator, String typeName);
	
}
