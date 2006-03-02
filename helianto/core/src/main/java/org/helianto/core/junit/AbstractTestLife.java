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

package org.helianto.core.junit;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * A base test class to domain objects beeing persisted with the 
 * <code>GenericDao</code> interface. 
 * 
 * <p>
 * Life cycle tests derived from this base class involve creation, 
 * load, unique key enforcement, update and removal of instances.
 * </p>
 * 
 * <p>
 * Classes under test should be POJO's implementing domain objects
 * having an id field as primary key and must implement a getter 
 * using a getId() method by default. Subclasses constructors 
 * should set the appropriate class of the id using {@link #setKeyType(Class)}
 * appropriately.
 * </p>
 *    
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public abstract class AbstractTestLife extends AbstractTestBase {
    
    private boolean supressKeyViolationTest = false;
    
    private Collection removeList = new ArrayList();
    
    private String baseQuery = "";
    
    /**
     * A local instance.
     */
    protected Object instance;
    
    private Class keyType = Integer.class;
    
    /**
     * A method to access an instance of the object under
     * test.
     */
    public Object getInstance() {
        return instance;
    }
    
    /**
     * @param instance the local instance to set.
     */
    protected void setInstance(Object instance) {
        this.instance = instance;
        logger.info("\n         Instance is now "+this.instance.toString());
    }
    
    protected void setBaseQuery(String baseQuery) {
        this.baseQuery = baseQuery;
    }
    
    protected String getBaseQuery() {
        return baseQuery;
    }
    
    protected void setKeyType(Class keyType) {
        this.keyType = keyType;
    }
    
    protected void setSupressKeyViolationTest(boolean sup) {
        this.supressKeyViolationTest = sup;
    }
    
    /**
     * Test method to encompass all phases of a domain
     * object life cycle. Subclasses should override the 
     * appropriate hooks to perform actual object creation.
     * 
     * @throws Exception
     */
    public void testLife() throws Exception {

        logger.info("\n======== TEST LIFE CYCLE\n");
        
        logger.info("\n======== TEST BEGIN Merge 1st instance "+getBaseQuery());
        setInstance(first());
        getGenericDao().merge(getInstance());
        assertNotNull(extractId(getInstance()));
        logger.info("\n         TEST END merge 1st instance\n");
        
        logger.info("\n======== TEST BEGIN Load copy "+getBaseQuery());
        Serializable id = (Serializable) extractId(getInstance());
        Object copy = getGenericDao().load(getInstance().getClass(), id);
        logger.info("\n         Copy of first instance loaded "+copy);
        addToBeRemoved(copy);
        assertEquals(extractId(copy), id);
        logger.info("\n         TEST END Load copy\n");

        logger.info("\n======== TEST BEGIN Try to violate uniqueness "+getBaseQuery());
        setIdToNull(getInstance(), keyType);
        logger.info("\n         TEST first instance now             "+getInstance());
        assertNull(extractId(getInstance()));
        try {
            getGenericDao().merge(getInstance());
            if (!supressKeyViolationTest) {
                fail("Expected DataIntegrityViolationException");
            }
        } catch (DataIntegrityViolationException e) {
            logger.info("\n         TEST Violated as expected "+e.toString());
            assertNotNull(e);
        }
        logger.info("\n         TEST END Try to violate uniqueness\n");

        logger.info("\n======== TEST BEGIN Create 2nd instance "+getBaseQuery());
        setInstance(changeUniqueKey(getInstance()));
        getGenericDao().merge(getInstance());
        logger.info("\n         Instance is now "+getInstance().toString());
        assertNotNull(extractId(getInstance()));
        logger.info("\n         TEST END Create 2nd with key changed\n");

        logger.info("\n======== TEST BEGIN Update 2nd instance "+getBaseQuery());
        setInstance(changeUniqueKey(getInstance()));
        getGenericDao().merge(getInstance());
        logger.info("\n         Instance is now "+getInstance().toString());
        assertNotNull(extractId(getInstance()));
        addToBeRemoved(getInstance());
        logger.info("\n         TEST END Update 2nd instance with key changed\n");

        logger.info("\n======== TEST BEGIN Create third instance "+getBaseQuery());
        setInstance(third());
        getGenericDao().merge(getInstance());
        logger.info("\n         Instance is now "+getInstance().toString());
        assertNotNull(extractId(getInstance()));
        logger.info("\n         TEST END Create third instance\n");

        logger.info("\n======== TEST BEGIN Create fourth instance "+getBaseQuery());
        setInstance(first());
        setIdToNull(getInstance(), keyType);
        changeUniqueKey(getInstance());
        getGenericDao().merge(getInstance());
        logger.info("\n         <b>Instance is now</b> "+getInstance().toString());
        assertNotNull(extractId(getInstance()));
        logger.info("\n         TEST END Create fourth instance\n");

        logger.info("\n======== TEST BEGIN Delete");
        Serializable key = (Serializable) extractId(getInstance()); 
        getGenericDao().remove(getInstance());
        try {
            getGenericDao().load(getInstance().getClass(), key);
            fail("Expect HibernateException");
        } catch (Exception e) {
            logger.info("\n        Expected exception "+e.toString());
            assertNotNull(e);
        }
        logger.info("\n         TEST END Delete\n");

        logger.info("\n======== TEST Delete all "+removeList.size());
        for (Iterator it = removeList.iterator(); it.hasNext();) {
            logger.info(extractId(it.next()));
        }
        getGenericDao().remove(removeList);

        logger.info("\n======== TEST LIFE CYCLE SUCCESSFUL\n");

    }
    
    @SuppressWarnings("unchecked")
    private void addToBeRemoved(Object object) {
        logger.info("\n         cached instance "+object);
        removeList.add(object);
    }
    
	public Object extractId(Object object) throws Exception {
        Object id = object
            .getClass()
            .getMethod("getId", (Class[]) null)
            .invoke(object, (Object[]) null); 
	    return id;
	}
    
    public boolean setIdToNull(Object object, Class keyTpe) {
        boolean success = true;
        try {
            object.getClass().getMethod("setId", keyTpe).invoke(object, new Object[] {null});
        } catch (IllegalArgumentException e) {
            success = false;
            e.printStackTrace();
        } catch (SecurityException e) {
            success = false;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            success = false;
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            success = false;
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }
	
    public abstract Object getCollaborator(Class clazz);
    
    public abstract Object getNewCollaborator(Class clazz);
    
    public abstract Object first();
    
    public abstract Object changeUniqueKey(Object object);
  
    public abstract Object third(); 
    
    protected Object createCollaborator(Class clazz) {
        throw new RuntimeException("Override this method to get a collaborator");
    }
    
}
