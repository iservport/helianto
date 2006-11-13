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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.web.test.FreeMarkerViewTestSupport;
import org.helianto.web.view.IdentityForm;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.support.BindStatus;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class IdentityViewTests extends FreeMarkerViewTestSupport {

    public void testSummary() throws Exception {
        model.put("page", 1);
        processView("identity/summary.ftl", model, false);
    }

    public void testPrincipalView() throws Exception {
        processView("identity/principal.ftl", model, true);
    }

    public void testPrincipalViewError() throws Exception {
        model.put("errors", "xxx");
        processView("identity/principal.ftl", model, true);
    }

    public void testDetailsView() throws Exception {
        processView("identity/details.ftl", model, true);
    }
    
    public void testPersonalView() throws Exception {
        processView("identity/personal.ftl", model, true);
    }
    
    public void testPersonalViewErrors() throws Exception {
        bindingResult.rejectValue("credential.identity.personalData.lastName", null, "ERROR IN lastName");
        bindingResult.rejectValue("credential.identity.personalData.appellation", null, "ERROR IN appellation");
        model.put(BindingResult.MODEL_KEY_PREFIX+"identityForm", bindingResult);
        processView("identity/personal.ftl", model, true);
    }
    
    @Override
    public void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
        super.setUp();
        IdentityForm identityForm = new IdentityForm();
        identityForm.setCredential(AuthenticationTestSupport.createCredential());
        model.put("identityForm", identityForm);
        bindingResult = new BeanPropertyBindingResult(identityForm, "identityForm");
    }

}
