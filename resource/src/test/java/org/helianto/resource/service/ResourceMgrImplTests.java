package org.helianto.resource.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.helianto.core.dao.FilterDao;
import org.helianto.resource.ResourceAssociation;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceGroupFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ResourceMgrImplTests {
	
	@Test
	public void testFindResourceGroups() {
		List<ResourceGroup> resourceGroupList = new ArrayList<ResourceGroup>();
		ResourceGroupFilter filter = new ResourceGroupFilter();
		
		expect(resourceGroupDao.find(filter)).andReturn(resourceGroupList);
		replay(resourceGroupDao);
		
		assertSame(resourceGroupList, resourceMgr.findResourceGroups(filter));
		verify(resourceGroupDao);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void testPrepareResourceGroup() {
		ResourceGroup resourceGroup = new ResourceGroup();
		ResourceGroup managedResourceGroup = new ResourceGroup() {
			@Override public Set<ResourceAssociation> getChildAssociations() {
				setId(1);
				return null;
			}
		};
		
		expect(resourceGroupDao.merge(resourceGroup)).andReturn(managedResourceGroup);
		resourceGroupDao.evict(resourceGroup);
		replay(resourceGroupDao);
		
		assertSame(managedResourceGroup, resourceMgr.prepareResourceGroup(resourceGroup));
		assertEquals(1, managedResourceGroup.getId());
		verify(resourceGroupDao);
	}
	
	@Test
	public void testStoreResourceGroup() {
		ResourceGroup resourceGroup = new ResourceGroup();
		ResourceGroup managedResourceGroup = new ResourceGroup();
		
		expect(resourceGroupDao.merge(resourceGroup)).andReturn(managedResourceGroup);
		replay(resourceGroupDao);
		
		assertSame(managedResourceGroup, resourceMgr.storeResourceGroup(resourceGroup));
		verify(resourceGroupDao);
	}
	
	// collabs
	
	private ResourceMgrImpl resourceMgr;
	private FilterDao<ResourceGroup, ResourceGroupFilter> resourceGroupDao;
	
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
