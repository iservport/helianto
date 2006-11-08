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

package org.helainto.web.view;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import junit.framework.TestCase;

public class PrincipalPageTests extends TestCase {

    private String testPathPrefix = "web/src/main/resources/freemarker/";
    private Configuration configuration;
    private Map<String, Object> model;
    
    public void testSummary() {
        model.put("page", 1);
        StringWriter result = process("identity/summary.ftl", false);
    }
    
//    public void testPrincipal() {
//        model.put("page", 1);
//        StringWriter result = process("identity/principal.ftl", false);
//    }
    
    public StringWriter process(String templateName, boolean visualTest) {
        try {
            Template template = 
                configuration.getTemplate(templateName);
            StringWriter result = new StringWriter();
            template.process(model, result);
            if (visualTest) {
                System.out.println(result.toString());
                fail();
            }
            return result;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to open template "+templateName, e);
        } catch (TemplateException e) {
            throw new IllegalStateException("Ivalid template "+templateName, e);
        }
    }

    public void setUp() throws IOException {
        configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(testPathPrefix));
        configuration.setObjectWrapper(new DefaultObjectWrapper()); 
        model = new HashMap<String, Object>();
  }
    
}
