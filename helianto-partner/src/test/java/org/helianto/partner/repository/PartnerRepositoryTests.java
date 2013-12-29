package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartnerRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Partner, PartnerRepository> {

	@Autowired
	private PartnerRepository repository;
	
	@Override
	protected PartnerRepository getRepository() {
		return repository;
	}

    private PrivateEntity privateEntity;
	
	@Autowired
	protected PrivateEntityRepository privateEntityRepository;

	@Override
	protected Partner getNewTarget() {
		return new Partner(privateEntity);
	}

	@Override
	protected Serializable getTargetId(Partner target) {
		return target.getId();
	}

	@Override
	protected Partner findByKey() {
		return getRepository().findByPrivateEntityAndType(privateEntity, 'P');
	}
	
	@Override
	protected void setUp() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
	}

}
