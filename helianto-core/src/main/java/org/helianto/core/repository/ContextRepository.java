package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Context;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Context repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContextRepository extends JpaRepository<Context, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param contextName
	 */
	Context findByContextName(String contextName);
	
}
