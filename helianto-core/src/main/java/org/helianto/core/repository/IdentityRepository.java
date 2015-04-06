package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Identity repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityRepository extends JpaRepository<Identity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param principal
	 */
	Identity findByPrincipal(String principal);
	
}
