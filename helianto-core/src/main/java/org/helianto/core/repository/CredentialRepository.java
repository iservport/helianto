package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;

/**
 * Credential repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CredentialRepository extends FilterRepository<Credential, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 */
	Credential findByIdentity(Identity identity);
	
	/**
	 * Find by identity principal.
	 * 
	 * @param principal
	 */
	List<Credential> findByIdentityPrincipal(String principal);
	
}
