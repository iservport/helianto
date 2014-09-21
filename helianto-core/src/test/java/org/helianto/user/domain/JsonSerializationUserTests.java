package org.helianto.user.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationUserTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(RootUser.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(UserAssociation.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(UserConnection.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(UserLog.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(UserRequest.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(UserRole.class)));
		
	}

}
