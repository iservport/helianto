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
package org.helianto.process.validation;

import org.helianto.core.validation.AbstractSessionPropertyEditor;
import org.helianto.process.ProcessDocument;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Default <code>SessionFactory</code> backed <code>Process</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("processDocumentPropertyEditor")
public class ProcessDocumentPropertyEditor extends AbstractSessionPropertyEditor {
    
    @Override
    public String getAsText() {
    	ProcessDocument processDocument = (ProcessDocument) getValue();
        return String.valueOf(processDocument.getId());
    }
    
    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setAsText(id, ProcessDocument.class);
    }

}
