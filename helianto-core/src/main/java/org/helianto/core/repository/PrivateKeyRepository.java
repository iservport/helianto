package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.PrivateKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Private key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateKeyRepository extends JpaRepository<PrivateKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param credential
	 */
	PrivateKey findByCredential(Credential credential);
	
}
