package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Province repository.
 * 
 * @deprecated
 * @see StateRepository
 * @author mauriciofernandesdecastro
 */
public interface ProvinceRepository extends JpaRepository<Province, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param provinceCode
	 */
	Province findByOperatorAndProvinceCode(Operator operator, String provinceCode);
	
}
