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
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.IdentityType;
import org.helianto.core.Operator;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.message.mail.compose.PasswordConfirmationMailForm;
import org.helianto.message.service.MessageMgr;
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
    
    public void testCreateNecessary() {
        Event event = identityFormAction.createIfNecessary(context);
        assertEquals(event.getId(), "success");
        
        assertTrue((identityFormAction.doGetForm(context).getCredential()) instanceof Credential);
    }
    
    public void testCreateNotNecessary() {
        Credential credential = CredentialTestSupport.createCredential();
        form.setCredential(credential);
        Event event = identityFormAction.createIfNecessary(context);
        assertEquals(event.getId(), "success");
        
        assertSame(credential, identityFormAction.doGetForm(context).getCredential());
    }
    
//    public void testWriteIdentity() throws Exception {
//        Credential credential = CredentialTestSupport.createCredential();
//        Identity managedIdentity = new Identity();
//        form.setCredential(credential);
//        
//        expect(userMgr.storeIdentity(form.getCredential().getIdentity())).andReturn(managedIdentity);
//        replay(userMgr);
//
//        Event event = identityFormAction.storeIdentity(context);
//        assertEquals(event.getId(), "success");
//        verify(userMgr);
//    }
//    
//    public void testNonUnique() throws Exception {
//        Credential credential = CredentialTestSupport.createCredential();
//        form.setCredential(credential);
//        BindingResult bindingResult = new BeanPropertyBindingResult(form, "identityFormAction");
//        context.getFlashScope().put("errors", bindingResult);
//
//        Event event = identityFormAction.writeIdentity(context);
//        assertEquals(event.getId(), "error");
//    }
//    
    public void testGeneratePassword() {
        Credential credential = CredentialTestSupport.createCredential();
        form.setCredential(credential);
        String password = credential.getPassword();

        Event event = identityFormAction.generatePassword(context);
        assertEquals(event.getId(), "success");
        
        assertFalse(credential.getPassword().equals(password));
    }
    
    public void testVerifySuccess() {
        Credential credential = Credential.credentialFactory("");
        credential.setPassword("TEST");
        credential.setVerifyPassword("TEST");
        form.setCredential(credential);
        
        Event event = identityFormAction.verify(context);
        assertEquals(event.getId(), "success");
        
    }
    
    public void testVerifyError() {
        Credential credential = Credential.credentialFactory("");
        credential.setPassword("TEST1");
        credential.setVerifyPassword("TEST2");
        form.setCredential(credential);
        
        Event event = identityFormAction.verify(context);
        assertEquals(event.getId(), "error");
        
    }
    
    public void testCreateMailFormSuccess() {
        IdentityFormAction localIdentityFormAction = new IdentityFormAction();
        Operator operator = OperatorTestSupport.createOperator();
        context.getFlowScope().put("operator", operator);
        Credential credential = CredentialTestSupport.createCredential();
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
        
        messageMgr.sendPasswordConfirmation(mailForm);
        replay(messageMgr);
        
        Event event = identityFormAction.send(context);
        assertEquals(event.getId(), "success");
        verify(messageMgr);
    }
    
    public void testSendError() throws MessagingException {
        mailForm = null;
        
        replay(messageMgr);

        Event event = identityFormAction.send(context);
        assertEquals(event.getId(), "error");
        verify(messageMgr);
    }
    
    public void testSendFailure() throws MessagingException {
        mailForm = new PasswordConfirmationMailForm();
        
        messageMgr.sendPasswordConfirmation(mailForm);
        expectLastCall().andThrow(new MessagingException());
        replay(messageMgr);

        Event event = identityFormAction.send(context);
        assertEquals(event.getId(), "error");
        verify(messageMgr);
    }
    
    // collabs
    private RequestContext context;
    private IdentityForm form;
    private MessageMgr messageMgr;
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
        
        messageMgr = createMock(MessageMgr.class);
        identityFormAction.setMessageMgr(messageMgr);
    }
    
    @Override
    public void tearDown() {
        reset(messageMgr);
    }
    
}



