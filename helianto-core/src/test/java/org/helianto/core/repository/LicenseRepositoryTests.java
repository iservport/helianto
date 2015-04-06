package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.License;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
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
public class LicenseRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<License, LicenseRepository> 
{
	
	@Autowired
	private LicenseRepository repository;

	@Override
	protected LicenseRepository getRepository() {
		return repository;
	}

	@Override
	protected License getNewTarget() {
		return new License(operator, "LIC");
	}

	@Override
	protected Serializable getTargetId(License target) {
		return target.getId();
	}

	@Override
	protected License findByKey() {
		return repository.findByOperator_IdAndLicenseCode(operator.getId(), "LIC");
	}

}
