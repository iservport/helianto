package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.def.ProviderType;
import org.helianto.core.domain.ConnectionData;
import org.helianto.core.domain.Identity;

/**
 * Authorization repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ConnectionDataRepository 
	extends FilterRepository<ConnectionData, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param identity
	 * @param providerType
	 */
	ConnectionData findByIdentityAndProviderType(Identity identity, ProviderType providerType);
	
	/**
	 * Find by identity id.
	 * 
	 * @param identityId
	 */
	List<ConnectionData> findByIdentityId(long identityId);
	
	/**
	 * Find by natural key.
	 * 
	 * @param identityId
	 * @param providerType
	 */
	ConnectionData findByIdentityIdAndProviderType(long identityId, ProviderType providerType);
	
	/**
	 * Find by consumer key.
	 * 
	 * @param consumerKey
	 */
	ConnectionData findByConsumerKey(String consumerKey);
	
}
