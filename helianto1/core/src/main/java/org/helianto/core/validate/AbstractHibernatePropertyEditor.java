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

package org.helianto.core.validate;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateOperations;

/**
 * A base class to <code>HibernateTemplate</code> backed property editors.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractHibernatePropertyEditor extends
        PropertyEditorSupport {

    private HibernateOperations hibernateTemplate;
    
    protected AbstractHibernatePropertyEditor(HibernateOperations hibernateTemplate) {
        setHibernateTemplate(hibernateTemplate);
    }
    
    private AbstractHibernatePropertyEditor() {}

    protected HibernateOperations getHibernateTemplate() {
        return hibernateTemplate;
    }

    private final void setHibernateTemplate(HibernateOperations hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
    protected void setAsText(String id, Class clazz) {
        if (logger.isDebugEnabled()) {
            logger.debug("Loaded "+clazz.getName()+" property editor");
        }
        try {
            Serializable key = resolveId(id);
            Object value = getHibernateTemplate().load(clazz, key);
            super.setValue(value);
            if (logger.isDebugEnabled()) {
                logger.debug("Loaded property: "+value);
            }
        } catch (Exception e) {
            super.setValue(null);
        }
    }
    
    protected Serializable resolveId(String id) {
        int value = Integer.parseInt(id);
        return value;
    }
    
    static protected final Log logger = LogFactory.getLog(AbstractHibernatePropertyEditor.class);

}
