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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract base class to <code>EntityManager</code> backed property editors.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractJpaPropertyEditor extends PropertyEditorSupport {

    /**
     * Default constructor.
     */
	public AbstractJpaPropertyEditor() {
		super();
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
            Object value = this.em.getReference(clazz, key);
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
    
    // collabs 
    
	private EntityManager em;
    
    /**
     * Spring will inject a managed JPA {@link EntityManager} into this field.
     */
    @PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
    
    static protected final Log logger = LogFactory.getLog(AbstractJpaPropertyEditor.class);

}
