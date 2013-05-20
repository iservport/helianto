package org.helianto.core.data;

import java.io.Serializable;

import org.helianto.core.filter.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Shared behavior.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 * @param <ID>
 * @param <F>
 */
@NoRepositoryBean
public interface FilterRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * Add find by filter.
	 * 
	 * @param filter
	 */
	Iterable<T> find(Filter filter);
	
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
