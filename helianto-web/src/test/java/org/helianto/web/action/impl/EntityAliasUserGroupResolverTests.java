package org.helianto.web.action.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
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
		
		
		userGroupDao.find(EasyMock.isA(Filter.class));
		EasyMock.expectLastCall().andReturn(userGroupList);
		EasyMock.replay(userGroupDao);
		
		assertSame(userGroup, resolver.resolveUserGroup("ALIAS"));
		EasyMock.verify(userGroupDao);
		
	}
	
	@Test
	public void createFilter() {
		Filter filter = resolver.new UserGroupAliasFilter("ALIAS");
		
		assertEquals("alias.entity.alias = 'ALIAS' AND alias.userKey = 'USER' ", filter.createCriteriaAsString());
	}
	
	private EntityAliasUserGroupResolver resolver;
	private FilterDao<UserGroup> userGroupDao;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		resolver = new EntityAliasUserGroupResolver();
		userGroupDao = EasyMock.createMock(FilterDao.class);
		resolver.setUserGroupDao(userGroupDao);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(userGroupDao);
	}

}
