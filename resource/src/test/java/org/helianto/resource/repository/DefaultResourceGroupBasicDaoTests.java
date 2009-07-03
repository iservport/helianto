package org.helianto.resource.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.repository.DefaultResourceGroupDao;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceGroupBasicDaoTests extends AbstractHibernateBasicDaoTest<ResourceGroup, DefaultResourceGroupDao> {


	@Override
	protected ResourceGroup doCreateTarget() {
		return new ResourceGroup();
	}

	@Override
	protected DefaultResourceGroupDao doCreateDao() {
		return new DefaultResourceGroupDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select resourcegroup from ResourceGroup resourcegroup ";
	}

}

