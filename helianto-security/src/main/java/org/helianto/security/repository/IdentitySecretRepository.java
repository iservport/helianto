package org.helianto.security.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Identity;
import org.helianto.security.domain.IdentitySecret;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Identity secret repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentitySecretRepository extends JpaRepository<IdentitySecret, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identityKey
	 */
	IdentitySecret findByIdentityKey(String identityKey);
	
	/**
	 * Find by principal or email.
	 * 
	 * @param identityKey
	 */
	@Query("select s_ from IdentitySecret s_ where s_.identity.principal = ?1 or s_.identity.email = ?1 ")
	IdentitySecret findByIdentityKeyOrEmail(String identityKey);
	
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
		
}
