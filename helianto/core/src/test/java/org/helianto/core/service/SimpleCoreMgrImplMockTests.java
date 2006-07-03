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

package org.helianto.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;
import org.helianto.core.security.SecureUserDetails;
import org.helianto.core.security.UserDetailsAdapter;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class SimpleCoreMgrImplMockTests extends TestCase {

    public void testPersistPersonalDataSuccess() {
        
        Identity identity = new Identity();
        Credential credential = new Credential();
        User[] users = createUsersWithSameIdentity(identity, 1);

        UserLog userLog = new UserLog();
        userLog.setUser(users[0]);
        SecureUserDetails secureUser = new UserDetailsAdapter(userLog, credential);
        
        mock.persistCredential(secureUser.getCredential());
        replay(mock);
        simpleCoreMgr.persistPersonalData(secureUser);
        verify(mock);
        
    }

    public void testSwitchAuthorizedUserSuccess() {
        
        Identity identity = new Identity();
        Credential credential = new Credential();
        User[] users = createUsersWithSameIdentity(identity, 2);
        
        UserLog userLog = new UserLog();
        userLog.setUser(users[0]);
        UserDetailsAdapter secureUser = new UserDetailsAdapter(userLog, credential);
        
        expect(mock.createAndPersistUserLog(users[1]))
            .andReturn(userLog);
        replay(mock);
        assertTrue(simpleCoreMgr.switchAuthorizedUser(secureUser, "ENT1"));
        reset(mock);
        
    }
    
    private SimpleCoreMgrImpl simpleCoreMgr;
    private UserDao mock;
    
    // utility fields
    private final Log logger = LogFactory.getLog(getClass());
    
    // setup
    
    public void setUp() {
        mock = createMock(UserDao.class);
        simpleCoreMgr = new SimpleCoreMgrImpl();
        simpleCoreMgr.setUserDao(mock);
    }
    
    // helpers
    
    public User[] createUsersWithSameIdentity(Identity identity, int size) {
        User[] users = new User[size];
        Entity[] entities = new Entity[size];
        for (int i = 0; i < size; i++) {
            users[i] = new User();
            entities[i] = new Entity();
            entities[i].setAlias("ENT"+i);
            users[i].setIdentity(identity);
            users[i].setEntity(entities[i]);
            identity.getUsers().add(users[i]);
            if (logger.isDebugEnabled()) {
                logger.debug("Test user "+users[i]+" "+users[i].getIdentity()+" "+users[i].getEntity());
            }
        }
        return users;
    }

}
