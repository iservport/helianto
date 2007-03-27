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

package org.helianto.core;

import java.util.Locale;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.Encription;
import org.helianto.core.type.OperationMode;
import org.helianto.core.type.ServerType;

public class BaseDomainTests extends TestCase {

    public void testOperator() {
        Operator operator = new Operator();
        operator.setId(Integer.MAX_VALUE);
        operator.setId(Integer.MIN_VALUE);

        operator.setOperatorName("");

        operator.setParent(new Operator());

//        operator.setLocale(Locale.getDefault());

        operator.setOperationMode(OperationMode.LOCAL.getValue());
        operator.setOperationMode(OperationMode.ENTERPRISE.getValue());
        operator.setOperationMode(OperationMode.DELEGATED.getValue());
    }

    public void testOperatorEquals() {
        Operator copy, operator = new Operator();
        operator.setOperatorName("TEST");
        copy = (Operator) DomainTestSupport.minimalEqualsTest(operator);

        copy.setOperatorName("TEST");
        assertTrue(operator.equals(copy));
    }

    public void testServer() {
        Server server = new Server();
        server.setId(Integer.MAX_VALUE);
        server.setId(Integer.MIN_VALUE);

        server.setOperator(new Operator());

        server.setServerName("");
        server.setServerHostAddress("");
        server.setServerPort(Integer.MAX_VALUE);
        server.setServerPort(Integer.MIN_VALUE);
        server.setServerDesc("");

        server.setServerType(ServerType.HTTP_SERVER.getValue());
        server.setServerType(ServerType.POP3_SERVER.getValue());
        server.setServerType(ServerType.SMTP_SERVER.getValue());

        server.setPriority((byte) 1);

        server.setServerState(ActivityState.ACTIVE.getValue());
        server.setServerState(ActivityState.CANCELLED.getValue());
        server.setServerState(ActivityState.INITIAL.getValue());
        server.setServerState(ActivityState.SUSPENDED.getValue());

        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());

        server.setCredential(new Credential());
    }

    public void testServerEquals() {
        Server copy, server = new Server();
        server.setOperator(new Operator());
        server.setServerName("TEST");
        server.setCredential(new Credential());
        copy = (Server) DomainTestSupport.minimalEqualsTest(server);

        copy.setOperator(server.getOperator());
        assertFalse(server.equals(copy));

        copy.setOperator(server.getOperator());
        copy.setServerName("TEST");
        assertFalse(server.equals(copy));

        copy.setOperator(null);
        copy.setServerName("TEST");
        copy.setCredential(server.getCredential());
        assertFalse(server.equals(copy));

        copy.setOperator(server.getOperator());
        copy.setServerName("");
        copy.setCredential(server.getCredential());
        assertFalse(server.equals(copy));

        copy.setOperator(server.getOperator());
        copy.setServerName("TEST");
        copy.setCredential(null);
        assertFalse(server.equals(copy));

        copy.setOperator(server.getOperator());
        copy.setServerName("TEST");
        copy.setCredential(server.getCredential());
        assertTrue(server.equals(copy));
    }

    public void testProvince() {
        Province province = new Province();
        province.setId(Integer.MAX_VALUE);
        province.setId(Integer.MIN_VALUE);

        province.setOperator(new Operator());

        province.setCode("");

        province.setProvinceName("");
    }

    public void testProvinceEquals() {
        Province copy, province = new Province();
        province.setOperator(new Operator());
        province.setCode("TEST");
        copy = (Province) DomainTestSupport.minimalEqualsTest(province);

        copy.setOperator(province.getOperator());
        assertFalse(province.equals(copy));

        copy.setOperator(null);
        copy.setCode("TEST");
        assertFalse(province.equals(copy));

        copy.setOperator(province.getOperator());
        copy.setCode("TEST");
        assertTrue(province.equals(copy));
    }

    public void testService() {
        Service service = new Service();
        service.setId(Integer.MAX_VALUE);
        service.setId(Integer.MIN_VALUE);

        service.setOperator(new Operator());

        service.setServiceName("");
    }

    public void testServiceEquals() {
        Service copy, service = new Service();
        service.setOperator(new Operator());
        service.setServiceName("TEST");
        copy = (Service) DomainTestSupport.minimalEqualsTest(service);
        
        copy.setOperator(service.getOperator());
        assertFalse(service.equals(copy));

        copy.setOperator(null);
        copy.setServiceName("TEST");
        assertFalse(service.equals(copy));

        copy.setOperator(service.getOperator());
        copy.setServiceName("TEST");
        assertTrue(service.equals(copy));
    }

    public void testEntity() {
        Entity entity = new Entity();
        entity.setId(Long.MAX_VALUE);
        entity.setId(Long.MIN_VALUE);

        entity.setAlias("");
        
        entity.setOperator(new Operator());
    }

    public void testEntityEquals() {
        Entity copy, entity = new Entity();
        entity.setOperator(new Operator());
        entity.setAlias("TEST");
        copy = (Entity) DomainTestSupport.minimalEqualsTest(entity);
        
        copy.setOperator(entity.getOperator());
        assertFalse(entity.equals(copy));

        copy.setOperator(null);
        copy.setAlias("TEST");
        assertFalse(entity.equals(copy));

        copy.setOperator(entity.getOperator());
        copy.setAlias("TEST");
        assertTrue(entity.equals(copy));
    }

    public void testOrganization() {
        Organization organization = new Organization();
        organization.setBusinessName("");

    }

    public void testIndividual() {
        Individual individual = new Individual();
        individual.setIdentity(new Identity());
    }
    
    public void testKeyType() {
        KeyType keyType = new KeyType();
        keyType.setId(Integer.MAX_VALUE);
        keyType.setId(Integer.MIN_VALUE);

        keyType.setOperator(new Operator());

        keyType.setKeyCode("");

        keyType.setPurpose("");
    }

    public void testKeyTypeEquals() {
        KeyType copy, keyType = new KeyType();
        keyType.setOperator(new Operator());
        keyType.setKeyCode("TEST");
        copy = (KeyType) DomainTestSupport.minimalEqualsTest(keyType);
        
        copy.setOperator(keyType.getOperator());
        assertFalse(keyType.equals(copy));

        copy.setOperator(null);
        copy.setKeyCode("TEST");
        assertFalse(keyType.equals(copy));

        copy.setOperator(keyType.getOperator());
        copy.setKeyCode("TEST");
        assertTrue(keyType.equals(copy));
    }

}
