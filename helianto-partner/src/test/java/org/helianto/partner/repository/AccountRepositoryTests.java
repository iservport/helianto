package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class AccountRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Account, AccountRepository> {

	@Autowired
	private AccountRepository repository;
	
	@Override
	protected AccountRepository getRepository() {
		return repository;
	}

	@Override
	protected Account getNewTarget() {
		return new Account(entity, "CODE");
	}

	@Override
	protected Serializable getTargetId(Account target) {
		return target.getId();
	}

	@Override
	protected Account findByKey() {
		return getRepository().findByEntityAndAccountCode(entity, "CODE");
	}
	
}
