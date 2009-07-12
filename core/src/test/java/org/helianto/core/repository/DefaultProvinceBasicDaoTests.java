package org.helianto.core.repository;

import org.helianto.core.Province;
import org.helianto.core.repository.DefaultProvinceDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProvinceBasicDaoTests extends AbstractHibernateBasicDaoTest<Province, DefaultProvinceDao> {


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

