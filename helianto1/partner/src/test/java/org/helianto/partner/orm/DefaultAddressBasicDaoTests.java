package org.helianto.partner.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.partner.Address;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultAddressBasicDaoTests extends AbstractBasicDaoTest<Address, DefaultAddressDao> {


	@Override
	protected Address doCreateTarget() {
		return new Address();
	}

	@Override
	protected DefaultAddressDao doCreateDao() {
		return new DefaultAddressDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select address from Address address ";
	}

}

