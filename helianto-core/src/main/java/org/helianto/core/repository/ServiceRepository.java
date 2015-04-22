package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Service repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ServiceRepository extends JpaRepository<Service, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param serviceName
	 */
	Service findByOperatorAndServiceName(Operator operator, String serviceName);
	
	/**
	 * Find by operator.
	 * 
	 * @param operator
	 */
	List<Service> findByOperator(Operator operator);
	
}
