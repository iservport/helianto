package org.helianto.core.repository;

import org.helianto.core.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Service repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ServiceRepository extends JpaRepository<Service, Serializable> {
	
	/**
	 * Find by natural key.
	 */
	Service findByContextNameAndServiceName(String contextName, String serviceName);
	
}
