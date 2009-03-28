package org.helianto.core.orm;

import org.helianto.core.Entity;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultEntityBasicDaoTests extends AbstractBasicDaoTest<Entity, DefaultEntityDao> {


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

