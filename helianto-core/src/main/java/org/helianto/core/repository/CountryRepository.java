package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Country;
import org.helianto.core.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Country repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CountryRepository extends JpaRepository<Country, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param countryCode
	 */
	Country findByOperatorAndCountryCode(Operator operator, String countryCode);
	
}
