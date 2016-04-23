package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.user.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User token repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserTokenRepository extends JpaRepository<UserToken, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param tokenSource
	 * @param principal
	 */
	UserToken findByTokenSourceAndPrincipal(String tokenSource, String principal);

}
