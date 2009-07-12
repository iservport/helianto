package org.helianto.core.repository;

import org.helianto.core.Operator;
import org.helianto.core.repository.DefaultOperatorDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultOperatorBasicDaoTests extends AbstractHibernateBasicDaoTest<Operator, DefaultOperatorDao> {


	@Override
	protected Operator doCreateTarget() {
		return new Operator();
	}

	@Override
	protected DefaultOperatorDao doCreateDao() {
		return new DefaultOperatorDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select operator from Operator operator ";
	}

}

