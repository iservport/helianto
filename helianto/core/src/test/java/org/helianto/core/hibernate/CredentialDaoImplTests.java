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

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.creation.UserCreatorImpl;
import org.helianto.core.dao.UserDao;
import org.helianto.core.junit.AbstractIntegrationTest;

public class CredentialDaoImplTests extends AbstractIntegrationTest {
    
    private UserDao userDao;
    
    private Identity createAndPersistIdentity(String principal, String optionalAlias ) {
        Identity identity = UserCreatorImpl.identityFactory(principal, optionalAlias);
        userDao.persistIdentity(identity);
        return identity;
    }

    public void testPersistIdentity() {
        //write
        String principal = generateKey(20);
        String optionalAlias = generateKey(20);
        Identity identity = createAndPersistIdentity(principal, optionalAlias);
        hibernateTemplate.flush();
        //read
        Identity found = userDao.findIdentityByPrincipal(principal);
        assertEquals(identity, found);
    }

    public void testPersistCredential() {
        
        String principal = generateKey(20);
        String optionalAlias = generateKey(20);
        Identity identity = createAndPersistIdentity(principal, optionalAlias);
        Credential credential = UserCreatorImpl.credentialFactory(identity);
        userDao.persistCredential(credential);
        
        hibernateTemplate.flush();
        
        Credential found = userDao.findCredentialByIdentity(identity);
        assertEquals(credential, found);
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //~ utility methods 
    
    public static List<Identity> createIdentities(int size) {
        List<Identity> identities = new ArrayList<Identity>();
        for (int i=0;i<size;i++) {
            identities.add(UserCreatorImpl.identityFactory("PRINCIPAL"+i, "ALIAS"+i));
        }
        return identities ;
    }
    
}
