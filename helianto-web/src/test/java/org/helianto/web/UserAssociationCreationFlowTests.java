package org.helianto.web;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Identity;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.impl.IdentityActionImpl;
import org.helianto.web.test.AbstractFlowTest;
import org.junit.Test;
import org.springframework.binding.mapping.Mapper;
import org.springframework.binding.mapping.MappingResults;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.engine.EndState;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.test.MockFlowBuilderContext;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserAssociationCreationFlowTests extends AbstractFlowTest {
	
	/**
	 * Base path can not be read from this class name.
	 */
	protected String getBasePath() {
		return "userAssociation/";
	}
	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
	    builderContext.registerBean("identityAction", identityAction);
	}
	
	@Test
	public void testStart() {
		MutableAttributeMap input = new LocalAttributeMap();
	    
	    startFlow(input, getContext());
	    assertCurrentStateEquals("selection.view");
	    assertEquals("identity/", getRequiredFlowAttribute("basePath"));
	    assertEquals("assign", getRequiredFlowAttribute("assign"));
	    assertEquals("", ((Identity) getRequiredFlowAttribute("identity")).getPrincipal());
	    
	    LocalAttributeMap viewScope = (LocalAttributeMap) getRequiredFlowAttribute("viewScope");
	    assertEquals("identity_bar", viewScope.get("sidebar"));
	    assertEquals("filter", viewScope.get("template"));
	}
	
//	@SuppressWarnings("unchecked")
//	public void testApplyFilterCreate() {
//	    setCurrentState("selection.view");
//	    getFlowDefinitionRegistry().registerFlowDefinition(createMockIdentitySubflow());
//	    getFlowDefinitionRegistry().registerFlowDefinition(createMockCredentialSubflow());
//	    getFlowDefinitionRegistry().registerFlowDefinition(createMockUserAssociationSubflow());
//	    Identity form = new Identity("");
//	    getFlowScope().put("identity", form);
//	    getFlowScope().put("identityModel", new SimpleModel<Identity>(form, new User()));
//
//	    getContext().setEventId("applyFilter");
//	    
//	    List<Identity> identityList = new ArrayList<Identity>();
//	    
//	    EasyMock.expect(userMgr.findIdentities(EasyMock.isA(Filter.class), (Collection<Identity>) EasyMock.isNull())).andReturn(identityList);
//	    EasyMock.replay(userMgr);
//	    
//	    resumeFlow(getContext());
//
//	    assertCurrentStateEquals("edit.view");
//	    
//	    assertSame(form, getRequiredFlowAttribute("identity"));
//	    LocalAttributeMap viewScope = (LocalAttributeMap) getRequiredFlowAttribute("viewScope");
//	    assertEquals("identity_bar",  viewScope.get("sidebar"));
//	    assertEquals("identity_form", viewScope.get("template"));
//	}
	
	public Flow createMockIdentitySubflow() {
	    Flow mockIdentityFlow = new Flow("identity.subflow");
	    mockIdentityFlow.setInputMapper(new Mapper() {
	        public MappingResults map(Object source, Object target) {
	            assertSame(identity, ((AttributeMap) source).get("identity"));
	            return null;
	        }
	    });
	    new EndState(mockIdentityFlow, "toCommit");
	    return mockIdentityFlow;
	}
	
	public Flow createMockCredentialSubflow() {
	    Flow mockCredentialFlow = new Flow("credential.subflow");
	    mockCredentialFlow.setInputMapper(new Mapper() {
	        public MappingResults map(Object source, Object target) {
	            // assert that 1L was passed in as input
	            assertEquals(1L, ((AttributeMap) source).get("hotelId"));
	            return null;
	        }
	    });
	    // immediately return the bookingConfirmed outcome so the caller can respond
	    new EndState(mockCredentialFlow, "bookingConfirmed");
	    return mockCredentialFlow;
	}
	
	public Flow createMockUserAssociationSubflow() {
	    Flow mockUserAssociationFlow = new Flow("userAssociation.subflow");
	    mockUserAssociationFlow.setInputMapper(new Mapper() {
	        public MappingResults map(Object source, Object target) {
	            // assert that 1L was passed in as input
	            assertEquals(1L, ((AttributeMap) source).get("hotelId"));
	            return null;
	        }
	    });
	    // immediately return the bookingConfirmed outcome so the caller can respond
	    new EndState(mockUserAssociationFlow, "bookingConfirmed");
	    return mockUserAssociationFlow;
	}
	
	// locals
	
	private IdentityActionImpl identityAction;
	private IdentityMgr identityMgr;
	private Identity identity;
	
	@Override
	protected void doSetup() {
		identity = new Identity();
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
