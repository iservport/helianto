package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.Token;
import org.helianto.query.data.QueryRepository;

/**
 * Token repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface TokenRepository 
	extends QueryRepository<Token, Serializable> 
{
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param tokenLabel
	 */
	Token findByEntityAndTokenLabel(Entity entity, String tokenLabel);

	/**
	 * Find by Natural Key
	 * 
	 * @param entity id
	 * @param tokenLabel
	 */
	Token findByEntity_IdAndTokenLabel(int entityId, String tokenLabel);

}
