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

import javax.mail.MessagingException;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.mail.MockJavaMailAdapter;

public class CoreMgrImplTests extends AbstractCoreTest {
    
    public void test() throws MessagingException {
//        Entity entity = getTestEntity();
//        Supervisor supervisor = entity.getSupervisor();
//        String ownerName = generateKey(20);
//        supervisor.setSupervisorName(ownerName);
//        supervisor.setHttpAddress("http://www.confirmationsite");
//        supervisor.setMailHost("mailHost");
//        supervisor.setMailUser("info@iservport.com");
//        supervisor.setMailPassword("aaa");
//        supervisor.setStoreHost("storeHost");
//        supervisor.setStoreType("pop3");
//        supervisor.setStoreUser("iservport");
//        supervisor.setStorePassword("bbb");
//        coreMgr.persistEntity(entity);
//        assertNotNull("0.1", entity.getId());
//        assertNotNull("0.2", entity.getSupervisor().getId());
//        assertEquals("0.3", ownerName, supervisor.getSupervisorName());
//        assertEquals("0.4", "http://www.confirmationsite", supervisor.getHttpAddress());
//        assertEquals("0.5", "mailHost", supervisor.getMailHost());
//        assertEquals("0.6", "info@iservport.com", supervisor.getMailUser());
//        assertEquals("0.7", "aaa", supervisor.getMailPassword());
//        assertEquals("0.8", "storeHost", supervisor.getStoreHost());
//        assertEquals("0.9", "pop3", supervisor.getStoreType());
//        assertEquals("0.10", "iservport", supervisor.getStoreUser());
//        assertEquals("0.11", "bbb", supervisor.getStorePassword());
//        
//        //Change to MockJavaMailAdapter
//        MockJavaMailAdapter ma = new MockJavaMailAdapter();
//        ma.setMailComposer(coreMgr.getJavaMailAdapter().getMailComposer());
//        coreMgr.setJavaMailAdapter(ma);
//        
//        Credential cred = coreMgr.credentialFactory();
//        String principal = generateKey(64);
//        cred.setPrincipal(principal);
//        String password = generateKey(20);
//        cred.setPassword(password);
//        
//        coreMgr.sendRegistrationNotification(supervisor, cred);
//        
    }
    
}
