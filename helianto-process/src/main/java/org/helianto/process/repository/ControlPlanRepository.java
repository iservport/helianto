package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.process.domain.ControlPlan;

/**
 * Control plan repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ControlPlanRepository 
	extends FilterRepository<ControlPlan, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param docCode
	 */
	ControlPlan findByEntityAndDocCode(Entity entity, String docCode);

}
