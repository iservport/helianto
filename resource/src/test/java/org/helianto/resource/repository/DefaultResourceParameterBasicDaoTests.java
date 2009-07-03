package org.helianto.resource.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.resource.ResourceParameter;
import org.helianto.resource.repository.DefaultResourceParameterDao;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceParameterBasicDaoTests extends AbstractHibernateBasicDaoTest<ResourceParameter, DefaultResourceParameterDao> {


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

