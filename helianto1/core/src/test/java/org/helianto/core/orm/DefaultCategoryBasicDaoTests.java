package org.helianto.core.orm;

import org.helianto.core.Category;
/**
 * <code>CategoryDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCategoryBasicDaoTests extends AbstractBasicDaoTest<Category, DefaultCategoryDao> {


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

