package org.helianto.web.action.impl;

import static org.junit.Assert.assertEquals;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.User;
import org.helianto.core.test.SecurityTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.helianto.document.DocumentMgr;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.filter.PrivateDocumentFilterAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateDocumentControllerTests {
	
	@Test
	public void loadContentByCode() {
		User user = UserTestSupport.createUser();
		Principal principal = SecurityTestSupport.createAuthentication(user);
		List<PrivateDocument> privateDocumentList = new ArrayList<PrivateDocument>();
		PrivateDocument document = new PrivateDocument(user.getEntity(), "CODE");
		document.setContent("TEST".getBytes());
		privateDocumentList.add(document);
		
		PrivateDocumentFilterAdapter filter = new PrivateDocumentFilterAdapter(user.getEntity(), "CODE");
		
		EasyMock.expect(documentMgr.findPrivateDocuments(EasyMock.eq(filter))).andReturn(privateDocumentList);
		EasyMock.replay(documentMgr);
		
		ResponseEntity<byte[]> response = controller.loadContentByCode("CODE", principal);
		EasyMock.verify(documentMgr);
		
		assertEquals(response.getBody(), document.getContent());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}
	
	// locals
	
	private PrivateDocumentController controller;
    private DocumentMgr documentMgr;

    @Before
    public void setUp() {
    	controller = new PrivateDocumentController();
    	documentMgr = EasyMock.createMock(DocumentMgr.class);
    	controller.setDocumentMgr(documentMgr);
    }
    
    @After
    public void tearDown() {
    	EasyMock.reset(documentMgr);
    }

}
