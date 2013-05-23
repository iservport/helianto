package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PartnerKeyRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<PartnerKey, PartnerKeyRepository> {

	@Autowired
	private PartnerKeyRepository repository;
	
	@Override
	protected PartnerKeyRepository getRepository() {
		return repository;
	}

    private PrivateEntity privateEntity;
    private Partner partner;
    private KeyType keyType;
	
	@Autowired
	protected PrivateEntityRepository privateEntityRepository;

	@Autowired
	protected PartnerRepository partnerRepository;

	@Autowired
	protected KeyTypeRepository keyTypeRepository;

	@Override
	protected PartnerKey getNewTarget() {
		return new PartnerKey(partner, keyType);
	}

	@Override
	protected Serializable getTargetId(PartnerKey target) {
		return target.getId();
	}

	@Override
	protected PartnerKey findByKey() {
		return getRepository().findByPartnerAndKeyType(partner, keyType);
	}
	
	@Override
	protected void setUp() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
		partner = partnerRepository.save(new Partner(privateEntity));
		keyType = keyTypeRepository.save(new KeyType(operator, "CODE"));
	}

}
