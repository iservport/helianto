package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * License repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface LicenseRepository 
	extends JpaRepository<License, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operatorId
	 * @param licenseCode
	 */
	License findByOperator_IdAndLicenseCode(int operatorId, String licenseCode);

}
