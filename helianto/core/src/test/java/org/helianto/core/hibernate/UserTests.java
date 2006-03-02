//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Owner domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserTests extends AbstractCoreTest {
    
    public UserTests() {
        setKeyType(Long.class);
        setBaseQuery("from User");
    }

    public Object first() {
        User obj = (User) getCollaborator(User.class);
        return obj;
    }

    public Object changeUniqueKey(Object object) {
        User user = (User) object;
        user.setCredential((Credential) getNewCollaborator(Credential.class));
        logger.info("\n         Unique key changed, [ " +
                user.toString()+
                " details [ credential = "+user.getCredential()+
                ", entity = "+user.getEntity()+
                " ]]");
        return user;
    }

    public Object third() {
        User parent = (User) getNewCollaborator(User.class);
        User user = (User) getNewCollaborator(User.class);
        user.setParent(parent);
        return user;
    }

}
