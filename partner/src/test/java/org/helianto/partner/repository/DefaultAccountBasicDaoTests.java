package org.helianto.partner.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.partner.Account;
import org.helianto.partner.repository.DefaultAccountDao;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultAccountBasicDaoTests extends AbstractHibernateBasicDaoTest<Account, DefaultAccountDao> {


	@Override
	protected Account doCreateTarget() {
		return new Account();
	}

	@Override
	protected DefaultAccountDao doCreateDao() {
		return new DefaultAccountDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select account from Account account ";
	}

}

