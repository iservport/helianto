package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PublicEntity;

/**
 * Public entity repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityRepository extends FilterRepository<PublicEntity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param entityAlias
	 */
	PublicEntity findByEntityAndEntityAlias(Entity entity, String entityAlias);
	
}
