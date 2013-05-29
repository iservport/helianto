package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.inventory.CardType;
import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CardRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<Card, CardRepository> {

	@Autowired
	private CardRepository repository;
	
	@Override
	protected CardRepository getRepository() {
		return repository;
	}
	
	@Autowired
	private CardSetRepository cardSetRepository;
	
	private CardSet cardSet;

	@Override
	protected Card getNewTarget() {
		cardSet = cardSetRepository.save(new CardSet(entity, CardType.TEST, 2));
		Card card = new Card(cardSet,"1");
		return card;
	}

	@Override
	protected Serializable getTargetId(Card target) {
		return target.getId();
	}

	@Override
	protected Card findByKey() {
		return getRepository().findByCardSetAndCardLabel(cardSet, "1");
	}
	
}
