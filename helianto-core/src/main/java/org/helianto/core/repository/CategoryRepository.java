package org.helianto.core.repository;


import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.enums.CategoryGroup;
import org.helianto.core.domain.Category2;
import org.helianto.core.domain.Entity;
import org.helianto.core.internal.SimpleCounter;
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
public interface CategoryRepository extends JpaRepository<Category2, Serializable> {
	
	/**
	 * Find by category group.
	 * 
	 * @param entity
	 * @param categoryGroup
	 */
	List<Category2> findByEntityAndCategoryGroup(Entity entity, char categoryGroup);
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param categoryGroup
	 * @param categoryCode
	 */
	Category2 findByEntityAndCategoryGroupAndCategoryCode(Entity entity, char categoryGroup, String categoryCode);
	
	/**
	 * Find by Entity Id
	 * 
	 * @param entityId
	 * @return
	 */
	List<Category2> findByEntity_Id(int entityId);
	
	public static final String QUERY = "select new Category2"
			+ "(category_.id"
			+ ", category_.content"
			+ ", category_.encoding"
			+ ", category_.multipartFileContentType"
			+ ", category_.categoryGroupType"
			+ ", category_.categoryCode"
			+ ", category_.categoryLabel"
			+ ", category_.categoryName"
			+ ", category_.categoryIcon"
			+ ", category_.priority"
			+ ", category_.referenceList"
			+ ", category_.customStyle"
			+ ", category_.customWorkflowRoles"
			+ ", category_.customProperties"
			+ ", category_.customNumberPattern"
			+ ", category_.patternPrefix"
			+ ", category_.patternSuffix"
			+ ", category_.numberOfDigits"
			+ ", category_.partnerFilterPattern"
			+ ", category_.scriptItems"
			+ ", category_.activityCode"
			+ ") "
			+ "from Category2 category_ ";
	
	/**
	 * Find category by Id.
	 * 
	 * @param id
	 */
	@Query(QUERY
			+ "where category_.id = ?1 ")
    Category2 findAdapter(int id);

	/**
	 * Category list.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param sort
	 */
	@Query(QUERY
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryGroup = ?2 ")
	List<Category2> findByEntity_IdAndCategoryGroup(int entityId, char categoryGroup, Sort sort);

	/**
	 * Category page.
	 * 
	 * @param entityId
	 * @param categoryGroupType
	 * @param page
	 */
	@Query(QUERY
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryGroupType = ?2 ")
	Page<Category2> findByEntity_IdAndCategoryGroupType(int entityId, CategoryGroup categoryGroupType, Pageable page);

	/**
	 * Category adapter list.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param sort
	 */
	@Query(QUERY
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryCode like %?2% ")
	Page<Category2> findByEntity_IdAndCategoryCodeLike(int entityId, String categoryCode, Pageable sort);

	/**
	 * Category adapter page.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param pageable
	 */
	@Query(QUERY
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryGroup = ?2 ")
	Page<Category2> findByEntity_IdAndCategoryGroup(int entityId, char categoryGroup, Pageable page);

	@Query(QUERY
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryGroup in ?2 "
			+ "and (category_.categoryCode like %?3% OR category_.categoryName like %?3% )")
	Page<Category2> findByEntityIdAndCategoryGroupAndCategoryCodeLikeOrCategoryNameLike(int entityId, char[] categoryGroup
			, String search, Pageable page);
	
	@Query("select new "
			+ "org.helianto.core.internal.SimpleCounter(category_.categoryGroup, count(category_.id)) "
			+ "from Category2 category_ "
			+ "where category_.entity.id = ?1 "
			+ "group by category_.categoryGroup ")
	SimpleCounter countCategoryByGroup(int entityId);

	@Query("select "
			+ "category_.categoryGroup "
			+ "from Category2 category_ "
			+ "where category_.entity.id = ?1 "
			+ "group by category_.categoryGroup ")
	List<Character> countCategoryByGroup2(int entityId);

	/**
	 * Category counter.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param categoryCode
	 */
	@Query("select count(category_) "
			+ "from Category2 category_ "
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryGroup = ?2 "
			+ "and category_.categoryCode like ?3 ")
	Long countByEntity_IdAndCategoryGroupAndCategoryCode(int entityId, char categoryGroup, String categoryCode);

	/**
	 * Category existing.
	 * 
	 * @param entityId
	 * @param categoryGroup
	 * @param categoryCode
	 */
	@Query("select category_.id "
			+ "from Category2 category_ "
			+ "where category_.entity.id = ?1 "
			+ "and category_.categoryGroup = ?2 "
			+ "and category_.categoryCode = ?3 ")
	Integer findByEntity_IdAndCategoryGroupAndCategoryCode(int entityId, char categoryGroup, String categoryCode);

	/**
	 * Count categories by group.
	 * 
	 * @param entityId
	 */
	@Query("select new " +
			"org.helianto.core.internal.SimpleCounter" +
			"(category_.categoryGroup, count(category_)) " +
			"from Category2 category_ " +
			"where category_.entity.id = ?1 " +
			"group by category_.categoryGroup")
	List<SimpleCounter> countCategoriesByEntityIdGroupByGroup(int entityId);
	
}
