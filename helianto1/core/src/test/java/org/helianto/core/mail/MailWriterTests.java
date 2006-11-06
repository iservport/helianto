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

package org.helianto.core.mail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Operator;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailWriterTests extends TestCase {
    
    static String body = 
"Welcome to OPERATOR,\n\n"+
"For reference, your identification information is:\n\n"+
"Email    : PRINCIPAL\n"+
"Password : PASSWORD\n\n"+
"In order to be able to login in the site, you have to activate your \n"+
"account. Please visit:\n\n"+
"    http://www.helianto.org/admin\n\n"+
"Thank you for your interest in OPERATOR.\n\n"+
"Regards,\n"+
"The OPERATOR operator.\n";        
    
    public void test() throws IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean fb = new FreeMarkerConfigurationFactoryBean();
        fb.setTemplateLoaderPath("file:fm/");
        fb.afterPropertiesSet();
        Configuration cfg = fb.createConfiguration();
        Template template = cfg.getTemplate("abc.ftl", new Locale("en"));
        Map<String, Object> model = createModel();
        StringWriter result = new StringWriter();
        template.process(model, result);
        assertEquals(body, result.toString());
        System.out.println(result.toString());

    }
    
    private Operator operator;
    private Credential credential;
    
    private Map<String, Object> createModel() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("operator", operator);
        model.put("credential", credential);
        return model;
    }

    protected void setUp() throws Exception {
        operator = OperatorTestSupport.createOperator();
        operator.setOperatorName("OPERATOR");
        credential = AuthenticationTestSupport.createCredential();
        credential.getIdentity().setPrincipal("PRINCIPAL");
        credential.setPassword("PASSWORD");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
