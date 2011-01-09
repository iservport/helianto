package org.helianto.resource.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.helianto.core.repository.FilterDao;
import org.helianto.resource.ResourceAssociation;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.filter.classic.ResourceGroupFilter;
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
		ResourceGroupFilter filter = new ResourceGroupFilter();
		
		expect(resourceGroupDao.find(filter)).andReturn(resourceGroupList);
		replay(resourceGroupDao);
		
		assertSame(resourceGroupList, resourceMgr.findResourceGroups(filter));
		verify(resourceGroupDao);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void prepareResourceGroup() {
		ResourceGroup resourceGroup = new ResourceGroup();
		final Set<ResourceAssociation> childAssociations = new HashSet<ResourceAssociation>();
		ResourceAssociation child = new ResourceAssociation();
		childAssociations.add(child);
		final Set<ResourceAssociation> parentAssociations = new HashSet<ResourceAssociation>();
		ResourceAssociation parent = new ResourceAssociation();
		parentAssociations.add(parent);
		ResourceGroup managedResourceGroup = new ResourceGroup() {
			@Override public Set<ResourceAssociation> getChildAssociations() {
				return childAssociations;
			}
			@Override public Set<ResourceAssociation> getParentAssociations() {
				return parentAssociations;
			}
		};
		
		expect(resourceGroupDao.merge(resourceGroup)).andReturn(managedResourceGroup);
		resourceGroupDao.evict(resourceGroup);
		replay(resourceGroupDao);
		
		assertSame(managedResourceGroup, resourceMgr.prepareResourceGroup(resourceGroup));
		assertSame(child, managedResourceGroup.getChildAssociationList().get(0));
		assertSame(parent, managedResourceGroup.getParentAssociationList().get(0));
		verify(resourceGroupDao);
	}
	
	@Test
	public void storeResourceGroup() {
		ResourceGroup resourceGroup = new ResourceGroup();
		ResourceGroup managedResourceGroup = new ResourceGroup();
		
		expect(resourceGroupDao.merge(resourceGroup)).andReturn(managedResourceGroup);
		replay(resourceGroupDao);
		
		assertSame(managedResourceGroup, resourceMgr.storeResourceGroup(resourceGroup));
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
