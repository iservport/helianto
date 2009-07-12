package org.helianto.core.repository;

import org.helianto.core.Category;
import org.helianto.core.repository.DefaultCategoryDao;
import org.helianto.core.test.AbstractHibernateBasicDaoTest;
/**
 * <code>CategoryDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCategoryBasicDaoTests extends AbstractHibernateBasicDaoTest<Category, DefaultCategoryDao> {


	@Override
	protected Category doCreateTarget() {
		return new Category();
	}

	@Override
	protected DefaultCategoryDao doCreateDao() {
		return new DefaultCategoryDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select category from Category category ";
	}

}

