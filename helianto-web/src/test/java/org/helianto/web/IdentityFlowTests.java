package org.helianto.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.filter.Filter;
import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.service.UserMgr;
import org.helianto.core.test.SecurityTestSupport;
import org.helianto.web.action.SimpleModel;
import org.helianto.web.action.impl.IdentityActionImpl;
import org.junit.After;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentityFlowTests extends AbstractXmlFlowExecutionTests {

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createClassPathResource("/freemarker/identity/_identity.xml", getClass());
	}
	
	@Override
	protected FlowDefinitionResource[] getModelResources(FlowDefinitionResourceFactory resourceFactory) {
	   return new FlowDefinitionResource[] {
	       resourceFactory.createClassPathResource("/freemarker/_menu.xml", getClass())
	   };
	}
	
	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
	    builderContext.registerBean("identityAction", identityAction);
	}
	
	@Test
	public void testStart() {
		MutableAttributeMap input = new LocalAttributeMap();
	    
	    startFlow(input, context);
	    assertCurrentStateEquals("selection.view");
	    assertEquals("identity/", getRequiredFlowAttribute("basePath"));
	    assertEquals("assign", getRequiredFlowAttribute("assign"));
	    assertEquals("", ((Identity) getRequiredFlowAttribute("identity")).getPrincipal());
	    
	    LocalAttributeMap viewScope = (LocalAttributeMap) getRequiredFlowAttribute("viewScope");
	    assertEquals("identity_bar", viewScope.get("sidebar"));
	    assertEquals("filter", viewScope.get("template"));
	}
	
	@SuppressWarnings("unchecked")
	public void testApplyFilterSuccess() {
	    setCurrentState("selection.view");
	    Identity form = new Identity("");
	    getFlowScope().put("identity", form);
	    getFlowScope().put("identityModel", new SimpleModel<Identity>(form, new User()));

	    context.setEventId("applyFilter");
	    
	    List<Identity> identityList = new ArrayList<Identity>();
	    identityList.add(new Identity("TEST"));
	    
	    EasyMock.expect(userMgr.findIdentities(EasyMock.isA(Filter.class), (Collection<Identity>) EasyMock.isNull())).andReturn(identityList);
	    EasyMock.replay(userMgr);
	    
	    resumeFlow(context);

	    assertCurrentStateEquals("edit.view");
	    
	    LocalAttributeMap viewScope = (LocalAttributeMap) getRequiredFlowAttribute("viewScope");
	    assertEquals("identity_bar",  viewScope.get("sidebar"));
	    assertEquals("identity_form", viewScope.get("template"));
	}
	
	@SuppressWarnings("unchecked")
	public void testApplyFilterError() {
	    setCurrentState("selection.view");
	    Identity form = new Identity("");
	    getFlowScope().put("identity", form);
	    getFlowScope().put("identityModel", new SimpleModel<Identity>(form, new User()));

	    context.setEventId("applyFilter");
	    
	    List<Identity> identityList = new ArrayList<Identity>();
	    
	    EasyMock.expect(userMgr.findIdentities(EasyMock.isA(Filter.class), (Collection<Identity>) EasyMock.isNull())).andReturn(identityList);
	    EasyMock.replay(userMgr);
	    
	    resumeFlow(context);

	    assertCurrentStateEquals("selection.view");
	}
	
	public void testStoreIdentity() {
	    setCurrentState("edit.view");
	    Identity form = new Identity("");
	    getFlowScope().put("identity", form);
	    getFlowScope().put("identityModel", new SimpleModel<Identity>(form, new User()));

	    context.setEventId("storeIdentity");
	    
	    Identity identity = new Identity("");
	    
	    EasyMock.expect(userMgr.storeIdentity(identity)).andReturn(identity);
	    EasyMock.replay(userMgr);
	    
	    resumeFlow(context);

	    assertFlowExecutionEnded();
	}
	
	// locals
	
	private IdentityActionImpl identityAction;
	private UserMgr userMgr;
	private NamingConventionStrategy actionNamingConventionStrategy;
	private MockExternalContext context;
	
	public void setUp() throws Exception {
		super.setUp();
		identityAction = new IdentityActionImpl();
		userMgr = EasyMock.createMock(UserMgr.class);
		identityAction.setUserMgr(userMgr);
		actionNamingConventionStrategy = new NamingConventionStrategy() {
			public String getConventionalName(Class<?> clazz) { return "identity"; }
		};
		identityAction.setActionNamingConventionStrategy(actionNamingConventionStrategy);
		Authentication auth = SecurityTestSupport.createAuthentication();
		context = new MockExternalContext();
		context.setCurrentUser(auth);
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		EasyMock.reset(userMgr);
	}

}
