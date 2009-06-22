package org.helianto.process.orm;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.process.CardSet;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCardSetBasicDaoTests extends AbstractHibernateBasicDaoTest<CardSet, DefaultCardSetDao> {


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

