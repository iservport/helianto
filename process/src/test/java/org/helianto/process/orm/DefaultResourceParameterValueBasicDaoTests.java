package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.ResourceParameterValue;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterValueBasicDaoTests extends AbstractBasicDaoTest<ResourceParameterValue, DefaultResourceParameterValueDao> {


	@Override
	protected ResourceParameterValue doCreateTarget() {
		return new ResourceParameterValue();
	}

	@Override
	protected DefaultResourceParameterValueDao doCreateDao() {
		return new DefaultResourceParameterValueDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select resourceparametervalue from ResourceParameterValue resourceparametervalue ";
	}

}

