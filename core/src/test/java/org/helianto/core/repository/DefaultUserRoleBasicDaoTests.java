package org.helianto.core.repository;

import org.helianto.core.UserRole;
import org.helianto.core.repository.DefaultUserRoleDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserRoleBasicDaoTests extends AbstractHibernateBasicDaoTest<UserRole, DefaultUserRoleDao> {


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

