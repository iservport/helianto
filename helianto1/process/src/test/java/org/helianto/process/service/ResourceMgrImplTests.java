package org.helianto.process.service;

import static org.easymock.EasyMock.createMock;

import org.helianto.partner.dao.PartnerDao;

import junit.framework.TestCase;

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
