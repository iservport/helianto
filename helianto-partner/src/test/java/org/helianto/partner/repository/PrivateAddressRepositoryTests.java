package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateAddressRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PrivateAddress, PrivateAddressRepository> {

	@Autowired
	private PrivateAddressRepository repository;
	
	@Override
	protected PrivateAddressRepository getRepository() {
		return repository;
	}

    private PrivateEntity privateEntity;
	
	@Autowired
	protected PrivateEntityRepository privateEntityRepository;

	@Override
	protected PrivateAddress getNewTarget() {
		return new PrivateAddress(privateEntity, 100);
	}

	@Override
	protected Serializable getTargetId(PrivateAddress target) {
		return target.getId();
	}

	@Override
	protected PrivateAddress findByKey() {
		return getRepository().findByPrivateEntityAndSequence(privateEntity, 100);
	}
	
	@Override
	protected void setUp() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
	}

}
