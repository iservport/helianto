package org.helianto.web.action.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.service.UserMgr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class EntityAliasUserGroupResolverTests {
	
	@Test
	public void resolve() {
		List<UserGroup> userGroupList = new ArrayList<UserGroup>();
		UserGroup userGroup = new UserGroup();
		userGroupList.add(userGroup);
		
		
		userMgr.findUsers(EasyMock.isA(Filter.class));
		EasyMock.expectLastCall().andReturn(userGroupList);
		EasyMock.replay(userMgr);
		
		assertSame(userGroup, resolver.resolveUserGroup("ALIAS"));
		EasyMock.verify(userMgr);
		
	}
	
	@Test
	public void createFilter() {
		Filter filter = resolver.createUserFilter("ALIAS");
		
		assertEquals("alias.entity.alias = 'ALIAS' AND alias.userKey = 'USER' ", filter.createCriteriaAsString());
	}
	
	private EntityAliasUserGroupResolver resolver;
	private UserMgr userMgr;
	
	@Before
	public void setUp() {
		resolver = new EntityAliasUserGroupResolver();
		userMgr = EasyMock.createMock(UserMgr.class);
		resolver.setUserMgr(userMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(userMgr);
	}

}
