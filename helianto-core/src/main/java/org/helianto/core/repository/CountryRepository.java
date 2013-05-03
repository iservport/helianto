package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Country;
import org.helianto.core.domain.Operator;

/**
 * Country repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CountryRepository extends FilterRepository<Country, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param countryCode
	 */
	Country findByOperatorAndCountryCode(Operator operator, String countryCode);
	
}
