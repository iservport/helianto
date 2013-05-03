package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.PrivateKey;

/**
 * Private key repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateKeyRepository extends FilterRepository<PrivateKey, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param credential
	 */
	PrivateKey findByCredential(Credential credential);
	
}
