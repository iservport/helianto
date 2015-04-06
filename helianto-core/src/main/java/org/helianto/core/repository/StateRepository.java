package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.State;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * State repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface StateRepository extends JpaRepository<State, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param context
	 * @param stateCode
	 */
	State findByContextAndStateCode(Operator context, String stateCode);
	
	/**
	 * Find by context.
	 * 
	 * @param context
	 * @param sort
	 */
	List<State> findByContext(Operator context, Sort sort);
	
}
