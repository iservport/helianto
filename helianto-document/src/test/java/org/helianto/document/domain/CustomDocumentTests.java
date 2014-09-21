package org.helianto.document.domain;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.document.internal.AbstractCustomDocument;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CustomDocumentTests {
	
	@Test
	public void custom() {
		DocumentFolder folder = new DocumentFolder(new Entity(new Operator("DEFAULT"), "ALIAS"), "CODE");
		customDocument.setSeries(folder);
		assertEquals("CODE", customDocument.getInternalNumberKey());
	}

	@Test
	public void applyPattern() {
		DocumentFolder folder = new DocumentFolder();
		folder.setNumberPattern("ABC000");
		customDocument.setFolder(folder);
		
		customDocument.setInternalNumber(1);
		assertEquals("ABC001", customDocument.getDocCode());
		folder.setNumberPattern("0000/'09'");
		
		customDocument.resetDocCode();
		customDocument.setInternalNumber(1);
		assertEquals("0001/09", customDocument.getDocCode());
	}
	
	private AbstractCustomDocument customDocument;
	
	@Before
	public void setUp() {
		customDocument = new AbstractCustomDocument() {
			private static final long serialVersionUID = 1L;
		};
	}

}
