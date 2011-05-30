package org.helianto.web;

import org.easymock.classextension.EasyMock;
import org.helianto.document.service.DocumentMgr;
import org.helianto.web.action.impl.PrivateDocumentActionImpl;
import org.helianto.web.test.AbstractFlowTest;
import org.springframework.webflow.test.MockFlowBuilderContext;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractPrivateDocumentFlowTest extends AbstractFlowTest {

	@Override
	protected String getFlowName() {
		return "_privateDocument";
	}

	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		builderContext.registerBean("privateDocumentAction", privateDocumentAction);
	}


	// locals

	protected PrivateDocumentActionImpl privateDocumentAction;
	protected DocumentMgr documentMgr;

	@Override
	protected void doSetup() {
		privateDocumentAction = new PrivateDocumentActionImpl();
		documentMgr = EasyMock.createMock(DocumentMgr.class);
		privateDocumentAction.setDocumentMgr(documentMgr);
		privateDocumentAction
				.setActionNamingConventionStrategy(createActionNamingConventionStrategy("privateDocument"));
	}

	@Override
	protected void doTearDown() {
		EasyMock.reset(documentMgr);
	}

}