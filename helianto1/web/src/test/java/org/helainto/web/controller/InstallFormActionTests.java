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

package org.helainto.web.controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.service.ServerMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.controller.InstallFormAction;
import org.helianto.web.view.UserForm;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.test.MockRequestContext;

public class InstallFormActionTests extends TestCase {
    
    // class under test
    private InstallFormAction installFormAction;
    
    public void testIfNewYes() {
        List<Operator> operatorList = new ArrayList<Operator>();
        expect(serverMgr.findOperator()).andReturn(operatorList);
        replay(serverMgr);
        
        Event event = installFormAction.ifNew(new MockRequestContext());
        assertEquals(event.getId(), "yes");
        verify(serverMgr);
    }

    public void testIfNewNo() {
        List<Operator> operatorList = new ArrayList<Operator>();
        operatorList.add(new Operator());
        expect(serverMgr.findOperator()).andReturn(operatorList);
        replay(serverMgr);
        
        Event event = installFormAction.ifNew(new MockRequestContext());
        assertEquals(event.getId(), "no");
        verify(serverMgr);
    }
    
    
    public void testCreateManager() {
        RequestContext context = simulateFormInContext(new UserForm());
        Identity managerIdentity = new Identity();
        context.getRequestScope().put("identity", managerIdentity);
        User user = new User();
        
        expect(serverMgr.createSystemConfiguration(managerIdentity))
            .andReturn(user);
        replay(serverMgr);
        
        Event event = installFormAction.createManager(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
        
        assertSame(user, ((UserForm) context.getFlowScope().get("formObject")).getUser());
    }
    
    public void testPersistManager() {
        UserForm form = new UserForm();
        form.setUser(new User());
        RequestContext context = simulateFormInContext(form);
        
        userMgr.persistUser(form.getUser());
        replay(serverMgr);

        Event event = installFormAction.persistManager(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
    }
    
    private RequestContext simulateFormInContext(Object form) {
        RequestContext context = new MockRequestContext();
        context.getFlowScope().put("formObject", form);
        return context;
    }
    
    // collabs
    private ServerMgr serverMgr;
    private UserMgr userMgr;
    
    @Override
    public void setUp() {
        serverMgr = createMock(ServerMgr.class);
        userMgr = createMock(UserMgr.class);
        installFormAction = new InstallFormAction();
        installFormAction.setServerMgr(serverMgr);
        installFormAction.setUserMgr(userMgr);
    }
    
    @Override
    public void tearDown() {
        reset(serverMgr);
        reset(userMgr);
    }
    
}
