package org.helianto.core.repository;

import org.helianto.core.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * State repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface StateRepository extends JpaRepository<State, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param contextName
	 * @param stateCode
	 */
	State findByContextNameAndStateCode(String contextName, String stateCode);
	
}
