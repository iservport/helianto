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

import java.beans.PropertyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.KeyType;
import org.helianto.core.Service;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * Basic <code>PropertyLoader</code> backed property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class BasicPropertyEditorRegistrar extends
        AbstractLoaderPropertyEditorRegistrar {

    public void registerCustomEditors(PropertyEditorRegistry registry) {

        // KeyType
        PropertyEditor KeyTypePropertyEditor = new KeyTypePropertyEditor(
                getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor " + KeyTypePropertyEditor);
        }
        registry.registerCustomEditor(KeyType.class, KeyTypePropertyEditor);

        // Service
        PropertyEditor ServicePropertyEditor = new ServicePropertyEditor(
                getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor " + ServicePropertyEditor);
        }
        registry.registerCustomEditor(Service.class, ServicePropertyEditor);

    }

    public static final Log logger = LogFactory
            .getLog(BasicPropertyEditorRegistrar.class);

}