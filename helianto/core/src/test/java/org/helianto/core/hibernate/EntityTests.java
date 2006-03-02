//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Entity;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Owner domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EntityTests extends AbstractCoreTest {
    
    public EntityTests() {
        setKeyType(Long.class);
        setBaseQuery("from Entity");
    }

    public Object first() {
        Entity obj = (Entity) getCollaborator(Entity.class);
        return obj;
    }

    public Object changeUniqueKey(Object object) {
        ((Entity) object).setAlias(generateKey());
        return object;
    }

    public Object third() {
        Entity obj = (Entity) getNewCollaborator(Entity.class);
        return obj;
    }

}
