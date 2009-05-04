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
package org.helianto.support.validation;

import java.beans.PropertyEditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.validation.AbstractLoaderPropertyEditorRegistrar;
import org.helianto.support.Server;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * <code>Server</code> <code>PropertyLoader</code> backed <code>Address</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerPropertyEditorRegistrar extends AbstractLoaderPropertyEditorRegistrar {

	public void registerCustomEditors(PropertyEditorRegistry registry) {
		
        // server
        PropertyEditor serverPropertyEditor = new ServerPropertyEditor(getPropertyLoader());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+serverPropertyEditor);
        }
        registry.registerCustomEditor(Server.class, serverPropertyEditor);

    }

    public static final Log logger = LogFactory.getLog(ServerPropertyEditorRegistrar.class);

}
