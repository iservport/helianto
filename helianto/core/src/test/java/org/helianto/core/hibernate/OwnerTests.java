//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Owner;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Owner domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OwnerTests extends AbstractCoreTest {
    
    public OwnerTests() {
        setBaseQuery("from Owner");
    }

    public Object first() {
        Owner owner = (Owner) getCollaborator(Owner.class);
        return owner;
    }

    public Object changeUniqueKey(Object object) {
        ((Owner) object).setOwnerName(generateKey());
        return object;
    }

    public Object third() {
        Owner parent = (Owner) getCollaborator(Owner.class);
        Owner owner = (Owner) getNewCollaborator(Owner.class);
        owner.setParent(parent);
        return owner;
    }

}
