package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Unit;

/**
 * Unit repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UnitRepository extends FilterRepository<Unit, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param category
	 * @param unitCode
	 */
	Unit findByCategoryAndUnitCode(Category category, String unitCode);
	
}
