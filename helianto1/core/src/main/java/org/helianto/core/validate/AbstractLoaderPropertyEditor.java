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

/**
 * Abstract base class to <code>PropertyLoader</code> backed property editors.
 * 
 * <p>Use to replace <code>AbstractHibernatePropertyEditor</code> if the
 * <code>getAsText()</code> method needs to participate in a transaction.
 * Notice that the service facade that controls the transaction must implement 
 * <code>PropertyLoader</code>.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractLoaderPropertyEditor extends
        PropertyEditorSupport {

    private PropertyLoader propertyLoader;
    
    /**
     * Required constructor.
     * 
     * @param propertyLoader
     */
    protected AbstractLoaderPropertyEditor(PropertyLoader propertyLoader) {
        setPropertyLoader(propertyLoader);
    }
    
    /**
     * Default constructor.
     * 
     * @param propertyLoader
     */
    private AbstractLoaderPropertyEditor() {}

    /**
     * <code>PropertyLoader</code> getter.
     */
    public PropertyLoader getPropertyLoader() {
        return propertyLoader;
    }

    /**
     * <code>PropertyLoader</code> setter.
     */
    public void setPropertyLoader(PropertyLoader propertyLoader) {
        this.propertyLoader = propertyLoader;
    }

    /**
     * Loads the object having the supplied key (id) and
     * sets it as the <code>PropertyEditor</code> value.
     * 
     * @param id
     * @param clazz
     */
    protected void setAsText(String id, Class clazz) {
        if (logger.isDebugEnabled()) {
            logger.debug("Loaded "+clazz.getName()+" property editor");
        }
        try {
            Serializable key = resolveId(id);
            Object value = getPropertyLoader().load(clazz, key);
            super.setValue(value);
            if (logger.isDebugEnabled()) {
                logger.debug("Loaded property: " + getValue()+" set from id="+id);
            }
        } catch (Exception e) {
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
    
    static protected final Log logger = LogFactory.getLog(AbstractHibernatePropertyEditor.class);

}