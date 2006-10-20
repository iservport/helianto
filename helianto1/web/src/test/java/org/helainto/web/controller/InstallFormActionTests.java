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
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Operator;
import org.helianto.core.service.ServerMgr;
import org.helianto.web.controller.InstallFormAction;
import org.helianto.web.view.OperatorForm;
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
    
    public void testCreateOperator() {
        Operator operator = new Operator();
        expect(serverMgr.createLocalDefaultOperator()).andReturn(operator);
        replay(serverMgr);
        
        RequestContext context = new MockRequestContext();
        OperatorForm form = new OperatorForm();
        context.getFlowScope().put("formObject", form);
        
        Event event = installFormAction.createOperator(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
        
        assertSame(operator, form.getOperator());
    }

    public void testPersistOperator() {
        Operator operator = new Operator();
        OperatorForm form = new OperatorForm();
        form.setOperator(operator);
        RequestContext context = new MockRequestContext();
        context.getFlowScope().put("formObject", form);
        
        serverMgr.persistOperator(operator);
        replay(serverMgr);
        
        Event event = installFormAction.persistOperator(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
    }

    // collabs
    private ServerMgr serverMgr;
    
    @Override
    public void setUp() {
        serverMgr = createMock(ServerMgr.class);
        installFormAction = new InstallFormAction();
        installFormAction.setServerMgr(serverMgr);
    }
    
    @Override
    public void tearDown() {
        reset(serverMgr);
    }
    
}
