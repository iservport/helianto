//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Role;
import org.helianto.core.Service;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Owner domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class RoleTests extends AbstractCoreTest {
    
    public RoleTests() {
        setKeyType(Integer.class);
        setBaseQuery("from Role");
    }

    public Object first() {
        Role obj = (Role) getCollaborator(Role.class);
        return obj;
    }

    public Object changeUniqueKey(Object object) {
        Role role = (Role) object;
        role.setRoleName(generateKey());
        role.setService((Service) getNewCollaborator(Service.class));
        logger.info("\n         Unique key changed, [ " +
                object.toString()+
                " details [ id = "+role.getId()+
                ", roleName = "+role.getRoleName()+
                ", user = "+role.getUser()+
                ", service = "+role.getService()+
                " ]]");
        return role;
    }

    public Object third() {
        Role obj = (Role) getNewCollaborator(Role.class);
        obj.setRoleName("ROLE_USER");
        return obj;
    }

}
