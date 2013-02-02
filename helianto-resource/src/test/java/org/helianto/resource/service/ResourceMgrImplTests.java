package org.helianto.resource.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.form.ResourceGroupForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceMgrImplTests {
	
	@Test
	public void findResourceGroups() {
		List<ResourceGroup> resourceGroupList = new ArrayList<ResourceGroup>();
		ResourceGroupForm form = EasyMock.createMock(ResourceGroupForm.class);
		
		expect(resourceGroupDao.find(EasyMock.isA(Filter.class))).andReturn(resourceGroupList);
		replay(resourceGroupDao);
		replay(form);
		
		assertSame(resourceGroupList, resourceMgr.findResourceGroups(form));
		verify(resourceGroupDao);
	}
	
	@Test
	public void storeResourceGroup() {
		ResourceGroup resourceGroup = new ResourceGroup();
		
		resourceGroupDao.saveOrUpdate(resourceGroup);
		replay(resourceGroupDao);
		
		assertSame(resourceGroup, resourceMgr.storeResourceGroup(resourceGroup));
		verify(resourceGroupDao);
	}
	
	// collabs
	
	private ResourceMgrImpl resourceMgr;
	private FilterDao<ResourceGroup> resourceGroupDao;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		resourceMgr = new ResourceMgrImpl();
		resourceGroupDao = createMock(FilterDao.class);
		resourceMgr.setResourceGroupDao(resourceGroupDao);
	}
	
	@After
	public void tearDown() {
		reset(resourceGroupDao);
	}
	


}
