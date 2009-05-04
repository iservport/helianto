package org.helianto.partner.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.partner.PartnerRegistry;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerRegistryBasicDaoTests extends AbstractBasicDaoTest<PartnerRegistry, DefaultPartnerRegistryDao> {


	@Override
	protected PartnerRegistry doCreateTarget() {
		return new PartnerRegistry();
	}

	@Override
	protected DefaultPartnerRegistryDao doCreateDao() {
		return new DefaultPartnerRegistryDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select partnerregistry from PartnerRegistry partnerregistry ";
	}

}

