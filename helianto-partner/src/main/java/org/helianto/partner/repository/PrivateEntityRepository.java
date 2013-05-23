package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Account repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateEntityRepository extends FilterRepository<PrivateEntity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param entityAlias
	 */
	PrivateEntity findByEntityAndEntityAlias(Entity entity, String entityAlias);
	
}
