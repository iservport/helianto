package org.helianto.order.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.Part;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.helianto.order.repository.PartReadAdapter;

/**
 * Part repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartRepository 
	extends QueryRepository<Part, Serializable> 
{
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param docCode
	 */
	Part findByEntityAndDocCode(Entity entity, String docCode);

	/**
	 * Find by Natural Key
	 * 
	 * @param entity id
	 * @param docCode
	 */
	Part findByEntity_IdAndDocCode(int entityId, String docCode);
	
	/**
	 * Find adapter by id.
	 * 
	 * @param partId
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue"
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "where part_.id = ?1 ")
	PartReadAdapter findAdapter(Integer partId);

	/**
	 * Page by category id and resolution.
	 * 
	 * @param entityId
	 * @param categoryId
	 * @param resolution
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "where part_.entity.id = ?1 "
			+ "and part_.category.id = ?2 "
			+ "and part_.resolution = ?3 ")
	Page<PartReadAdapter> findByCategoryIdAndResolution(int entityId, Integer categoryId, Character resolution, Pageable page);
	
	/**
	 * Page by category id and state.
	 * 
	 * @param categoryId
	 * @param activityState
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "where part_.category.id = ?1 "
			+ "and part_.activityState = ?2 ")
	Page<PartReadAdapter> findByCategoryIdAndActivityState(Integer categoryId, Character activityState, Pageable page);
	
	/**
	 * Page like doc code.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "WHERE part_.entity.id = ?1 "
			+ "and part_.docCode like ?2 ")
	Page<PartReadAdapter> findByEntity_IdAndDocCode(int entityId, String docCode, Pageable page);
	
	/**
	 * Page like doc code or doc name.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param docName
	 * @param exclusions
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "where (part_.entity.id = ?1 "
			+ "and part_.docCode like ?2 or part_.docName like ?3) "
			+ "and part_.id not in ?4 ")
	Page<PartReadAdapter> findByEntity_IdAndDocCodeOrDocName(int entityId, String docCode, String docName, List<Integer> exclusions, Pageable page);
	
	/**
	 * Page by entity id.
	 * 
	 * @param entityId
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "where part_.entity.id = ?1 ")
	Page<PartReadAdapter> findByEntity_Id(int entityId, Pageable page);
	
	/**
	 * Page like doc name.
	 * 
	 * @param entityId
	 * @param docName
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "WHERE part_.entity.id = ?1 "
			+ "and part_.docName like ?2 ")
	Page<PartReadAdapter> findByEntity_IdAndDocName(int entityId, String docName, Pageable page);
	
	/**
	 * @deprecated
	 * @param entityId
	 * @param id
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.PartReadAdapter"
			+ "( part_.id" 
			+ ", part_.entity.id" 
			+ ", part_.category.id" 
			+ ", part_.category.categoryCode" 
			+ ", part_.category.categoryName" 
			+ ", part_.owner.id" 
			+ ", part_.currency.id" 
			+ ", part_.issueDate" 
			+ ", part_.docCode" 
			+ ", part_.docName" 
			+ ", part_.docAbstract" 
			+ ", part_.activityState" 
			+ ", part_.docFlag" 
			+ ", part_.docValue" 
			+ ", part_.category.scriptItems"
			+ ") "
			+ "from Part part_ "
			+ "WHERE part_.entity.id = ?1 and part_.id = ?2 ")
	Page<PartReadAdapter> findByEntity_IdAndId(int entityId, int id, Pageable page);
	
}
