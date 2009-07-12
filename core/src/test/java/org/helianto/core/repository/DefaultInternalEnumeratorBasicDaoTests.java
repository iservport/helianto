package org.helianto.core.repository;

import org.helianto.core.InternalEnumerator;
import org.helianto.core.repository.DefaultInternalEnumeratorDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultInternalEnumeratorBasicDaoTests extends AbstractHibernateBasicDaoTest<InternalEnumerator, DefaultInternalEnumeratorDao> {


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

