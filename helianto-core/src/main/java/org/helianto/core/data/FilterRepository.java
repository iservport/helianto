package org.helianto.core.data;

import java.io.Serializable;

import org.helianto.core.filter.Filter;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Shared behavior.
 * 
 * <p>
 * This interface will replaced by QueryRepository from the helianto-query project.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 * @param <ID>
 * @param <F>
 * 
 * @deprecated
 */
@NoRepositoryBean
public interface FilterRepository<T, ID extends Serializable> extends QueryRepository<T, ID> {

	/**
	 * Add find by filter.
	 * 
	 * @param filter
	 */
	Iterable<T> find(Filter filter);
	
	/**
	 * Add find by filter.
	 * 
	 * @param filter
	 */
	Iterable<T> find(Filter filter, Pageable pageable);
	
	/**
	 * Add count by filter.
	 * 
	 * @param filter
	 */
	long count(Filter filter);
	
	/**
	 * Add refresh.
	 * 
	 * @param entity
	 */
	void refresh(T entity);

}
