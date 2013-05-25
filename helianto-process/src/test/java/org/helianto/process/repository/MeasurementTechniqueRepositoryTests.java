package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.process.domain.MeasurementTechnique;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class MeasurementTechniqueRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<MeasurementTechnique, MeasurementTechniqueRepository> {

	@Autowired
	private MeasurementTechniqueRepository repository;
	
	@Override
	protected MeasurementTechniqueRepository getRepository() {
		return repository;
	}
	
	@Override
	protected MeasurementTechnique getNewTarget() {
		return new MeasurementTechnique(entity, "CODE");
	}
	
	@Override
	protected Serializable getTargetId(MeasurementTechnique target) {
		return target.getId();
	}
	
	@Override
	protected MeasurementTechnique findByKey() {
		return getRepository().findByEntityAndMeasurementTechniqueCode(entity, "CODE");
	}
	
}
