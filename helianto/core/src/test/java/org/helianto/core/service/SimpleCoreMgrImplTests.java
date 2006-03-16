package org.helianto.core.service;

import org.helianto.core.DefaultEntity;

public class SimpleCoreMgrImplTests extends AbstractCoreTest {
	
	private SimpleCoreMgr simpleCoreMgr;
	
	public void setSimpleCoreMgr(SimpleCoreMgr simpleCoreMgr) {
		this.simpleCoreMgr = simpleCoreMgr;
	}

	public void testCreateDefaultEntitySuccess() {
		
		DefaultEntity defaultEntity = simpleCoreMgr.createDefaultEntity("TEST");
		simpleCoreMgr.persistDefaultEntity(defaultEntity);
		
	}


}
