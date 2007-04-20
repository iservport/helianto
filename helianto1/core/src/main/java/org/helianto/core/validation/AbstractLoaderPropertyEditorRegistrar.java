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

package org.helianto.core.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.PropertyEditorRegistrar;

/**
 * A base class to <code>PropertyEditorRegistrar</code> that
 * inject an <code>PropertyLoader</code> via <code>CustomPropertyEditor</code>
 * constructors.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractLoaderPropertyEditorRegistrar implements PropertyEditorRegistrar {

    private PropertyLoader propertyLoader;
    
    public void setPropertyLoader(PropertyLoader propertyLoader) {
        this.propertyLoader = propertyLoader;
    }

    public PropertyLoader getPropertyLoader() {
        return propertyLoader;
    }
    
    public void init() {
        if (propertyLoader==null) {
            throw new IllegalArgumentException("PropertyLoader requierd...");
        }
    }

    public static final Log logger = LogFactory.getLog(AbstractLoaderPropertyEditorRegistrar.class);

}
