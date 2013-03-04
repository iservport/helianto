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

package org.helianto.message.transform;

import java.io.IOException;
import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * A class to make Freemarker processing easier.  it works both
 * as a value object, holding
 */
public class MessageProcessor {
    
    private Model model;
    private Configuration configuration;
    
    /**
     * Constructor.
     * 
     * @param configuration
     * @param model
     */
    public MessageProcessor(Configuration configuration, Model model) {
        this.configuration = configuration;
        if (model!=null) {
            this.model = model;
        }
    }
    
    /**
     * Processes the template according to the internal <code>Configuration</code>
     * instance with the current model.
     * 
     * @param templateName
     */
    public String process() {
        try {
            StringWriter result = new StringWriter();
            Template template = configuration.getTemplate(model.getTemplateName());
            template.process(model.getRequiredModel(), result);
            return result.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load "+model.getTemplateName(), e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException("Unable to process "+model.getTemplateName(), e);
        }
    }

}
