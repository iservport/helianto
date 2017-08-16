package org.helianto.core.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.helianto.core.domain.ContextGroup;
import org.helianto.core.domain.Operator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * ContextGroup repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextGroupRepository 
	extends JpaRepository<ContextGroup, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param contextId
	 * @param contextGroupCode
	 */
	@Query("select contextGroup "
			+ "from ContextGroup contextGroup "
			+ "where contextGroup.context.id = ?1 "
			+ "and contextGroup.contextGroupCode = ?2 ")
	public ContextGroup findByContextIdAndContextGroupCode(int contextId, String contextGroupCode);
	
	/**
	 * Find by entity id.
	 * 
	 * @param entityId
	 * @param contextGroupCode
	 */
	@Query("select c_ "
			+ "from ContextGroup c_, Entity e_ "
			+ "where c_.context.id = e_.operator.id "
			+ "and e_.id = ?1 "
			+ "and c_.contextGroupCode = ?2 ")
	public ContextGroup findByEntityIdAndContextGroupCode(int entityId, String contextGroupCode);
	
	/**
	 * Find by context name.
	 * 
	 * @param contextName
	 * @param contextGroupCodes
	 */
	@Query("select contextGroup_ "
			+ "from ContextGroup contextGroup_ "
			+ "where contextGroup_.context.operatorName = ?1 "
			+ "and contextGroup_.contextGroupCode in ?2 ")
	public List<ContextGroup> findByContextName(String contextName, Collection<String> contextGroupCodes);
	
	/**
	 * List context groups.
	 * 
	 * @param operator
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.core.repository.ContextGroupReadAdapter"
			+ "(contextGroup.id, contextGroup.context.id, contextGroup.contextGroupCode, "
			+ "contextGroup.contextGroupName) "
			+ "from ContextGroup contextGroup "
			+ "where contextGroup.context = ?1 ")
	List<ContextGroupReadAdapter> findByContext(Operator operator, Pageable page);

	/**
	 * List context groups.
	 * 
	 * @param operatorId
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.core.repository.ContextGroupReadAdapter"
			+ "(contextGroup.id, contextGroup.context.id, contextGroup.contextGroupCode, "
			+ "contextGroup.contextGroupName) "
			+ "from ContextGroup contextGroup "
			+ "where contextGroup.context.id = ?1 ")
	List<ContextGroupReadAdapter> findByContextId(int operatorId, Pageable page);

}
