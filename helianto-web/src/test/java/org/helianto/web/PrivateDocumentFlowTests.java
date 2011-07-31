package org.helianto.web;

import org.easymock.classextension.EasyMock;
import org.helianto.core.User;
import org.helianto.document.PrivateDocument;
import org.helianto.web.action.SimpleModel;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateDocumentFlowTests extends AbstractPrivateDocumentFlowTest {
	
	public void testStart() {
		doEditStartTest();
	}
	
	public void testStorePrivateDocument() {
	    SimpleModel<PrivateDocument> model = new SimpleModel<PrivateDocument>(new PrivateDocument(), new User());

	    doPrepareStoreEvent(model);
	    
		PrivateDocument privateDocument = new PrivateDocument();
	    
	    EasyMock.expect(documentMgr.storeDocument(privateDocument)).andReturn(privateDocument);
	    EasyMock.replay(documentMgr);
	    
	    resumeFlow(getContext());

	    assertFlowExecutionEnded();
	}
	
}
