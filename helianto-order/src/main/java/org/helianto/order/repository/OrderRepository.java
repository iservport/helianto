package org.helianto.order.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.order.domain.AbstractOrder;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Order repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface OrderRepository extends
		QueryRepository<AbstractOrder, Serializable> {
	
	/**
	 * Find by Natural Key
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	AbstractOrder findByEntityAndInternalNumber(Entity entity, long internalNumber);

	/**
	 * Find by Natural Key
	 * 
	 * @param entity id
	 * @param internalNumber
	 */
	AbstractOrder findByEntity_IdAndInternalNumber(int entityId, long internalNumber);

	/**
	 * Find adapter by id.
	 * 
	 * @param id
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.id = ?1 ")
	OrderReadAdapter findById(int id);
	
	/**
	 * Page by category id.
	 * 
	 * @param entityId
	 * @param categoryId
	 * @param resolution
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.category.id = ?2 "
			+ "and order_.resolution in ?3   ")
	Page<OrderReadAdapter> findByCategoryIdAndResolution(int entityId, Integer categoryId, char[] resolution, Pageable page);
	
	/**
	 * Page by number.
	 * 
	 * @param entityId
	 * @param internalNumber
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.internalNumber = ?2 ")
	Page<OrderReadAdapter> findByEntity_IdAndInternalNumber(int entityId, Long internalNumber, Pageable page);
	
	/**
	 * Page like doc code.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.part.docCode like ?2 ")
	Page<OrderReadAdapter> findByEntity_IdAndDocCode(int entityId, String docCode, Pageable page);
	
	/**
	 * Page like doc code and by resolution.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param resolution
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.part.docCode like ?2 "
			+ "and order_.resolution = ?3 ")
	Page<OrderReadAdapter> findByEntity_IdAndDocCodeAndResolution(int entityId, String docCode, char resolution, Pageable page);
	
	/**
	 * Page like doc name.
	 * 
	 * @param entityId
	 * @param docName
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.part.docName LIKE ?2 ")
	Page<OrderReadAdapter> findByEntity_IdAndDocName(int entityId, String docName, Pageable page);
	
	/**
	 * Page by search string.
	 * 
	 * @param entityId
	 * @param categoryId
	 * @param resolution
	 * @param search
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.order.repository.OrderReadAdapter"
			+ "(order_.id"
			+ ", order_.internalNumber"
			+ ", order_.part.id"
			+ ", order_.part.docCode"
			+ ", order_.part.docName"
			+ ", order_.issueDate"
			+ ", order_.owner.id"
			+ ", order_.owner.displayName"
			+ ", order_.owner.personalData.imageUrl"
			+ ", order_.resolution"
			+ ", order_.checkOutTime"
			+ ", order_.category.id"
			+ ", order_.part.category.categoryCode"
			+ ", order_.part.category.categoryName"
			+ ", order_.remarks"
			+ ", order_.part.currency.id"
			+ ", order_.faceValue"
			+ ") "
			+ "from AbstractOrder order_ "
			+ "where order_.entity.id = ?1 "
			+ "and order_.category.id = ?2 "
			+ "and order_.resolution in ?3 "
			+ "and (order_.part.docName like %?4%) "
			+ "")
	Page<OrderReadAdapter> findBySearchString(int entityId, Integer categoryId, char[] resolution, String search, Pageable page);

	/**
	 * Find the id from last InternalNumber.
	 */
	@Query("select max(order_.internalNumber) from AbstractOrder order_ ")
	Long findLastInternalNumber();

	/**
	 * List id like docCode.
	 * 
	 * @param entityId
	 * @param docCode
	 * @param page
	 */
	@Query("select order_.part.id "
			+ "from AbstractOrder order_ "
			+ "where order_.part.entity.id = ?1 "
			+ "and order_.part.docCode like ?2 "
			+ "and order_.resolution = 'T' ")
	List<Integer> findPartAdapterToExcludeByEntityIdAndDocCode(int entityId, String docCode, Pageable page);
	
}
