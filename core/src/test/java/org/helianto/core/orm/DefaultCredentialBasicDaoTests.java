package org.helianto.core.orm;

import org.helianto.core.Credential;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCredentialBasicDaoTests extends AbstractHibernateBasicDaoTest<Credential, DefaultCredentialDao> {


	@Override
	protected Credential doCreateTarget() {
		return new Credential();
	}

	@Override
	protected DefaultCredentialDao doCreateDao() {
		return new DefaultCredentialDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select credential from Credential credential ";
	}

}

