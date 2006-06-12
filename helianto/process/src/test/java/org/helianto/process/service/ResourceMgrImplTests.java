package org.helianto.process.service;

import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.dao.PartnerDao;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceCreator;

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

public class ResourceMgrImplTests extends TestCase {
	
	// class (interface) under test
	private ResourceMgrImpl resourceMgr;
	
	public void testPrepareResource() {
		
		Entity entity = new Entity();
		ResourceGroup parentGroup = new ResourceGroup();
		parentGroup.setEntity(entity);
		Division division = new Division();
		Resource resource = new Resource();
		
        expect(partnerDaoMock.findCurrentDivision(parentGroup.getEntity()))
        	.andReturn(division);
        replay(partnerDaoMock);

        expect(resourceCreatorMock.resourceFactory(parentGroup, "TEST", division))
        	.andReturn(resource);
        replay(resourceCreatorMock);

        assertSame(resource, resourceMgr.prepareResource(parentGroup, "TEST"));
		
        verify(partnerDaoMock);
		verify(resourceCreatorMock);
		
	}
	
	@Override
	public void setUp() {
		resourceMgr = new ResourceMgrImpl();
		partnerDaoMock = createMock(PartnerDao.class);
		resourceMgr.setPartnerDao(partnerDaoMock);
		resourceCreatorMock = createMock(ResourceCreator.class);
		resourceMgr.setResourceCreator(resourceCreatorMock);
	}
	
	private PartnerDao partnerDaoMock;
	private ResourceCreator resourceCreatorMock;


}
