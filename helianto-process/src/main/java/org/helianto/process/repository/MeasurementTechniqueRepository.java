package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.process.domain.MeasurementTechnique;

/**
 * Measurement technique repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface MeasurementTechniqueRepository 
	extends FilterRepository<MeasurementTechnique, Serializable> {

	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param measurementTechniqueCode
	 */
	MeasurementTechnique findByEntityAndMeasurementTechniqueCode(Entity entity, String measurementTechniqueCode);

}
