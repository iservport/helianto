package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;

/**
 * Category repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CategoryRepository extends FilterRepository<Category, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param categoryGroup
	 * @param categoryCode
	 */
	Category findByEntityAndCategoryGroupAndCategoryCode(Entity entity, char categoryGroup, String categoryCode);
	
}