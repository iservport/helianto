package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartnerPhoneRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PartnerPhone, PartnerPhoneRepository> {

	@Autowired
	private PartnerPhoneRepository repository;
	
	@Override
	protected PartnerPhoneRepository getRepository() {
		return repository;
	}

    private PrivateEntity privateEntity;
	
	@Autowired
	protected PrivateEntityRepository privateEntityRepository;

	@Override
	protected PartnerPhone getNewTarget() {
		return new PartnerPhone(privateEntity, 100);
	}

	@Override
	protected Serializable getTargetId(PartnerPhone target) {
		return target.getId();
	}

	@Override
	protected PartnerPhone findByKey() {
		return getRepository().findByPrivateEntityAndSequence(privateEntity, 100);
	}
	
	@Override
	protected void setUp() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
	}

}
