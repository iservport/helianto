package org.helianto.core.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationCoreTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Category.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(City.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Country.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Entity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Identity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(IdentitySecurity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(KeyType.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Operator.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PersonalAddress.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateSequence.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PublicAddress.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PublicEntityKey.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Service.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(State.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Unit.class)));

	}

}
