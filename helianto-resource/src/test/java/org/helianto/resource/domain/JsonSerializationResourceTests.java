package org.helianto.resource.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationResourceTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(ResourceGroup.class)));
		
	}

}
