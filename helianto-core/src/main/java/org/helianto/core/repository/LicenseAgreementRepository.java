package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.LicenseAgreement;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * License agreement repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface LicenseAgreementRepository 
	extends QueryRepository<LicenseAgreement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entityId
	 * @param licenseId
	 */
	LicenseAgreement findByEntity_IdAndLicense_Id(int entityId, int licenseId);

	@Query("select licenseAgreement "
			+ "from LicenseAgreement licenseAgreement "
			+ "where licenseAgreement.entity.id = ?1 "
			+ "and licenseAgreement.resolution = 'A' "
			+ "order by licenseAgreement.issueDate DESC ")
	List<LicenseAgreement> findActiveByEntity_IdOrdered(int entityId);

}
