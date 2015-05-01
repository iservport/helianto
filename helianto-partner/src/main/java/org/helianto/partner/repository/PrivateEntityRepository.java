package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Account repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityRepository extends JpaRepository<PrivateEntity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param entityAlias
	 */
	PrivateEntity findByEntityAndEntityAlias(Entity entity, String entityAlias);
	
	/**
	 * Find by entity.
	 * 
	 * @param entity
	 * @param pageable
	 */
	Page<PrivateEntity> findByEntity(Entity entity, Pageable pageable);
	
}
