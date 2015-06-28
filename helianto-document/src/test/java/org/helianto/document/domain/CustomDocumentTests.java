package org.helianto.document.domain;

import static org.junit.Assert.assertEquals;

import org.helianto.document.internal.AbstractCustomDocument;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CustomDocumentTests {
	
	@Test
	public void applyPattern() {
		DocumentFolder folder = new DocumentFolder();
		folder.setPatternPrefix("ABC");
		folder.setNumberOfDigits(2);
		customDocument.setFolder(folder);
		customDocument.setInternalNumber(12L);
		customDocument.applyNumberPattern();
		assertEquals("ABC12", customDocument.getDocCode());
	}
	
	private AbstractCustomDocument customDocument;
	
	@Before
	public void setUp() {
		customDocument = new AbstractCustomDocument() {
			private static final long serialVersionUID = 1L;
		};
	}

}
