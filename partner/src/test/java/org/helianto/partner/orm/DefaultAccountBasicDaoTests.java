package org.helianto.partner.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.partner.Account;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultAccountBasicDaoTests extends AbstractBasicDaoTest<Account, DefaultAccountDao> {


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

