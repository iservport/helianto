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

public class RegistryTests extends AbstractCoreTest {
    
    private ServerMgr serverMgr;

    private UserMgr userMgr;

    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }
    
    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    // just put the init method to work
    public void testInit() {
        assertTrue(serverMgr instanceof ServerMgr);
        assertTrue(userMgr instanceof UserMgr);
    }

}
