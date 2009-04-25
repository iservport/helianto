package org.helianto.core.orm;

import org.helianto.core.UserLog;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserLogBasicDaoTests extends AbstractBasicDaoTest<UserLog, DefaultUserLogDao> {


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

