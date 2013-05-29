package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.inventory.domain.CardSet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CardSetRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<CardSet, CardSetRepository> {

	@Autowired
	private CardSetRepository repository;
	
	@Override
	protected CardSetRepository getRepository() {
		return repository;
	}

	@Override
	protected CardSet getNewTarget() {
		return new CardSet(entity, 1);
	}

	@Override
	protected Serializable getTargetId(CardSet target) {
		return target.getId();
	}

	@Override
	protected CardSet findByKey() {
		return getRepository().findByEntityAndInternalNumber(entity, 1);
	}
	
}
