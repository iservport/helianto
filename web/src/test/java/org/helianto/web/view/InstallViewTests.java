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

import org.helianto.core.test.UserTestSupport;
import org.helianto.web.controller.UserForm;
import org.helianto.web.test.FreeMarkerViewTestSupport;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import freemarker.template.TemplateException;

public class InstallViewTests extends FreeMarkerViewTestSupport {

    public void testInfoView() throws Exception {
    	setOutputFileName("info.htm");
        processView("configuration/info.ftl", model, false);
    }
    
    public void testRenameView() throws Exception {
    	setOutputFileName("rename.htm");
        processView("configuration/rename.ftl", model, false);
    }

    public void testRenameViewErrors() throws Exception {
    	setOutputFileName("renameError.htm");
        bindingResult.rejectValue("user.entity.alias", null, "ERROR IN alias");
        model.put(BindingResult.MODEL_KEY_PREFIX+"userForm", bindingResult);
        processView("configuration/rename.ftl", model, false);
    }

    @Override
    public void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
        super.setUp();
        UserForm userForm = new UserForm();
        userForm.setUser(UserTestSupport.createUser());
        model.put("userForm", userForm);
        bindingResult = new BeanPropertyBindingResult(userForm, "userForm");
    }

}
