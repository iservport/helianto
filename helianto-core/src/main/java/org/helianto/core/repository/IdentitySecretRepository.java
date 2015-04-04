package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentitySecret;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Identity secret repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentitySecretRepository extends JpaRepository<IdentitySecret, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 * @param identityKey
	 */
	IdentitySecret findByIdentityAndIdentityKey(Identity identity, String identityKey);
	
	/**
	 * Find by natural key.
	 * 
	 * @param identityId
	 * @param identityKey
	 */
	IdentitySecret findByIdentityIdAndIdentityKey(int identityId, String identityKey);
	
	/**
	 * Find by identity.
	 * 
	 * @param identity
	 * @param page
	 */
	List<IdentitySecret> findByIdentity(Identity identity, Pageable page);
	
	/**
	 * Find by identity id.
	 * 
	 * @param identityId
	 * @param page
	 */
	List<IdentitySecret> findByIdentityId(int identityId, Pageable page);
	
	/**
	 * Find by identityKey.
	 * 
	 * @param identityKey
	 */
	List<IdentitySecret> findByIdentityKey(String identityKey);
	
}
