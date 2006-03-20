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

import org.helianto.core.UserCreatorImpl;
import org.helianto.core.Credential;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.junit.AbstractIntegrationTest;

public class CredentialDaoImplTests extends AbstractIntegrationTest {
    
    private CredentialDao credentialDao;
    private UserCreatorImpl factory;
    
    public void setCredentialDao(CredentialDao credentialDao) {
        this.credentialDao = credentialDao;
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        factory = new UserCreatorImpl();
    }

    public void testCredentialLifeCycle() {
        
        Credential credential = factory.credentialFactory("TEST");
        credentialDao.persistCredential(credential);
        
        hibernateTemplate.flush();
        
        Credential cr = credentialDao.findCredentialByPrincipal("TEST");
        assertEquals(credential, cr);
        
        Credential duplicatedCredential = factory.credentialFactory("TEST");
        try {
            credentialDao.persistCredential(duplicatedCredential);
            fail();
        } catch (Exception e) {
            //ok
        }

    }

}
