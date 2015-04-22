package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Unit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Unit repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UnitRepository extends JpaRepository<Unit, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param unitCode
	 */
	Unit findByEntityAndUnitCode(Entity entity, String unitCode);
	
	/**
	 * Find by natural key.
	 * 
	 * @param entityId
	 * @param nature
	 * @param page
	 */
	List<Unit> findByEntity_IdAndNatureContaining(int entityId, String nature, Pageable page);
	
}
