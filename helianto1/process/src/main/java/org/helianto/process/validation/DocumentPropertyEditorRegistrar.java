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

import java.beans.PropertyEditor;
import java.io.Serializable;

import org.helianto.core.validation.AbstractHibernatePropertyEditor;
import org.helianto.core.validation.AbstractPropertyEditorRegistrar;
import org.helianto.document.ExternalDocument;
import org.helianto.process.ProcessDocument;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.orm.hibernate3.HibernateOperations;

public class DocumentPropertyEditorRegistrar extends
        AbstractPropertyEditorRegistrar {

    public void registerCustomEditors(PropertyEditorRegistry registry) {
        PropertyEditor documentPropertyEditor = new DocumentPropertyEditor(getHibernateTemplate());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+documentPropertyEditor);
        }
        registry.registerCustomEditor(ProcessDocument.class, documentPropertyEditor);

        PropertyEditor externalDocumentPropertyEditor = new ExternalDocumentPropertyEditor(getHibernateTemplate());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+externalDocumentPropertyEditor);
        }
        registry.registerCustomEditor(ExternalDocument.class, externalDocumentPropertyEditor);
    }
    
    /**
     * Default hibernate backed <code>Document</code> property editor.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class DocumentPropertyEditor extends AbstractHibernatePropertyEditor {
        
        public DocumentPropertyEditor(HibernateOperations hibernateTemplate) {
            super(hibernateTemplate);
        }
        @Override
        public String getAsText() {
            return String.valueOf(((ProcessDocument) getValue()).getDocCode());
        }
        @Override
        public void setAsText(String id) throws IllegalArgumentException {
            setAsText(id, ExternalDocument.class);
        }

        @Override
        protected Serializable resolveId(String id) {
            return Long.parseLong(id);
        }
        
    }
    
    /**
     * Default hibernate backed <code>ExternalDocument</code> property editor.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class ExternalDocumentPropertyEditor extends AbstractHibernatePropertyEditor {
        
        public ExternalDocumentPropertyEditor(HibernateOperations hibernateTemplate) {
            super(hibernateTemplate);
        }
        @Override
        public String getAsText() {
            return String.valueOf(((ExternalDocument) getValue()).getDocCode());
        }
        @Override
        public void setAsText(String id) throws IllegalArgumentException {
            if (logger.isDebugEnabled()) {
                logger.debug("Loaded ExternalDocument property editor");
            }
            try {
                Object value = getHibernateTemplate().load(ExternalDocument.class, Long.parseLong(id));
                super.setValue(value);
                if (logger.isDebugEnabled()) {
                    logger.debug("Loaded property: "+value);
                }
            } catch (Exception e) {
                super.setValue(null);
            }
        }
        
    }
    
}
