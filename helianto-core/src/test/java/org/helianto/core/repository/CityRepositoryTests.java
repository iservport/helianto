package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.City;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CityRepositoryTests extends AbstractJpaRepositoryIntegrationTest<City, CityRepository> {

	@Autowired
	private CityRepository repository;
	
	protected CityRepository getRepository() {
		return repository;
	}
	
	protected City getNewTarget() {
		return new City(operator, "CODE");		
	}
	
	protected Serializable getTargetId(City target) {
		return target.getId();
	}
	
	protected City findByKey() {
		return getRepository().findByContextAndCityCode(operator, "CODE");
	}
	
}
