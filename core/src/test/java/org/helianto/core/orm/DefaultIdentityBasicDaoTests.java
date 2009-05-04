package org.helianto.core.orm;

import org.helianto.core.Identity;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultIdentityBasicDaoTests extends AbstractBasicDaoTest<Identity, DefaultIdentityDao> {


	@Override
	protected Identity doCreateTarget() {
		return new Identity();
	}

	@Override
	protected DefaultIdentityDao doCreateDao() {
		return new DefaultIdentityDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select identity from Identity identity ";
	}

}

