package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;

/**
 * Province repository.
 * 
 * @deprecated
 * @see StateRepository
 * @author mauriciofernandesdecastro
 */
public interface ProvinceRepository extends FilterRepository<Province, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param provinceCode
	 */
	Province findByOperatorAndProvinceCode(Operator operator, String provinceCode);
	
}
