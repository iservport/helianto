package org.helianto.partner.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.partner.Partner;
import org.helianto.partner.repository.DefaultPartnerDao;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerBasicDaoTests extends AbstractHibernateBasicDaoTest<Partner, DefaultPartnerDao> {


	@Override
	protected Partner doCreateTarget() {
		return new Partner();
	}

	@Override
	protected DefaultPartnerDao doCreateDao() {
		return new DefaultPartnerDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select partner from Partner partner ";
	}

}

