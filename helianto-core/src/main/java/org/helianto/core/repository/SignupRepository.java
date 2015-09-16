package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Signup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Signup repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface SignupRepository extends JpaRepository<Signup, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param contextId
	 * @param principal
	 */
	Signup findByContextIdAndPrincipal(int contextId, String principal);

}
