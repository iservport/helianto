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

import org.helianto.core.UserLog;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.test.UserLogTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUserLogDaoIntegrationTests extends AbstractHibernateIntegrationTest {
	
	public DefaultUserLogDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		UserLog userLog = UserLogTestSupport.createUserLog();
		userLogDao.persist(userLog);
		assertEquals(userLog, userLogDao.findUnique(userLog.getUser(), userLog.getLastEvent()));
	}

    //- collabs

    private BasicDao<UserLog> userLogDao;
    
    public void setUserLogDao(BasicDao<UserLog> userLogDao) {
        this.userLogDao = userLogDao;
    }
    
}