package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.Setup;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultSetupBasicDaoTests extends AbstractBasicDaoTest<Setup, DefaultSetupDao> {


	@Override
	protected Setup doCreateTarget() {
		return new Setup();
	}

	@Override
	protected DefaultSetupDao doCreateDao() {
		return new DefaultSetupDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select setup from Setup setup ";
	}

}

