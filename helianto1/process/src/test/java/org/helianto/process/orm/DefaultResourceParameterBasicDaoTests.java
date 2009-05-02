package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.ResourceParameter;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterBasicDaoTests extends AbstractBasicDaoTest<ResourceParameter, DefaultResourceParameterDao> {


	@Override
	protected ResourceParameter doCreateTarget() {
		return new ResourceParameter();
	}

	@Override
	protected DefaultResourceParameterDao doCreateDao() {
		return new DefaultResourceParameterDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select resourceparameter from ResourceParameter resourceparameter ";
	}

}

