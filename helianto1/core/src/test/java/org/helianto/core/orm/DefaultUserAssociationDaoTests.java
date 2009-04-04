package org.helianto.core.orm;

import org.helianto.core.UserAssociation;
import org.helianto.core.test.AbstractBasicDaoTest;
/**
 * <code>UserAssociationDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserAssociationDaoTests extends AbstractBasicDaoTest<UserAssociation, DefaultUserAssociationDao> {


	@Override
	protected UserAssociation doCreateTarget() {
		return new UserAssociation();
	}

	@Override
	protected DefaultUserAssociationDao doCreateDao() {
		return new DefaultUserAssociationDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select userassociation from UserAssociation userassociation ";
	}

}

