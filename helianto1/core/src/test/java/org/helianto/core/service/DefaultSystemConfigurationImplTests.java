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

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.test.AuthorizationTestSupport;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultSystemConfigurationImplTests extends TestCase {
    
//    private SystemConfigurationTemplate systemConfigurationTemplate;
//    
//    public void testCreateDefaultEntity() {
//        Entity defaultEntity = systemConfigurationTemplate.createDefaultEntity();
//        assertEquals("DEFAULT", defaultEntity.getAlias());
//        assertEquals("DEFAULT", defaultEntity.getOperator().getOperatorName());
//    }
//    
//    public void testCreateManager() {
//        UserRole managerRole = AuthorizationTestSupport.createUserRole();
//        Identity managerIdentity = new Identity();
//        User manager = systemConfigurationTemplate.createManager(managerRole, managerIdentity);
//        assertSame(managerRole.getUserGroup(), manager.getParent());
//        assertSame(managerIdentity, manager.getIdentity());
//    }
//    
//    @Override
//    public void setUp() {
//        ServerMgrImpl serverMgr = new ServerMgrImpl();
//        systemConfigurationTemplate = serverMgr.new DefaultSystemConfigurationImpl();
//    }

}
