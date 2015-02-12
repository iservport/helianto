package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractQueryRepositoryIntegrationTest;
import org.helianto.order.domain.Token;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TokenRepositoryTests extends
		AbstractQueryRepositoryIntegrationTest<Token, TokenRepository> {

	@Autowired
	private TokenRepository repository;
	
	@Override
	protected TokenRepository getRepository() {
		return repository;
	}

	@Override
	protected Token getNewTarget() {
		return new Token(entity, "tokenLabel");
	}

	@Override
	protected Serializable getTargetId(Token target) {
		return target.getId();
	}

	@Override
	protected Token findByKey() {
		return getRepository().findByEntityAndTokenLabel(entity, "tokenLabel");
	}

}
