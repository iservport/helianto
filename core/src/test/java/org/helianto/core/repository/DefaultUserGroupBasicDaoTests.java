package org.helianto.core.repository;

import org.helianto.core.UserGroup;
import org.helianto.core.repository.DefaultUserGroupDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserGroupBasicDaoTests extends AbstractHibernateBasicDaoTest<UserGroup, DefaultUserGroupDao> {


	@Override
	protected UserGroup doCreateTarget() {
		return new UserGroup();
	}

	@Override
	protected DefaultUserGroupDao doCreateDao() {
		return new DefaultUserGroupDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select usergroup from UserGroup usergroup ";
	}

}

