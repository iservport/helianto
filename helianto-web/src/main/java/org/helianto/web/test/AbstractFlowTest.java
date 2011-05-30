package org.helianto.web.test;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.test.SecurityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.helianto.web.action.SimpleModel;
import org.junit.After;
import org.springframework.security.core.Authentication;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractFlowTest extends AbstractXmlFlowExecutionTests {
	
	protected StringBuilder getFlowBasePathBuilder() {
		return new StringBuilder("/freemarker/");
	}
	
	/**
	 * The base path (after freemarker folder), defaults to the test class name
	 * minus the FlowTests suffix.
	 */
	protected String getBasePath() {
		return new FlowTestNamingConventionStrategy().getConventionalName(getClass());
	}
	
	/**
	 * The base name, defaults to the base path.
	 */
	protected String getBaseName() {
		return getBasePath();
	}
	
	/**
	 * The base name, defaults to the base path.
	 */
	protected String getBaseNameCapFirst() {
		return new StringBuilder(getBasePath().substring(0, 1).toUpperCase())
			.append(getBasePath().substring(1))
			.toString();
	}
	
	/**
	 * The base name, defaults to the $baseName."Model".
	 */
	protected String getModelName() {
		return new StringBuilder(getBaseName()).append("Model").toString();
	}
	
	/**
	 * Helper to create a view name like $baseName.suffix
	 * 
	 * @param suffix
	 */
	protected String standardViewNameBuilder(String suffix) {
		return new StringBuilder(getBaseName())
		.append("_")
		.append(suffix)
		.toString();
	}

	/**
	 * The flow name.
	 */
	protected abstract String getFlowName();

	/**
	 * The flow suffix.
	 */
	protected String getFlowSuffix() {
		return "";
	}

	/**
	 * Override if relative path is different from $basePath.flowName.xml.
	 */
	protected String getRelativePath() {
		return new StringBuilder(getBasePath())
		.append("/")
		.append(getFlowName())
		.append(getFlowSuffix())
		.append(".xml")
		.toString();
	}

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createClassPathResource(getFlowBasePathBuilder().append(getRelativePath()).toString(), getClass());
	}
	
	@Override
	protected FlowDefinitionResource[] getModelResources(FlowDefinitionResourceFactory resourceFactory) {
	   return new FlowDefinitionResource[] {
	       resourceFactory.createClassPathResource("/freemarker/_menu.xml", getClass())
	   };
	}
	
	protected void doSelectionStartTest() {
		MutableAttributeMap input = new LocalAttributeMap();
	    
	    startFlow(input, getContext());
	    assertCurrentStateEquals("selection.view");
	    assertEquals(new StringBuilder(getBasePath()).append("/").toString(), getRequiredFlowAttribute("basePath"));
	    assertEquals("assign", getRequiredFlowAttribute("assign"));
	    
	    LocalAttributeMap viewScope = (LocalAttributeMap) getRequiredFlowAttribute("viewScope");
	    assertEquals("filter", viewScope.get("sidebar"));
	    assertEquals("selection", viewScope.get("template"));
	}
	
	/**
	 * Standard edit flow test.
	 */
	protected void doEditStartTest() {
		MutableAttributeMap input = new LocalAttributeMap();
	    
	    startFlow(input, getContext());
	    assertCurrentStateEquals("edit.view");
	    
	    assertEquals(standardViewNameBuilder("bar"), getViewAttribute("sidebar"));
	    assertEquals(standardViewNameBuilder("form"), getViewAttribute("template"));
	    assertEquals(new StringBuilder(getBasePath()).append("/").toString(), getViewAttribute("basePath"));
	    if (getViewAttribute("assign")==null) {
		    assertEquals("assign", getFlowAttribute("assign"));
	    }
	    else {
		    assertEquals("assign", getViewAttribute("assign"));
	    }
	}
	
	/**
	 * Prepare the store event test.
	 * 
	 * @param model
	 */
	protected void doPrepareStoreEvent(SimpleModel<?> model) {
	    setCurrentState("edit.view");
	    getFlowScope().put(getBaseName(), model.getForm());
	    getFlowScope().put(getModelName(), model);
	    getContext().setEventId(new StringBuilder("store").append(getBaseNameCapFirst()).toString());
	}

	// locals
	
	private MockExternalContext context;
	protected Entity entity;
	
	protected MockExternalContext getContext() {
		return context;
	}
	
	/**
	 * Helper method to supply naming convention strategy dependency.
	 * 
	 * @param actionName
	 */
	protected NamingConventionStrategy createActionNamingConventionStrategy(final String actionName) {
		return new NamingConventionStrategy() {
			public String getConventionalName(Class<?> clazz) { return actionName; }
		};
	}
	
	public void setUp() throws Exception {
		super.setUp();
		User user = UserTestSupport.createUser();
		entity = user.getEntity();
		Authentication auth = SecurityTestSupport.createAuthentication(user);
		context = new MockExternalContext();
		context.setCurrentUser(auth);
		doSetup();
	}
	
	protected abstract void doSetup();
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		doTearDown();
	}

	protected abstract void doTearDown();
	
}
