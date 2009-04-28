package org.helianto.partner.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.partner.PartnerKey;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerKeyBasicDaoTests extends AbstractBasicDaoTest<PartnerKey, DefaultPartnerKeyDao> {


	@Override
	protected PartnerKey doCreateTarget() {
		return new PartnerKey();
	}

	@Override
	protected DefaultPartnerKeyDao doCreateDao() {
		return new DefaultPartnerKeyDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select partnerkey from PartnerKey partnerkey ";
	}

}

