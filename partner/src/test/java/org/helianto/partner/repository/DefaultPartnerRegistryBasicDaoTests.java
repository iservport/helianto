package org.helianto.partner.repository;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.repository.DefaultPartnerRegistryDao;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultPartnerRegistryBasicDaoTests extends AbstractHibernateBasicDaoTest<PartnerRegistry, DefaultPartnerRegistryDao> {


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

