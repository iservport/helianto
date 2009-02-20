package org.helianto.core;

import java.util.Set;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.UserGroupTestSupport;

/**
 * <code>UserGroup</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupTests extends TestCase {
    
    /**
     * Test <code>UserGroup</code> static factory method.
     */
    public void testUserGroupFactory() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, identity);
        
        assertSame(entity, userGroup.getEntity());
        assertSame(identity, userGroup.getIdentity());
        
    }
    
    /**
     * Test <code>UserGroup</code> equals() method.
     */
    public void testUserGroupEquals() {
        Entity entity = new Entity();
        Identity identity = new Identity();
        
        UserGroup userGroup = UserGroup.userGroupFactory(entity, identity);
        UserGroup copy = (UserGroup) DomainTestSupport.minimalEqualsTest(userGroup);
        
        copy.setEntity(null);
        copy.setIdentity(identity);
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(null);
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setIdentity(identity);

        assertTrue(userGroup.equals(copy));
    }

    private UserRole[] createUserRoles(UserGroup user, String... extensions) {
        UserRole[] roles = new UserRole[extensions.length];
        for (int i = 0;i<extensions.length;i++) {
            UserRole role = new UserRole();
            Service service = new Service();
            role.setUserGroup(user);
            user.getRoles().add(role);
            role.setService(service);
            role.setServiceExtension(extensions[i]);
            System.out.println(role);
            roles[i] = role;
        }
        return roles;
    }
    
    /**
     * All roles come from the user.
     */
    public void testRolesLocal() {
        UserGroup user =  UserGroupTestSupport.createUserGroup();
        UserRole[] roles = createUserRoles(user, "E1", "E2");
        Set<UserRole> roleSet = user.getAllRoles();
        assertEquals(2, roleSet.size());
        assertTrue(roleSet.contains(roles[0]));
        assertTrue(roleSet.contains(roles[1]));
    }
    
    /**
     * Some roles come from the ancestor.
     */
    public void testRolesFromAncestor() {
        UserGroup user =  UserGroupTestSupport.createUserGroup();
        UserGroup parent = UserGroupTestSupport.createUserGroup();
        UserRole[] roles1 = createUserRoles(user, "E1", "E2");
        UserRole[] roles2 = createUserRoles(parent, "E2", "E3");
        UserAssociation.userAssociationFactory(parent, user);
        Set<UserRole> roleSet = user.getAllRoles();
        assertEquals(4, roleSet.size());
        assertTrue(roleSet.contains(roles1[0]));
        assertTrue(roleSet.contains(roles1[1]));
        assertTrue(roleSet.contains(roles2[0]));
        assertTrue(roleSet.contains(roles2[1]));
    }
    
}
    
    
