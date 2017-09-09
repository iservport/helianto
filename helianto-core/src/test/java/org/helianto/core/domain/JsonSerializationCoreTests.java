package org.helianto.core.domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationCoreTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Category2.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(City.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Entity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Identity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(IdentitySecurity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(KeyType.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PersonalAddress.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateSequence.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PublicAddress.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PublicEntityKey.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Service.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(State.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Unit.class)));

	}

}
