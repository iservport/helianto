package org.helianto.resource.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.resource.ResourceParameterValue;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterValueBasicDaoTests extends AbstractHibernateBasicDaoTest<ResourceParameterValue, DefaultResourceParameterValueDao> {


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

