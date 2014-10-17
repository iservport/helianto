package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentityAlias;
import org.springframework.data.domain.Pageable;

/**
 * Identity repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityAliasRepository extends FilterRepository<IdentityAlias, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 * @param identityAlias
	 */
	IdentityAlias findByIdentityAndIdentityAlias(Identity identity, String identityAlias);
	
	/**
	 * Find by natural key.
	 * 
	 * @param identityId
	 * @param identityAlias
	 */
	IdentityAlias findByIdentityIdAndIdentityAlias(int identityId, String identityAlias);
	
	/**
	 * Find by identity.
	 * 
	 * @param identity
	 * @param page
	 */
	List<IdentityAlias> findByIdentity(Identity identity, Pageable page);
	
	/**
	 * Find by identity id.
	 * 
	 * @param identityId
	 * @param page
	 */
	List<IdentityAlias> findByIdentityId(int identityId, Pageable page);
	
}
