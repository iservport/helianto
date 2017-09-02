package org.helianto.core.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.City;
import org.helianto.core.domain.State;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CityRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<City, CityRepository> 
{

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private StateRepository stateRepository;
	
	protected CityRepository getRepository() {
		return repository;
	}
	
	protected City getNewTarget() {
		return new City("DEFAULT", "CODE");
	}
	
	protected Serializable getTargetId(City target) {
		return target.getId();
	}
	
	protected City findByKey() {
		return getRepository().findByContextNameAndCityCode("DEFAULT", "CODE");
	}
	
	@Test
	public void stateCode() {
		State state1 = stateRepository.saveAndFlush(new State("DEFAULT", "S1"));
		State state2 = stateRepository.saveAndFlush(new State("DEFAULT", "S2"));
		City city1 = getRepository().saveAndFlush(new City(state1, "C1"));
		City city2 = getRepository().saveAndFlush(new City(state2, "C2"));
		List<City> cityList = getRepository().findByContextNameAndStateStateCode("DEFAULT", "S1", new Sort(Direction.ASC, "cityCode"));
		assertTrue(cityList.contains(city1));
		assertFalse(cityList.contains(city2));
	}
	
}
