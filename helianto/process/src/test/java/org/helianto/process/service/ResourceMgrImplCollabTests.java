package org.helianto.process.service;

public class ResourceMgrImplCollabTests extends AbstractProcessTest {
	
	private ResourceMgr resourceMgr;
	
	public void testCollaborators() {
		
		try {
			resourceMgr.createResource(null, "");
			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
		try {
			resourceMgr.createResource(null, "", null);
			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
		try {
			resourceMgr.createSubGroup(null, "");
			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
		try {
			resourceMgr.findResourceByEntity(null);
			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
		try {
			resourceMgr.findResourceByParent(null);
			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
		try {
			resourceMgr.findRootResourceByEntity(null);
			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
		try {
			resourceMgr.installEquipmentTree(null, "");
			// FIXME 
//			fail();
		} catch (Exception e) {
			logger.info("\n"+e.toString());
		}
		
	}

	public void setResourceMgr(ResourceMgr resourceMgr) {
		this.resourceMgr = resourceMgr;
	}

}
