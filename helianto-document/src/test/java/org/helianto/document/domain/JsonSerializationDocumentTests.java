package org.helianto.document.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationDocumentTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Document.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(DocumentAssociation.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(DocumentFolder.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateDocument.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(ProcessDocument.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(ProcessDocumentKey.class)));
		
	}

}
