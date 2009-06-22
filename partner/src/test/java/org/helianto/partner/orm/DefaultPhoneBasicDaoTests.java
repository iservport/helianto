package org.helianto.partner.orm;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.partner.Phone;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPhoneBasicDaoTests extends AbstractHibernateBasicDaoTest<Phone, DefaultPhoneDao> {


	@Override
	protected Phone doCreateTarget() {
		return new Phone();
	}

	@Override
	protected DefaultPhoneDao doCreateDao() {
		return new DefaultPhoneDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select phone from Phone phone ";
	}

}

