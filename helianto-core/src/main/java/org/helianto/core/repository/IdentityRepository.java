package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Identity;

/**
 * Identity repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityRepository extends FilterRepository<Identity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param principal
	 */
	Identity findByPrincipal(String principal);
	
}
