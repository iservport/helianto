package org.helianto.core.orm;

import org.helianto.core.UserRole;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserRoleBasicDaoTests extends AbstractBasicDaoTest<UserRole, DefaultUserRoleDao> {


	@Override
	protected UserRole doCreateTarget() {
		return new UserRole();
	}

	@Override
	protected DefaultUserRoleDao doCreateDao() {
		return new DefaultUserRoleDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select userrole from UserRole userrole ";
	}

}

