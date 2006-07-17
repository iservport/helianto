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

import org.helianto.core.validate.AbstractHibernatePropertyEditor;
import org.helianto.core.validate.AbstractPropertyEditorRegistrar;
import org.helianto.process.Unit;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.orm.hibernate3.HibernateOperations;

public class UnitPropertyEditorRegistrar extends
        AbstractPropertyEditorRegistrar {

    public void registerCustomEditors(PropertyEditorRegistry registry) {
        PropertyEditor unitPropertyEditor = new UnitPropertyEditor(getHibernateTemplate());
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+unitPropertyEditor);
        }
        registry.registerCustomEditor(Unit.class, unitPropertyEditor);
    }
    
    /**
     * Default hibernate backed <code>Unit</code> property editor.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class UnitPropertyEditor extends AbstractHibernatePropertyEditor {
        
        public UnitPropertyEditor(HibernateOperations hibernateTemplate) {
            super(hibernateTemplate);
        }
        @Override
        public String getAsText() {
            return String.valueOf(((Unit) getValue()).getUnitCode());
        }
        @Override
        public void setAsText(String id) throws IllegalArgumentException {
            setAsText(id, Unit.class);
        }

    }
    
}
