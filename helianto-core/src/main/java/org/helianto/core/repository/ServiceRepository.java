package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;

/**
 * Service repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ServiceRepository extends FilterRepository<Service, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param serviceName
	 */
	Service findByOperatorAndServiceName(Operator operator, String serviceName);
	
}
