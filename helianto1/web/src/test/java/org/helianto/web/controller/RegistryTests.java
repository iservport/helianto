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

package org.helianto.web.controller;

import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.web.controller.InstallFormAction;

public class RegistryTests extends AbstractIntegrationTest {
    
    @Override 
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/core.xml", 
                "deploy/security.xml", 
                "deploy/security-http.xml", 
                "deploy/install.xml", 
                "deploy/webflow.xml"};
    }
    
    private InstallFormAction installFormAction;
    
    public void testInit() {
        assertTrue(installFormAction instanceof InstallFormAction);
    }

    public void setInstallFormAction(InstallFormAction installFormAction) {
        this.installFormAction = installFormAction;
    }

}
