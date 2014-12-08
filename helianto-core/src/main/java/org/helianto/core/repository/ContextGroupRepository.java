package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.ContextGroup;
import org.helianto.core.domain.Operator;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * ContextGroup repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextGroupRepository 
	extends QueryRepository<ContextGroup, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param contextId
	 * @param contextGroupCode
	 * @return
	 */
	@Query("select contextGroup "
			+ "from ContextGroup contextGroup "
			+ "where contextGroup.context.id = ?1 "
			+ "and contextGroup.contextGroupCode = ?2 ")
	public ContextGroup findByContextIdAndContextGroupCode(int contextId, String contextGroupCode);
	
	/**
	 * List context groups.
	 * 
	 * @param operator
	 * @param page
	 * @return
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
	 * @return
	 */
	@Query("select new "
			+ "org.helianto.core.repository.ContextGroupReadAdapter"
			+ "(contextGroup.id, contextGroup.context.id, contextGroup.contextGroupCode, "
			+ "contextGroup.contextGroupName) "
			+ "from ContextGroup contextGroup "
			+ "where contextGroup.context.id = ?1 ")
	List<ContextGroupReadAdapter> findByContextId(int operatorId, Pageable page);

}
