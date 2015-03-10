package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

/**
 * Category repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CategoryRepository extends QueryRepository<Category, Serializable> {
	
	/**
	 * Find by category group.
	 * 
	 * @param entity
	 * @param categoryGroup
	 */
	List<Category> findByEntityAndCategoryGroup(Entity entity, char categoryGroup);
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param categoryGroup
	 * @param categoryCode
	 */
	Category findByEntityAndCategoryGroupAndCategoryCode(Entity entity, char categoryGroup, String categoryCode);
	
	/**
	 * Find by Entity Id
	 * 
	 * @param entityId
	 * @return
	 */
	List<Category> findByEntity_Id(int entityId);
	
	/**
	 * Category adapter list.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param sort
	 */
	@Query("select new "
			+ "org.helianto.core.repository.CategoryAdapter"
			+ "(category.id"
			+ ", category.categoryCode"
			+ ", category.categoryName"
			+ ", category.categoryIcon"
			+ ") "
			+ "from Category category "
			+ "where category.entity.id = ?1 "
			+ "and category.categoryGroup = ?2 ")
	List<CategoryAdapter> findByEntity_IdAndCategoryGroup(int entityId, char categoryGroup
			, Sort sort);

	/**
	 * Category counter.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param categoryCode
	 */
	@Query("select count(category) "
			+ "from Category category "
			+ "where category.entity.id = ?1 "
			+ "and category.categoryGroup = ?2 "
			+ "and category.categoryCode like ?3 ")
	Long countByEntity_IdAndCategoryGroupAndCategoryCode(int entityId, char categoryGroup, String categoryCode);

}
