package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PrivateSequence;

/**
 * Private sequence repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateSequenceRepository extends FilterRepository<PrivateSequence, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param typeName
	 */
	PrivateSequence findByEntityAndTypeName(Entity entity, String typeName);
	
}
