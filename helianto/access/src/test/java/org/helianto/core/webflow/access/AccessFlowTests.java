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

package org.helianto.core.webflow.access;

import java.util.HashSet;
import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.context.SecurityContext;
import net.sf.acegisecurity.context.SecurityContextHolder;
import net.sf.acegisecurity.context.SecurityContextImpl;
import net.sf.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;
import org.helianto.core.security.UserAdapter;
import org.springframework.webflow.Flow;
import org.springframework.webflow.FlowArtifactException;
import org.springframework.webflow.test.AbstractFlowExecutionTests;

public class AccessFlowTests  extends AbstractFlowExecutionTests {
    
    protected String flowId() {
        return "accessFlow";
    }
    
    @Override
    protected Flow getFlow() throws FlowArtifactException {
        // TODO Auto-generated method stub
        return null;
    }

    protected String[] getConfigLocations() {
        return new String[] {
                "classpath:/deploy/applicationContext-core1.xml",
                "classpath:/deploy/security.xml",
                "classpath:/access-web.xml",
                "classpath:/credential-web.xml",
                "classpath:/access-servlet.xml"
                };
    }

    public void testFlowToNone() {
        // simulate security context
        Credential cred = new MockCredentialWithNoEntity();
        SecurityContext sc = new MockAuthenticatedSecurityContext(cred);
        SecurityContextHolder.setContext(sc);
        // start flow
        startFlow();
        assertCurrentStateEquals("noEntity.view");
    }
    
    public void testFlowToMany() {
        // simulate security context
        Credential cred = new MockCredentialWithManyEntities();
        SecurityContext sc = new MockAuthenticatedSecurityContext(cred);
        SecurityContextHolder.setContext(sc);
        // start flow
        startFlow();
        assertCurrentStateEquals("entList.view");
    }
    
    public void testFlowToOne() {
        // simulate security context
        Credential cred = new MockCredentialWithOneEntity();
        SecurityContext sc = new MockAuthenticatedSecurityContext(cred);
        SecurityContextHolder.setContext(sc);
        // start flow
        startFlow();
        assertCurrentStateEquals("welcome.view");
    }
    
    @SuppressWarnings("serial")
    private class MockAuthenticatedSecurityContext extends SecurityContextImpl {
        
        MockAuthenticatedSecurityContext(Credential cred) {
            UserAdapter ua = new UserAdapter(cred);
            Authentication auth = new UsernamePasswordAuthenticationToken(ua, ua.getPassword());
            this.setAuthentication(auth);
            logger.info("\n         Principal is "+this.getAuthentication().getPrincipal());
        }
    }
    
    @SuppressWarnings("serial")
    private class MockCredentialWithNoEntity extends Credential {
        
        MockCredentialWithNoEntity() {
            this.setPrincipal("noentity");
            this.setPassword("password");
            PersonalData pd = new PersonalData();
            pd.setFirstName("firstname");
            pd.setFirstName("lastname");
            this.setPersonalData(pd);
            this.setUsers(new HashSet());
        }
    }
    
    @SuppressWarnings("serial")
    private class MockCredentialWithOneEntity extends MockCredentialWithNoEntity {
        
        @SuppressWarnings("unchecked") 
        MockCredentialWithOneEntity() {
            super();
            this.setPrincipal("oneentity");
            new MockUser(this);
        }
    }
    
    @SuppressWarnings("serial")
    private class MockCredentialWithManyEntities extends MockCredentialWithOneEntity {
        
        @SuppressWarnings("unchecked") 
        MockCredentialWithManyEntities() {
            super();
            this.setPrincipal("manyentities");
            new MockUser(this);
            new MockUser(this);
        }
    }
    
    @SuppressWarnings("serial")
    private class MockUser extends User {
        
        @SuppressWarnings("unchecked") 
        MockUser(Credential cred) {
            this.setCredential(cred);
            this.setEntity(new Entity());
            cred.getUsers().add(this);
        }
    }

}
