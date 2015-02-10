package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.License;
import org.helianto.query.data.QueryRepository;

/**
 * License repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface LicenseRepository 
	extends QueryRepository<License, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operatorId
	 * @param licenseCode
	 */
	License findByOperator_IdAndLicenseCode(int operatorId, String licenseCode);

}
