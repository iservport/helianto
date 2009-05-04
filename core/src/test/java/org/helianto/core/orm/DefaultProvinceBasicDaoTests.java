package org.helianto.core.orm;

import org.helianto.core.Province;
import org.helianto.core.test.AbstractBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProvinceBasicDaoTests extends AbstractBasicDaoTest<Province, DefaultProvinceDao> {


	@Override
	protected Province doCreateTarget() {
		return new Province();
	}

	@Override
	protected DefaultProvinceDao doCreateDao() {
		return new DefaultProvinceDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select province from Province province ";
	}

}

