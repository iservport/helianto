package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.ParameterGroup;

/**
 * Parameter group repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ParameterGroupRepository extends FilterRepository<ParameterGroup, Serializable> {
	
	/**
	 * Find by key.
	 * 
	 * @param operator
	 * @param parameterName
	 */
	ParameterGroup findByOperatorAndParameterName(Operator operator, String parameterName);

}
