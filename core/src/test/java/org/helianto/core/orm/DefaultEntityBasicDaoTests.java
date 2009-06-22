package org.helianto.core.orm;

import org.helianto.core.Entity;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityBasicDaoTests extends AbstractHibernateBasicDaoTest<Entity, DefaultEntityDao> {


	@Override
	protected Entity doCreateTarget() {
		return new Entity();
	}

	@Override
	protected DefaultEntityDao doCreateDao() {
		return new DefaultEntityDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select entity from Entity entity ";
	}

}

