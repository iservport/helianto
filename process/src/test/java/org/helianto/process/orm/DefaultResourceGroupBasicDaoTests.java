package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.ResourceGroup;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultResourceGroupBasicDaoTests extends AbstractBasicDaoTest<ResourceGroup, DefaultResourceGroupDao> {


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

