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
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.form.ResourceGroupForm;
import org.helianto.resource.repository.ResourceGroupRepository;
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
		
		expect(resourceGroupRepository.find(EasyMock.isA(Filter.class))).andReturn(resourceGroupList);
		replay(resourceGroupRepository);
		replay(form);
		
		assertSame(resourceGroupList, resourceMgr.findResourceGroups(form));
		verify(resourceGroupRepository);
	}
	
	@Test
	public void storeResourceGroup() {
		ResourceGroup resourceGroup = new ResourceGroup();
		
		expect(resourceGroupRepository.saveAndFlush(resourceGroup)).andReturn(resourceGroup);
		replay(resourceGroupRepository);
		
		assertSame(resourceGroup, resourceMgr.storeResourceGroup(resourceGroup));
		verify(resourceGroupRepository);
	}
	
	// collabs
	
	private ResourceMgrImpl resourceMgr;
	private ResourceGroupRepository resourceGroupRepository;
	
	@Before
	public void setUp() {
		resourceMgr = new ResourceMgrImpl();
		resourceGroupRepository = createMock(ResourceGroupRepository.class);
		resourceMgr.setResourceGroupRepository(resourceGroupRepository);
	}
	
	@After
	public void tearDown() {
		reset(resourceGroupRepository);
	}
	


}
