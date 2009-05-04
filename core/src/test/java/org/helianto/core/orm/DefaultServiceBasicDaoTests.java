package org.helianto.core.orm;

import org.helianto.core.Service;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultServiceBasicDaoTests extends AbstractBasicDaoTest<Service, DefaultServiceDao> {


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

