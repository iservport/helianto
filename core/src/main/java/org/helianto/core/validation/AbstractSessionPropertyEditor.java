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

import java.beans.PropertyEditorSupport;
import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

/**
 * Abstract base class to <code>SessionFactory</code> backed property editors.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractSessionPropertyEditor extends
        PropertyEditorSupport {

	private SessionFactory sessionFactory;
    
    /**
     * Default constructor.
     */
	public AbstractSessionPropertyEditor() {}

    /**
     * <code>SessionFactory</code> getter.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    /**
     * <code>SessionFactory</code> setter.
     */
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Loads the object having the supplied key (id) and
     * sets it as the <code>PropertyEditor</code> value.
     * 
     * @param id
     * @param clazz
     */
    @SuppressWarnings("unchecked")
	protected void setAsText(String id, Class clazz) {
        if (logger.isDebugEnabled()) {
            logger.debug("Loaded "+clazz.getName()+" property editor");
        }
        try {
            Serializable key = resolveId(id);
            Object value = this.sessionFactory.getCurrentSession().load(clazz, key);
            super.setValue(value);
            if (logger.isDebugEnabled()) {
                logger.debug("Loaded property: " + getValue()+" set from id="+id);
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception caugth during load: " + e.toString());
            }
            super.setValue(null);
        }
    }
    
    /**
     * Method to be overriden if the object key is not
     * an integer.
     * 
     * @param id
     */
    protected Serializable resolveId(String id) {
        int value = Integer.parseInt(id);
        return value;
    }
    
    static protected final Log logger = LogFactory.getLog(AbstractSessionPropertyEditor.class);

}
