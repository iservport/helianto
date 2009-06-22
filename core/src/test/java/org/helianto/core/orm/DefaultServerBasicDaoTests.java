package org.helianto.core.orm;

import org.helianto.core.Server;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultServerBasicDaoTests extends AbstractHibernateBasicDaoTest<Server, DefaultServerDao> {


	@Override
	protected Server doCreateTarget() {
		return new Server();
	}

	@Override
	protected DefaultServerDao doCreateDao() {
		return new DefaultServerDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select server from Server server ";
	}

}

