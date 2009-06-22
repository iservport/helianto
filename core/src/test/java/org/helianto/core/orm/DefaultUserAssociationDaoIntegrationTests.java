/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.helianto.core.orm;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.test.AbstractDaoIntegrationTest;
import org.helianto.core.test.UserAssociationTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserAssociationDaoIntegrationTests extends AbstractDaoIntegrationTest {
	
	@Override
	public void testFindUnique() {
		UserAssociation userAssociation = UserAssociationTestSupport.createUserAssociation();
		userGroupDao.persist(userAssociation.getParent());
		userAssociationDao.persist(userAssociation);
		assertEquals(userAssociation, userAssociationDao.findUnique(userAssociation.getParent(), userAssociation.getChild()));
	}

    //- collabs

    private BasicDao<UserAssociation> userAssociationDao;
    private BasicDao<UserGroup> userGroupDao;
    
    @Resource(name="userAssociationDao")
    public void setUserAssociationDao(BasicDao<UserAssociation> userAssociationDao) {
        this.userAssociationDao = userAssociationDao;
    }
    
    @Resource(name="userGroupDao")
    public void setUserGroupDao(BasicDao<UserGroup> userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
    
}
