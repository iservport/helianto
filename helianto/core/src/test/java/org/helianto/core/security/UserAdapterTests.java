//Created on 18/08/2005
package org.helianto.core.security;

import java.util.HashSet;
import java.util.Set;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.context.SecurityContext;
import net.sf.acegisecurity.context.SecurityContextHolder;
import net.sf.acegisecurity.context.SecurityContextImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Role;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.hibernate.CollaboratorTestFactory;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

public class UserAdapterTests extends MockObjectTestCase {

    private final Log logger = LogFactory.getLog(CustomDaoImplTests.class);
    
    private UserAdapter ua;
    
    private Credential cred;

    private CollaboratorTestFactory factory = new CollaboratorTestFactory();
    
    public void setUp() {
        cred = (Credential) factory.getCollaborator(Credential.class);
    }
    
    public void testRetrievePublicUserDetailsFromSecurityContext() {
        SecurityContext sc = new SecurityContextImpl();
        SecurityContextHolder.setContext(sc);
    }
    
    @SuppressWarnings("unchecked")
    public void testUserAdapter() {

        // create an adapter using the class member credential
        ua = new UserAdapter(cred);
        assertNotNull(ua);
        assertSame(cred.getPersonalData(), ua.getPersonalData());
        logger.info("\n         UserAdapter created");
        
        // add an user to the credential
        // retrive the entity created with the user
        User u1 = (User) factory.getCollaborator(User.class);
        u1.setId(new Long(1));
        Entity e1 = u1.getEntity();
        e1.setId(new Long(1));
        assertNotNull(e1);
        logger.info("\n         Entity for user 1 is "+e1.getAlias());
        u1.setCredential(cred);
        // credential and user have a bidi relation
        Set users = new HashSet();
        users.add(u1);
        cred.setUsers(users);
        // re-create the adapter
        // and retrieve the entity from the adapter
        ua = new UserAdapter(cred);
        assertNotNull(ua);
        assertSame(e1, ua.getEntity());
        logger.info("\n         Current user set");
        
        // add a role to the user
        Role r1 = (Role) factory.getCollaborator(Role.class);
        Service s1 = r1.getService();
        assertNotNull(s1);
        logger.info("\n         Service for role 1 is "+s1.getServiceName());
        r1.setUser(u1);
        // role and user have a bidi relation
        Set roles = new HashSet();
        roles.add(r1);
        u1.setRoles(roles);
        // re-create the adapter
        // and retrieve authoriies from the adapter
        ua = new UserAdapter(cred);
        assertNotNull(ua);
        GrantedAuthority[] authorities = ua.getAuthorities();
        assertNotNull(authorities);
        assertEquals(r1.getRoleName(), authorities[0].getAuthority());
        logger.info("\n         Authority granted "+r1.getRoleName());
        
        // add a second user to the credential
        User u2 = (User) factory.getNewCollaborator(User.class);
        u2.setId(new Long(2));
        Entity e2 = u2.getEntity();
        e2.setId(new Long(2));
        assertNotNull(e2);
        logger.info("\n         Entity for user 2 ["+e2.getId()+"] is "+e2.getAlias());
        u2.setCredential(cred);
        // credential and user have a bidi relation
        users.add(u2);
        assertEquals(2, users.size());
        cred.setUsers(users);
        // re-create the adapter
        ua = new UserAdapter(cred);
        assertNotNull(ua);
        assertNull(ua.getEntity());
        logger.info("\n         Current user is null because there's more than one");
        
        // set an entity to
        // resolve the current user through the entity
//        ua.setCurrentEntity(e2);
//        assertSame(e2, ua.getCurrentEntity());
//        authorities = ua.getAuthorities();
//        assertEquals(1, authorities.length);
//        logger.info("\n         Current entity set");
        
        // set a different entity
//        ua.setCurrentEntity(e1);
//        assertSame(e1, ua.getCurrentEntity());
//        authorities = ua.getAuthorities();
//        assertNotNull(authorities);
//        logger.info("\n         Current entity changed");
        
    }
}
