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

	Iterable<T> find(Filter filter);

}
