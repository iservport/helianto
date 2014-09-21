package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.def.ProviderType;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.IdentitySecurity;

/**
 * Authorization repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentitySecurityRepository 
	extends FilterRepository<IdentitySecurity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 * @param providerType
	 */
	IdentitySecurity findByIdentityAndProviderType(Identity identity, ProviderType providerType);
	
	/**
	 * Find by identity id.
	 * 
	 * @param identityId
	 */
	List<IdentitySecurity> findByIdentityId(int identityId);
	
	/**
	 * Find by natural key.
	 * 
	 * @param identityId
	 * @param providerType
	 */
	IdentitySecurity findByIdentityIdAndProviderType(long identityId, ProviderType providerType);
	
	/**
	 * Find by consumer key.
	 * 
	 * @param consumerKey
	 */
	IdentitySecurity findByConsumerKey(String consumerKey);
	
}
