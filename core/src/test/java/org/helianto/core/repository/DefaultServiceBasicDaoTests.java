package org.helianto.core.repository;

import org.helianto.core.Service;
import org.helianto.core.repository.DefaultServiceDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultServiceBasicDaoTests extends AbstractHibernateBasicDaoTest<Service, DefaultServiceDao> {


	@Override
	protected Service doCreateTarget() {
		return new Service();
	}

	@Override
	protected DefaultServiceDao doCreateDao() {
		return new DefaultServiceDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select service from Service service ";
	}

}

