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

import java.util.Date;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.service.SecurityMgr;
import org.helianto.web.controller.IdentityFormAction;
import org.helianto.web.view.IdentityForm;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.test.MockRequestContext;

public class IdentityFormActionTests extends TestCase {
    
    // class under test
    private IdentityFormAction identityFormAction;
    
    /*
    public Event create(RequestContext context) {
        IdentityForm form = doGetForm(context);
        form.setCredential(securityMgr.createCredentialAndIdentity());
        return success();
    }
    */
    
    public void testCreate() {
        RequestContext context = simulateFormInContext(new IdentityForm());
        Credential credential = new Credential();
        
        expect(securityMgr.createCredentialAndIdentity())
            .andReturn(credential);
        replay(securityMgr);
        
        Event event = identityFormAction.create(context);
        assertEquals(event.getId(), "success");
        verify(securityMgr);
        
        assertSame(credential, ((IdentityForm) context.getFlowScope().get("formObject")).getCredential());
    }
    
    /*
    public Event persistIdentity(RequestContext context) {
        securityMgr.persistIdentity(doGetForm(context).getCredential().getIdentity());
        return success();
    }
    */
    
    public void testPersistIdentity() {
        IdentityForm form = new IdentityForm();
        form.setCredential(new Credential());
        Identity identity = new Identity();
        form.getCredential().setIdentity(identity);
        RequestContext context = simulateFormInContext(form);
        
        securityMgr.persistIdentity(form.getCredential().getIdentity());
        replay(securityMgr);

        Event event = identityFormAction.persistIdentity(context);
        assertEquals(event.getId(), "success");
        verify(securityMgr);
    }
    
    /*
    public Event verify(RequestContext context) {
        Credential credential = doGetForm(context).getCredential();
        if (securityMgr.verifyPassword(credential)) {
            return success();
        }
        return error();
    }
    
    */
    
    public void testVerifySuccess() {
        IdentityForm form = new IdentityForm();
        Credential credential = new Credential();
        form.setCredential(credential);
        RequestContext context = simulateFormInContext(form);
        
        expect(securityMgr.verifyPassword(credential))
            .andReturn(true);
        replay(securityMgr);
        
        Event event = identityFormAction.verify(context);
        assertEquals(event.getId(), "success");
        verify(securityMgr);
        
    }
    
    public void testVerifyError() {
        IdentityForm form = new IdentityForm();
        Credential credential = new Credential();
        form.setCredential(credential);
        RequestContext context = simulateFormInContext(form);
        
        expect(securityMgr.verifyPassword(credential))
            .andReturn(false);
        replay(securityMgr);
        
        Event event = identityFormAction.verify(context);
        assertEquals(event.getId(), "error");
        verify(securityMgr);
        
    }
    
    private RequestContext simulateFormInContext(Object form) {
        RequestContext context = new MockRequestContext();
        context.getFlowScope().put("formObject", form);
        return context;
    }
    
    // collabs
    private SecurityMgr securityMgr;
    
    @Override
    public void setUp() {
        securityMgr = createMock(SecurityMgr.class);
        identityFormAction = new IdentityFormAction();
        identityFormAction.setSecurityMgr(securityMgr);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
    }
    
}
