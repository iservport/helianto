package org.helianto.user.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.helianto.core.Programmable;
import org.helianto.core.def.CreateIdentity;
import org.helianto.core.def.UserState;
import org.helianto.core.domain.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>UserGroup</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupTests {
	
	@Test
	public void contructor() {
		UserGroup userGroup = new UserGroup();
		assertTrue(userGroup instanceof Serializable);
		assertTrue(userGroup instanceof Comparable<?>);
		assertEquals('G', userGroup.getDiscriminator());
		assertEquals("", userGroup.getUserKey());
		assertTrue(userGroup.getLastEvent() instanceof Date);
    	assertEquals(UserState.ACTIVE.getValue(), userGroup.getUserState());
    	assertTrue(userGroup.isAccountNonExpired());
    	assertEquals(CreateIdentity.REJECT.getValue(), userGroup.getCreateIdentity());
	}
    
	@Test
	public void entityContructor() {
		Entity entity = new Entity();
		UserGroup userGroup = new UserGroup(entity);
		assertSame(entity, userGroup.getEntity());
	}
    
    /**
     * Test <code>UserGroup</code> static factory method.
     */
	@Test
    public void userGroupFactory() {
        Entity entity = new Entity();
        
        UserGroup userGroup = new UserGroup(entity, "userKey");
        
        assertSame(entity, userGroup.getEntity());
        assertEquals("userKey", userGroup.getUserKey());
        
    }
    
    /**
     * Test <code>UserGroup</code> equals() method.
     */
	@Test
    public void userGroupEquals() {
        Entity entity = new Entity();
        
        UserGroup userGroup = new UserGroup(entity, "userKey");
        UserGroup copy = (UserGroup) DomainTestSupport.minimalEqualsTest(userGroup);
        
        copy.setEntity(null);
        copy.setUserKey("userKey");
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setUserKey("");
        assertFalse(userGroup.equals(copy));

        copy.setEntity(entity);
        copy.setUserKey("userKey");

        assertTrue(userGroup.equals(copy));
    }
	
	@Test
	public void programmable() {
		Entity entity = new Entity();
		
		Programmable userGroup = new UserGroup(entity, "userKey");
		((UserGroup) userGroup).setScriptItems("'CODE1', 'CODE2'");
		assertEquals(2, userGroup.getScriptItemsAsArray().length);
		assertEquals("'CODE1'", userGroup.getScriptItemsAsArray()[0]);
		assertEquals("'CODE2'", userGroup.getScriptItemsAsArray()[1]);
		
		List<String> scriptList = Arrays.asList(new String[] { "Script 1 content", "Script 2 content" });
		((UserGroup) userGroup).setScriptList(scriptList);
		assertTrue(userGroup.getScriptList().contains("Script 1 content"));
		assertTrue(userGroup.getScriptList().contains("Script 2 content"));
	}

}
    
    
