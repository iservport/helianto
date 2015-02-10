package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.License;
import org.helianto.core.domain.LicenseAgreement;
import org.helianto.core.test.AbstractQueryRepositoryIntegrationTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LicenseAgreementRepositoryTests 
	extends AbstractQueryRepositoryIntegrationTest<LicenseAgreement, LicenseAgreementRepository> 
{
	
	@Autowired
	private LicenseAgreementRepository repository;

	@Autowired
	private LicenseRepository licenseRepository;

	@Override
	protected LicenseAgreementRepository getRepository() {
		return repository;
	}

	private License license;
	
	@Override
	protected LicenseAgreement getNewTarget() {
		license = licenseRepository.saveAndFlush(new License(operator, "LIC"));
		return new LicenseAgreement(entity, license);
	}

	@Override
	protected Serializable getTargetId(LicenseAgreement target) {
		return target.getId();
	}

	@Override
	protected LicenseAgreement findByKey() {
		return repository.findByEntity_IdAndLicense_Id(entity.getId(), license.getId());
	}

}
