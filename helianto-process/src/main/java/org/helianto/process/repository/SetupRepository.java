package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.process.domain.Setup;
import org.helianto.process.domain.Operation;
import org.helianto.resource.domain.ResourceGroup;

/**
 * Set up repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface SetupRepository 
	extends FilterRepository<Setup, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param operation
	 * @param resource
	 */
	Setup findByOperationAndResource(Operation operation, ResourceGroup resource);

}
