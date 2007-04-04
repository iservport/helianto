package org.helianto.process;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;

public class DocumentDomainTests extends TestCase {
	
	public void testDocument() {
		
		Document document = new Document();
		document.setId(Long.MAX_VALUE);
		document.setId(Long.MIN_VALUE);
		document.setEntity(new Entity());
		document.setDocCode("");
		document.setDocName("");
//		document.setChildren(new ArrayList<Tree>(0));
	}
	
	public void testDocumentEquals() {
		Document copy, document = new Document();
		document.setEntity(new Entity());
		document.setDocCode("TEST");
        copy = (Document) DomainTestSupport.minimalEqualsTest(document);
        
        copy.setEntity(document.getEntity());
        assertFalse(document.equals(copy));
        
        copy.setDocCode("TEST");
        assertTrue(document.equals(copy));
	}
	
	public void testExternalDocument() {
		ExternalDocument externalDocument = new ExternalDocument();
		externalDocument.setId(Long.MAX_VALUE);
		externalDocument.setId(Long.MIN_VALUE);
		externalDocument.setEntity(new Entity());
		externalDocument.setDocCode("");
		externalDocument.setDocName("");
		externalDocument.setDocUrl("");
		externalDocument.setDocType(DocumentType.CATEGORY.getValue());
		externalDocument.setDocType(DocumentType.FILE.getValue());
		externalDocument.setDocType(DocumentType.FOLDER.getValue());
	}

}
