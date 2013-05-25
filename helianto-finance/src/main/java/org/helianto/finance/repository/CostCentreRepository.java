package org.helianto.finance.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.finance.domain.CostCentre;

/**
 * Cost centre repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CostCentreRepository 
	extends FilterRepository<CostCentre, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param costCentreCode
	 */
	CostCentre findByEntityAndCostCentreCode(Entity entity, String costCentreCode);

}