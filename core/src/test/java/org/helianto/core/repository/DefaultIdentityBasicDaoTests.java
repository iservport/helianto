package org.helianto.core.repository;

import org.helianto.core.Identity;
import org.helianto.core.repository.DefaultIdentityDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentityBasicDaoTests extends AbstractHibernateBasicDaoTest<Identity, DefaultIdentityDao> {


	@Override
	protected Identity doCreateTarget() {
		return new Identity();
	}

	@Override
	protected DefaultIdentityDao doCreateDao() {
		return new DefaultIdentityDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select identity from Identity identity ";
	}

}

