package org.helianto.core.orm;

import org.helianto.core.KeyType;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultKeyTypeBasicDaoTests extends AbstractBasicDaoTest<KeyType, DefaultKeyTypeDao> {


	@Override
	protected KeyType doCreateTarget() {
		return new KeyType();
	}

	@Override
	protected DefaultKeyTypeDao doCreateDao() {
		return new DefaultKeyTypeDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select keytype from KeyType keytype ";
	}

}

