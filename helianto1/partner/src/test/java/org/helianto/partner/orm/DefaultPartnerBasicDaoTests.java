package org.helianto.partner.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.partner.Partner;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerBasicDaoTests extends AbstractBasicDaoTest<Partner, DefaultPartnerDao> {


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
