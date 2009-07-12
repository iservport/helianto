package org.helianto.core.repository;

import org.helianto.core.UserLog;
import org.helianto.core.repository.DefaultUserLogDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserLogBasicDaoTests extends AbstractHibernateBasicDaoTest<UserLog, DefaultUserLogDao> {


	@Override
	protected UserLog doCreateTarget() {
		return new UserLog();
	}

	@Override
	protected DefaultUserLogDao doCreateDao() {
		return new DefaultUserLogDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select userlog from UserLog userlog ";
	}

}

