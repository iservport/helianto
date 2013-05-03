package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Province;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProvinceRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Province, ProvinceRepository> {

	@Autowired
	private ProvinceRepository repository;
	
	protected ProvinceRepository getRepository() {
		return repository;
	}
	
	protected Province getNewTarget() {
		return new Province(operator, "CODE");		
	}
	
	protected Serializable getTargetId(Province target) {
		return target.getId();
	}
	
	protected Province findByKey() {
		return getRepository().findByOperatorAndProvinceCode(operator, "CODE");
	}
	
}
