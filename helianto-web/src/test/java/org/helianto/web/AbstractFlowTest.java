package org.helianto.web;

import org.helianto.core.naming.NamingConventionStrategy;
import org.helianto.core.test.SecurityTestSupport;
import org.junit.After;
import org.springframework.security.core.Authentication;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
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
	
	protected abstract String getRelativePath();

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

	// locals
	
	private MockExternalContext context;
	
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
		Authentication auth = SecurityTestSupport.createAuthentication();
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
