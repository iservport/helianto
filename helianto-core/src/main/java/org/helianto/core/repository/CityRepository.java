package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.City;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.State;
import org.springframework.data.domain.Sort;

/**
 * City repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CityRepository extends FilterRepository<City, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param context
	 * @param stateCode
	 */
	City findByContextAndCityCode(Operator context, String cityCode);
	
	/**
	 * Find by state code.
	 * 
	 * @param context
	 * @param state
	 * @param sort
	 */
	List<City> findByContextAndStateStateCode(Operator context, String stateCode, Sort sort);
	
	/**
	 * Find by state.
	 * 
	 * @param state
	 * @param sort
	 */
	List<City> findByState(State state, Sort sort);
	
}
