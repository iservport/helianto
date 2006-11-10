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

import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.web.test.FreeMarkerViewTestSupport;
import org.helianto.web.view.IdentityForm;
import org.springframework.mock.web.MockHttpServletResponse;

public class IdentityViewTests extends FreeMarkerViewTestSupport {

    public void testSummary() throws Exception {
        model.put("page", 1);
        processView("identity/summary.ftl", model, false);
    }

    public void testPrincipalView() throws Exception {
        processView("identity/principal.ftl");
    }

    public void testDetailsView() throws Exception {
        processView("identity/details.ftl");
    }
    
    public MockHttpServletResponse processView(String templateName) throws Exception {
        IdentityForm identityForm = new IdentityForm();
        identityForm.setCredential(AuthenticationTestSupport.createCredential());
        model.put("identityForm", identityForm);
        return processView(templateName, model, false);
    }

}
