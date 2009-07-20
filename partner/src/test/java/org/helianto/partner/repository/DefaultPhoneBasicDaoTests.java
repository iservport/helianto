package org.helianto.partner.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.partner.Phone;
import org.helianto.partner.repository.DefaultPhoneDao;

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

