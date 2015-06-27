package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Card set repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CardSetRepository 
	extends JpaRepository<CardSet, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	CardSet findByEntityAndInternalNumber(Entity entity, long internalNumber);

}