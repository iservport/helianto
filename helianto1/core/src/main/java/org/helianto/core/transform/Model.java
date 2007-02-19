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

package org.helianto.core.transform;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple value object to hold both a model map and a template name.
 */
public class Model {
    
    private Map<String, Object> model;
    private String templateName;
    
    /**
     * Constructs the model having the supplied
     * object class name as key.
     * 
     * @param object
     */
    public Model(Object object) {
        this(object, object.getClass().getSimpleName());
    }
    
    /**
     * Constructs the model having the supplied
     * key.
     * 
     * @param object
     * @param key
     */
    public Model(Object object, String key) {
        if (object!=null) {
            getRequiredModel().put(key, object);
        }
    }
    
    /**
     * @return is never null.
     */
    public Map<String, Object> getRequiredModel() {
        if (model==null) {
            model = new HashMap<String, Object>();
        }
        return model;
    }

    /**
     * @param model
     */
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    /**
     * @return
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

}
