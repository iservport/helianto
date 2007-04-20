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

package org.helianto.core.creation;

import java.util.Locale;

import junit.framework.TestCase;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Encription;
import org.helianto.core.OperationMode;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.ServerType;

public class OperatorCreatorTests extends TestCase {

    /*
     * Test method for 'org.helianto.core.creation.BaseCreator.operatorFactory(String, OperationMode, Locale)'
     */
    public void testOperatorFactory() {
        Operator operator = OperatorCreator.operatorFactory("NAME", OperationMode.ENTERPRISE, Locale.CANADA);
        
        assertEquals("NAME", operator.getOperatorName());
        assertEquals("operator@helianto.org", operator.getOperatorSourceMailAddress());
        assertEquals("http://www.helianto.org", operator.getOperatorHostAddress());
        assertEquals("ISO-8859-1", operator.getDefaultEncoding());
        assertEquals(OperationMode.ENTERPRISE.getValue(), operator.getOperationMode());
        assertEquals(Locale.CANADA, operator.getLocale());
        assertNull(operator.getParent());
    }

    public void testOperatorFactoryDefaults() {
        Operator operator = OperatorCreator.operatorFactory("", null, null);
        
        assertEquals("", operator.getOperatorName());
        assertEquals(OperationMode.LOCAL.getValue(), operator.getOperationMode());
        assertEquals(Locale.getDefault(), operator.getLocale());
    }

    /*
     * Test method for 'org.helianto.core.creation.BaseCreator.serverFactory(Operator, String, ServerType, Credential)'
     */
    public void testServerFactory() {
        Operator operator = new Operator();
        Credential credential = new Credential();
        Server server = OperatorCreator.serverFactory(operator, "NAME", ServerType.HTTP_SERVER, credential);
        
        assertSame(operator, server.getOperator());
        assertEquals("NAME", server.getServerName());
        assertEquals(-1, server.getServerPort());
        assertEquals(ServerType.HTTP_SERVER.getValue(), server.getServerType());
        assertSame(credential, server.getCredential());
        
        assertEquals("", server.getServerHostAddress());
        assertEquals("", server.getServerDesc());
        assertEquals((byte) 1, server.getPriority());
        assertEquals(ActivityState.ACTIVE.getValue(), server.getServerState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), server.getRequiredEncription());
    }

    public void testServerFactoryDefaults() {
        Operator operator = new Operator();
        Server server = OperatorCreator.serverFactory(operator, "NAME", null, null);
        
        assertEquals(ServerType.SMTP_SERVER.getValue(), server.getServerType());
        assertSame("NAME", server.getCredential().getIdentity().getPrincipal());
        assertSame("", server.getCredential().getPassword());
    }

   /*
     * Assertion errors
     */
    public void testErrors() {
        try {
            OperatorCreator.serverFactory(null, "", ServerType.HTTP_SERVER, new Credential()); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
        try {
            OperatorCreator.provinceFactory(null, "", ""); fail();
        } catch (IllegalArgumentException e) {
        } catch (Exception e) { fail(); }
    }

}
