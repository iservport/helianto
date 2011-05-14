package org.helianto.web;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.SimpleModel;
import org.helianto.web.action.impl.IdentityActionImpl;
import org.junit.Test;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockFlowBuilderContext;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentityFlowTests extends AbstractFlowTest {
	
	@Override
	protected String getRelativePath() {
		return "identity/_identity.xml";
	}

	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
	    builderContext.registerBean("identityAction", identityAction);
	}
	
	@Test
	public void testStart() {
		MutableAttributeMap input = new LocalAttributeMap();
	    
	    startFlow(input, getContext());
	    assertCurrentStateEquals("edit.view");
	    
	    LocalAttributeMap viewScope = (LocalAttributeMap) getRequiredFlowAttribute("viewScope");
	    assertEquals("identity_bar", viewScope.get("sidebar"));
	    assertEquals("identity_form", viewScope.get("template"));
	    assertEquals("identity/", viewScope.get("basePath"));
	    assertEquals("assign", viewScope.get("assign"));
	}
	
	public void testStoreIdentity() {
	    setCurrentState("edit.view");
	    Identity form = new Identity("");
	    getFlowScope().put("identity", form);
	    getFlowScope().put("identityModel", new SimpleModel<Identity>(form, new User()));

	    getContext().setEventId("storeIdentity");
	    
	    Identity identity = new Identity("");
	    
	    EasyMock.expect(userMgr.storeIdentity(identity)).andReturn(identity);
	    EasyMock.replay(userMgr);
	    
	    resumeFlow(getContext());

	    assertFlowExecutionEnded();
	}
	
	// locals
	
	private IdentityActionImpl identityAction;
	private UserMgr userMgr;
	
	@Override
	protected void doSetup() {
		identityAction = new IdentityActionImpl();
		userMgr = EasyMock.createMock(UserMgr.class);
		identityAction.setUserMgr(userMgr);
		identityAction.setActionNamingConventionStrategy(createActionNamingConventionStrategy("identity"));
	}
	
	@Override
	protected void doTearDown() {
		EasyMock.reset(userMgr);
	}

}
