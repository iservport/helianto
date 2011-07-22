package org.helianto.web;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.SimpleModel;
import org.helianto.web.action.impl.IdentityActionImpl;
import org.helianto.web.test.AbstractFlowTest;
import org.junit.Test;
import org.springframework.webflow.test.MockFlowBuilderContext;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentityFlowTests extends AbstractFlowTest {
	
	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
	    builderContext.registerBean("identityAction", identityAction);
	}
	
	@Test
	public void testStart() {
		doEditStartTest();
	}
	
	public void testStoreIdentity() {
	    setCurrentState("edit.view");
	    Identity form = new Identity("");
	    getFlowScope().put("identity", form);
	    getFlowScope().put("identityModel", new SimpleModel<Identity>(form, new User()));

	    getContext().setEventId("storeIdentity");
	    
	    Identity identity = new Identity("");
	    
	    EasyMock.expect(identityMgr.storeIdentity(identity)).andReturn(identity);
	    EasyMock.replay(identityMgr);
	    
	    resumeFlow(getContext());

	    assertFlowExecutionEnded();
	}
	
	// locals
	
	private IdentityActionImpl identityAction;
	private IdentityMgr identityMgr;
	
	@Override
	protected void doSetup() {
		identityAction = new IdentityActionImpl();
		identityMgr = EasyMock.createMock(IdentityMgr.class);
		identityAction.setIdentityMgr(identityMgr);
		identityAction.setActionNamingConventionStrategy(createActionNamingConventionStrategy("identity"));
	}
	
	@Override
	protected void doTearDown() {
		EasyMock.reset(identityMgr);
	}

}
