package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PublicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Public entity repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicEntityRepository extends JpaRepository<PublicEntity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param entityAlias
	 */
	PublicEntity findByEntityAndEntityAlias(Entity entity, String entityAlias);
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 */
	PublicEntity findByEntity(Entity entity);
	
}
