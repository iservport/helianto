package org.helianto.finance.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.repository.EntityRepository;
import org.helianto.finance.domain.FinanceAccount;
import org.helianto.finance.test.FinanceRepositoryIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.repository.PartnerRepository;
import org.helianto.partner.repository.PrivateEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FinanceAccountRepositoryTests 
	extends FinanceRepositoryIntegrationTest<FinanceAccount, FinanceAccountRepository> {

	@Autowired
	private FinanceAccountRepository repository;
	
	@Override
	protected FinanceAccountRepository getRepository() {
		return repository;
	}
	
	private Entity entity;
	private PrivateEntity privateEntity;
	private Partner partner;
	
	@Autowired
	private EntityRepository entityRepository;

	@Autowired
	private PrivateEntityRepository privateEntityRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	protected FinanceAccount getNewTarget() {
		return new FinanceAccount(partner, "CODE");
	}

	@Override
	protected Serializable getTargetId(FinanceAccount target) {
		return target.getId();
	}

	@Override
	protected FinanceAccount findByKey() {
		return getRepository().findByPartnerAndCompositeAccountCode(partner, "CODE");
	}
	
	@Override
	protected void setUp() {
		entity = entityRepository.save(new Entity());
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity));
		partner = partnerRepository.save(new Partner(privateEntity));
	}
	
}
