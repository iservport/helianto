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
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.ServerMgr;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.type.IdentityType;
import org.helianto.web.view.IdentityForm;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFormActionTests extends TestCase {
    
    // class under test
    private IdentityFormAction identityFormAction;
    
    public void testConstruct() {
        assertEquals("identityForm", identityFormAction.getFormObjectName());
        assertEquals(IdentityForm.class, identityFormAction.getFormObjectClass());
    }
    
    public void testCreate() {
        Credential credential = new Credential();
        
        expect(securityMgr.createCredentialAndIdentity())
            .andReturn(credential);
        replay(securityMgr);
        
        Event event = identityFormAction.create(context);
        assertEquals(event.getId(), "success");
        verify(securityMgr);
        
        assertSame(credential, ((IdentityForm) context.getFlowScope().get("identityForm")).getCredential());
    }
    
    public void testPersistIdentity() {
        form.setCredential(new Credential());
        Identity identity = new Identity();
        form.getCredential().setIdentity(identity);
        
        securityMgr.persistIdentity(form.getCredential().getIdentity());
        replay(securityMgr);

        Event event = identityFormAction.persistIdentity(context);
        assertEquals(event.getId(), "success");
        verify(securityMgr);
    }
    
    public void testVerifySuccess() {
        Credential credential = new Credential();
        form.setCredential(credential);
        
        expect(securityMgr.verifyPassword(credential))
            .andReturn(true);
        replay(securityMgr);
        
        Event event = identityFormAction.verify(context);
        assertEquals(event.getId(), "success");
        verify(securityMgr);
        
    }
    
    public void testVerifyError() {
        Credential credential = new Credential();
        form.setCredential(credential);
        
        expect(securityMgr.verifyPassword(credential))
            .andReturn(false);
        replay(securityMgr);
        
        Event event = identityFormAction.verify(context);
        assertEquals(event.getId(), "error");
        verify(securityMgr);
        
    }
    
    public void testCreateMailFormSuccess() {
        IdentityFormAction localIdentityFormAction = new IdentityFormAction();
        Operator operator = OperatorTestSupport.createOperator();
        context.getFlowScope().put("operator", operator);
        Credential credential = AuthenticationTestSupport.createCredential();
        credential.getIdentity().setIdentityType(IdentityType.PERSONAL_EMAIL.getValue());
        form.setCredential(credential);
        
        mailForm = localIdentityFormAction.createMailForm(context);
        assertNotNull(mailForm);
        assertSame(operator, mailForm.getOperator());
        assertSame(credential, mailForm.getCredential());
        assertSame(credential.getIdentity(), mailForm.getRecipientIdentities().iterator().next());
    }
    
    public void testCreateMailFormError() {
        IdentityFormAction localIdentityFormAction = new IdentityFormAction();
        
        mailForm = localIdentityFormAction.createMailForm(context);
        assertNull(mailForm);
    }
    
    public void testSendSuccess() throws MessagingException {
        mailForm = new PasswordConfirmationMailForm();
        
        serverMgr.sendPasswordConfirmation(mailForm);
        replay(serverMgr);
        
        Event event = identityFormAction.send(context);
        assertEquals(event.getId(), "success");
        verify(serverMgr);
    }
    
    public void testSendError() throws MessagingException {
        mailForm = null;
        
        replay(serverMgr);

        Event event = identityFormAction.send(context);
        assertEquals(event.getId(), "error");
        verify(serverMgr);
    }
    
    public void testSendFailure() throws MessagingException {
        mailForm = new PasswordConfirmationMailForm();
        
        serverMgr.sendPasswordConfirmation(mailForm);
        expectLastCall().andThrow(new MessagingException());
        replay(serverMgr);

        Event event = identityFormAction.send(context);
        assertEquals(event.getId(), "error");
        verify(serverMgr);
    }
    
    // collabs
    private RequestContext context;
    private IdentityForm form;
    private SecurityMgr securityMgr;
    private ServerMgr serverMgr;
    private PasswordConfirmationMailForm mailForm;
    
    @Override
    public void setUp() {
        context = new MockRequestContext();
        form = new IdentityForm();
        identityFormAction = new IdentityFormAction() {
            protected PasswordConfirmationMailForm createMailForm(RequestContext context) {
                System.out.println("Mail is "+mailForm);
                return mailForm;
            }
        };
        context.getFlowScope().put(identityFormAction.getFormObjectName(), form);
        
        securityMgr = createMock(SecurityMgr.class);
        serverMgr = createMock(ServerMgr.class);
        identityFormAction.setSecurityMgr(securityMgr);
        identityFormAction.setServerMgr(serverMgr);
    }
    
    @Override
    public void tearDown() {
        reset(securityMgr);
        reset(serverMgr);
    }
    
}
