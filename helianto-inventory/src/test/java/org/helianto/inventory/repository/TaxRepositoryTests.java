package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.Tax;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class TaxRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Tax, TaxRepository> {

	@Autowired
	private TaxRepository repository;
	
	@Override
	protected TaxRepository getRepository() {
		return repository;
	}
	
	@Autowired
	private ProcessAgreementRepository processAgreementRepository;
	
	@Autowired
	private KeyTypeRepository keyTypeRepository;
	
	private ProcessAgreement processAgreement;
	private KeyType keyType;

	@Override
	protected Tax getNewTarget() {
		processAgreement = processAgreementRepository.save(new ProcessAgreement(entity, 1));
		keyType = keyTypeRepository.save(new KeyType(operator, "CODE"));
		return new Tax(processAgreement, keyType);
	}

	@Override
	protected Serializable getTargetId(Tax target) {
		return target.getId();
	}

	@Override
	protected Tax findByKey() {
		return getRepository().findByProcessAgreementAndKeyType(processAgreement, keyType);
	}
	
}
