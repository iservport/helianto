package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Country;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CountryRepositoryTests extends AbstractJpaRepositoryIntegrationTest<Country, CountryRepository> {

	@Autowired
	private CountryRepository repository;
	
	protected CountryRepository getRepository() {
		return repository;
	}
	
	protected Country getNewTarget() {
		return new Country(operator, "CODE");		
	}
	
	protected Serializable getTargetId(Country target) {
		return target.getId();
	}
	
	protected Country findByKey() {
		return getRepository().findByOperatorAndCountryCode(operator, "CODE");
	}
	
}
