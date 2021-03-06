package org.helianto.core.repository;

import org.helianto.core.domain.City;
import org.helianto.core.domain.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * City repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CityRepository extends JpaRepository<City, Serializable> {
	
	/**
	 * Find by natural key.
	 */
	City findByContextNameAndCityCode(String contextName, String cityCode);
	
	/**
	 * Find by state code.
	 */
	List<City> findByContextNameAndStateStateCode(String contextName, String stateCode, Sort sort);
	
	/**
	 * Find by state.
	 * 
	 * @param state
	 * @param sort
	 */
	List<City> findByState(State state, Sort sort);
	
	/**
	 * Search cities.
	 */
	List<City> findByCityCodeContainingOrCityNameContainingOrStateStateNameContainingOrStateStateCodeContaining
				(String cityCode, String cityName, String stateName, String stateCode , Pageable pageable);
	
	/**
	 * Find by name and state.
	 */
	@Query("select city "
			+ "from City city "
			+ "where lower(city.cityName) like lower(?1) "
			+ "AND city.state.stateCode = ?2 ")
	List<City> findByCityNameAndStateCode(String cityName, String stateCode, Pageable pageable);
	
	/**
	 * Find by state id.
	 * 
	 * @param stateId
	 */
	List<City> findByState_Id(int stateId);
	
}
