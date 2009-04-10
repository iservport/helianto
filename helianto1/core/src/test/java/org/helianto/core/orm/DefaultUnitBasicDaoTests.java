package org.helianto.core.orm;

import org.helianto.core.Unit;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUnitBasicDaoTests extends AbstractBasicDaoTest<Unit, DefaultUnitDao> {


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

