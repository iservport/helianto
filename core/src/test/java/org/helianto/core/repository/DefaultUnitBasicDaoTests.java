package org.helianto.core.repository;

import org.helianto.core.Unit;
import org.helianto.core.repository.DefaultUnitDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUnitBasicDaoTests extends AbstractHibernateBasicDaoTest<Unit, DefaultUnitDao> {


	@Override
	protected Unit doCreateTarget() {
		return new Unit();
	}

	@Override
	protected DefaultUnitDao doCreateDao() {
		return new DefaultUnitDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select unit from Unit unit ";
	}

}

