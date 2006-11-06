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
import java.util.Map;

import org.helianto.core.mail.compose.DecoratedPreparator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class TemplateMailMessageDecorator extends DecoratedPreparator {
    
    public TemplateMailMessageDecorator(DecoratedPreparator parent, Configuration configuration) {
        super(parent.getSenderIdentity(), parent.getRecipientIdentity());
        this.configuration = configuration;
        model = new HashMap<String, Object>();
    }
    
    private Configuration configuration;
    public void addToModel(String key, Object value) {
        model.put(key, value);
    }
    
    public void compose(String templateName) {
        try {
            Template template = 
                configuration.getTemplate(templateName);
            StringWriter result = new StringWriter();
            template.process(model, result);
            getBody().append(result.toString());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to open template "+templateName, e);
        } catch (TemplateException e) {
            throw new IllegalStateException("Ivalid template "+templateName, e);
        }
    }

    public Map<String, Object> getModel() {
        return model;
    }
    
}
