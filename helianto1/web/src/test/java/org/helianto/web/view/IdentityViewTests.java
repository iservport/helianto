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

package org.helianto.web.view;

import java.io.IOException;

import org.helianto.core.IdentityType;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.web.test.FreeMarkerViewTestSupport;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import freemarker.template.TemplateException;

public class IdentityViewTests extends FreeMarkerViewTestSupport {

    public void testPrincipalView() throws Exception {
    	setOutputFileName("principal.htm");
        processView("identity/principal.ftl", model, false);
    }

    public void testPlainView() throws Exception {
    	setOutputFileName("plain.htm");
        processView("identity/plain.ftl", model, false);
    }

    public void testGeneratedView() throws Exception {
    	setOutputFileName("generated.htm");
        processView("identity/generated.ftl", model, false);
    }

    public void testPrincipalViewErrors() throws Exception {
    	setOutputFileName("principalError.htm");
        bindingResult.rejectValue("credential.identity.principal", null, "ERROR IN principal");
        model.put(BindingResult.MODEL_KEY_PREFIX+"identityForm", bindingResult);
        processView("identity/principal.ftl", model, false);
    }

    public void testPrivacyView() throws Exception {
    	setOutputFileName("privacy.htm");
        processView("identity/privacy.ftl", model, false);
    }
    
    public void testPersonalView() throws Exception {
    	setOutputFileName("personal.htm");
        processView("identity/personal.ftl", model, false);
    }
    
    public void testPersonalViewErrors() throws Exception {
    	setOutputFileName("personalError.htm");
        bindingResult.rejectValue("credential.identity.optionalAlias", null, "ERROR IN optionalAlias");
        bindingResult.rejectValue("credential.identity.personalData.firstName", null, "ERROR IN firstName");
        bindingResult.rejectValue("credential.identity.personalData.lastName", null, "ERROR IN lastName");
        bindingResult.rejectValue("credential.identity.personalData.appellation", null, "ERROR IN appellation");
        bindingResult.rejectValue("credential.identity.personalData.gender", null, "ERROR IN gender");
        model.put(BindingResult.MODEL_KEY_PREFIX+"identityForm", bindingResult);
        processView("identity/personal.ftl", model, false);
    }
    
    public void testCredentialView() throws Exception {
        processView("identity/credential.ftl", model, false);
    }
    
    public void testPasswordView() throws Exception {
        processView("identity/password.ftl", model, false);
    }
    
    public void testPasswordViewError() throws Exception {
        bindingResult.rejectValue("credential.verifyPassword", null, "ERROR IN password");
        model.put(BindingResult.MODEL_KEY_PREFIX+"identityForm", bindingResult);
        processView("identity/password.ftl", model, false);
    }
    
    public void testConfirmView() throws Exception {
    	setOutputFileName("confirm.htm");
        processView("identity/confirm.ftl", model, false);
    }
    
    @Override
    public void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
        super.setUp();
        IdentityForm identityForm = new IdentityForm();
        identityForm.setCredential(CredentialTestSupport.createCredential());
        identityForm.getCredential().getIdentity().setIdentityType(IdentityType.EMAIL.getValue());
        identityForm.getCredential().getIdentity().getPersonalData().setFirstName("First Name");
        identityForm.getCredential().getIdentity().getPersonalData().setLastName("Last Name");
        model.put("identityForm", identityForm);
        bindingResult = new BeanPropertyBindingResult(identityForm, "identityForm");
    }

}
