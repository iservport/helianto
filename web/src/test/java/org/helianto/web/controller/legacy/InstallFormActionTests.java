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

package org.helianto.web.controller.legacy;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.service.ServerMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.controller.legacy.IdentityForm;
import org.helianto.web.controller.legacy.InstallFormAction;
import org.helianto.web.controller.legacy.UserForm;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InstallFormActionTests extends TestCase {
    
    // class under test
    private InstallFormAction installFormAction;
    
    public void testConstruct() {
        assertEquals("userForm", installFormAction.getFormObjectName());
        assertEquals(UserForm.class, installFormAction.getFormObjectClass());
    }
    
    public void testIfNewYes() {
        List<Operator> operatorList = new ArrayList<Operator>();
        expect(operatorMgr.findOperator()).andReturn(operatorList);
        replay(operatorMgr);
        
        Event event = installFormAction.ifNew(new MockRequestContext());
        assertEquals(event.getId(), "yes");
        verify(operatorMgr);
    }

    public void testIfNewNo() {
        List<Operator> operatorList = new ArrayList<Operator>();
        operatorList.add(new Operator());
        expect(operatorMgr.findOperator()).andReturn(operatorList);
        replay(operatorMgr);
        
        Event event = installFormAction.ifNew(new MockRequestContext());
        assertEquals(event.getId(), "no");
        verify(operatorMgr);
    }
    
    
    public void testCreateManager() {
        RequestContext context = simulateFormInContext(new UserForm());
        Identity managerIdentity = new Identity();
        IdentityForm identityForm = new IdentityForm();
        identityForm.setCredential(new Credential());
        identityForm.getCredential().setIdentity(managerIdentity);
        context.getRequestScope().put("identityForm", identityForm);
        User user = new User();
        
        expect(serverMgr.storeManager(managerIdentity))
            .andReturn(user);
        replay(serverMgr);
        
        Event event = installFormAction.createManager(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
        
        assertSame(user, ((UserForm) context.getFlowScope().get("userForm")).getUser());
    }
    
    private RequestContext simulateFormInContext(Object form) {
        RequestContext context = new MockRequestContext();
        context.getFlowScope().put("userForm", form);
        return context;
    }
    
    // collabs
    private NamespaceMgr operatorMgr;
    private ServerMgr serverMgr;
    private UserMgr userMgr;
    
    @Override
    public void setUp() {
    	operatorMgr = createMock(NamespaceMgr.class);
        serverMgr = createMock(ServerMgr.class);
        userMgr = createMock(UserMgr.class);
        installFormAction = new InstallFormAction();
        installFormAction.setOperatorMgr(operatorMgr);
        installFormAction.setServerMgr(serverMgr);
        installFormAction.setUserMgr(userMgr);
    }
    
    @Override
    public void tearDown() {
        reset(operatorMgr);
        reset(serverMgr);
        reset(userMgr);
    }
    
}
