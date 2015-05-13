package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Category repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CategoryRepository extends JpaRepository<Category, Serializable> {
	
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
	 * Find adapter by Id.
	 * 
	 * @param id
	 */
	@Query("select new "
			+ "org.helianto.core.repository.CategoryReadAdapter"
			+ "(category.id"
			+ ", category.entity.id"
			+ ", category.categoryGroup"
			+ ", category.categoryCode"
			+ ", category.categoryName"
			+ ", category.categoryIcon"
			+ ", category.scriptItems"
			+ ", category.customProperties"
			+ ") "
			+ "from Category category "
			+ "where category.id = ?1 ")
	CategoryReadAdapter findAdapter(int id);

	/**
	 * Category adapter list.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param sort
	 */
	@Query("select new "
			+ "org.helianto.core.repository.CategoryReadAdapter"
			+ "(category.id"
			+ ", category.entity.id"
			+ ", category.categoryGroup"
			+ ", category.categoryCode"
			+ ", category.categoryName"
			+ ", category.categoryIcon"
			+ ", category.scriptItems"
			+ ", category.customProperties"
			+ ") "
			+ "from Category category "
			+ "where category.entity.id = ?1 "
			+ "and category.categoryGroup = ?2 ")
	List<CategoryReadAdapter> findByEntity_IdAndCategoryGroup(int entityId, char categoryGroup, Sort sort);

	/**
	 * Category adapter list.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param sort
	 */
	@Query("select new "
			+ "org.helianto.core.repository.CategoryReadAdapter"
			+ "(category.id"
			+ ", category.entity.id"
			+ ", category.categoryGroup"
			+ ", category.categoryCode"
			+ ", category.categoryName"
			+ ", category.categoryIcon"
			+ ", category.scriptItems"
			+ ", category.customProperties"
			+ ") "
			+ "from Category category "
			+ "where category.entity.id = ?1 "
			+ "and category.categoryCode like %?2% ")
	Page<CategoryReadAdapter> findByEntity_IdAndCategoryCodeLike(int entityId, String categoryCode, Pageable sort);

	/**
	 * Category adapter page.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param pageable
	 */
	@Query("select new "
			+ "org.helianto.core.repository.CategoryReadAdapter"
			+ "(category.id"
			+ ", category.entity.id"
			+ ", category.categoryGroup"
			+ ", category.categoryCode"
			+ ", category.categoryName"
			+ ", category.categoryIcon"
			+ ", category.scriptItems"
			+ ", category.customProperties"
			+ ") "
			+ "from Category category "
			+ "where category.entity.id = ?1 "
			+ "and category.categoryGroup = ?2 ")
	Page<CategoryReadAdapter> findByEntity_IdAndCategoryGroup(int entityId, char categoryGroup, Pageable page);

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

	/**
	 * Category existing.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param categoryCode
	 */
	@Query("select category.id "
			+ "from Category category "
			+ "where category.entity.id = ?1 "
			+ "and category.categoryGroup = ?2 "
			+ "and category.categoryCode = ?3 ")
	Integer findByEntity_IdAndCategoryGroupAndCategoryCode(int entityId, char categoryGroup, String categoryCode);

}
