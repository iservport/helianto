package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.CardSet;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCardSetBasicDaoTests extends AbstractBasicDaoTest<CardSet, DefaultCardSetDao> {


	@Override
	protected CardSet doCreateTarget() {
		return new CardSet();
	}

	@Override
	protected DefaultCardSetDao doCreateDao() {
		return new DefaultCardSetDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select cardset from CardSet cardset ";
	}

}

