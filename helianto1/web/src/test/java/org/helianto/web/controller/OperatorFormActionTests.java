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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import junit.framework.TestCase;

import org.helianto.core.Operator;
import org.helianto.core.service.ServerMgr;
import org.helianto.web.controller.OperatorFormAction;
import org.helianto.web.view.OperatorForm;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorFormActionTests extends TestCase {
    
    // class under test
    private OperatorFormAction operatorFormAction;
    
    public void testConstruct() {
        assertEquals("operatorForm", operatorFormAction.getFormObjectName());
        assertEquals(OperatorForm.class, operatorFormAction.getFormObjectClass());
    }
    
    public void testCreateOperator() {
        Operator operator = new Operator();
        expect(serverMgr.createLocalDefaultOperator()).andReturn(operator);
        replay(serverMgr);
        
        RequestContext context = new MockRequestContext();
        OperatorForm form = new OperatorForm();
        context.getFlowScope().put("operatorForm", form);
        
        Event event = operatorFormAction.createOperator(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
        
        assertSame(operator, form.getOperator());
    }

    public void testPersistOperator() {
        Operator operator = new Operator();
        OperatorForm form = new OperatorForm();
        form.setOperator(operator);
        RequestContext context = new MockRequestContext();
        context.getFlowScope().put("operatorForm", form);
        
        serverMgr.persistOperator(operator);
        replay(serverMgr);
        
        Event event = operatorFormAction.persistOperator(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
    }
    
    @SuppressWarnings("unchecked")
    public void testLoadAvailableLocales() {
        RequestContext context = new MockRequestContext();
        Locale[] locales = Locale.getAvailableLocales();
        Set<String> keys = new HashSet<String>();
        for (Locale locale: locales) {
            keys.add(locale.toString());
        }

        Event event = operatorFormAction.loadAvailableLocalesInRequestScope(context);
        assertEquals(event.getId(), "success");

        HashMap<String, String> contextLocales = (HashMap<String, String>) context.getRequestScope().get("locales");
        assertTrue(keys.containsAll(contextLocales.keySet()));
        assertTrue(contextLocales.keySet().containsAll(keys));
    }
    
    public void testListOperator() {
        RequestContext context = new MockRequestContext();
        
        List<Operator> operatorList = new ArrayList<Operator>();
        expect(serverMgr.findOperator()).andReturn(operatorList);
        replay(serverMgr);
        
        operatorFormAction.listOperatorInRequestScope(context);
        verify(serverMgr);
        assertSame(operatorList, context.getRequestScope().get("operatorList"));
        
    }

    // collabs
    private ServerMgr serverMgr;
    
    @Override
    public void setUp() {
        serverMgr = createMock(ServerMgr.class);
        operatorFormAction = new OperatorFormAction();
        operatorFormAction.setServerMgr(serverMgr);
    }
    
    @Override
    public void tearDown() {
        reset(serverMgr);
    }
    
}
