package org.helianto.core.orm;

import org.helianto.core.Server;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultServerBasicDaoTests extends AbstractBasicDaoTest<Server, DefaultServerDao> {


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

