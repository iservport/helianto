package org.helianto.partner.repository;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.enums.PartnerType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

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
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
		return new Partner(privateEntity, PartnerType.CUSTOMER);
	}

	@Override
	protected Serializable getTargetId(Partner target) {
		return target.getId();
	}

	@Override
	protected Partner findByKey() {
		return getRepository().findByPrivateEntityAndPartnerType(privateEntity, PartnerType.CUSTOMER);
	}
	
}
