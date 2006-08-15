package org.helianto.process.service;

import static org.easymock.EasyMock.createMock;
import junit.framework.TestCase;

import org.helianto.core.dao.PartnerDao;

public class ResourceMgrImplTests extends TestCase {
	
	// class (interface) under test
	private ResourceMgrImpl resourceMgr;
	
	public void test() {
		
	}
	
	@Override
	public void setUp() {
		resourceMgr = new ResourceMgrImpl();
		partnerDaoMock = createMock(PartnerDao.class);
		resourceMgr.setPartnerDao(partnerDaoMock);
	}
	
	private PartnerDao partnerDaoMock;


}
