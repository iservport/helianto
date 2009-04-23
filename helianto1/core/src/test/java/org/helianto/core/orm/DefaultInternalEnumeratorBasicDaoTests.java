package org.helianto.core.orm;

import org.helianto.core.InternalEnumerator;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultInternalEnumeratorBasicDaoTests extends AbstractBasicDaoTest<InternalEnumerator, DefaultInternalEnumeratorDao> {


	@Override
	protected InternalEnumerator doCreateTarget() {
		return new InternalEnumerator();
	}

	@Override
	protected DefaultInternalEnumeratorDao doCreateDao() {
		return new DefaultInternalEnumeratorDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select internalenumerator from InternalEnumerator internalenumerator ";
	}

}

