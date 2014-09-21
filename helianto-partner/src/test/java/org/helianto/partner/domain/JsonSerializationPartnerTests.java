package org.helianto.partner.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationPartnerTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Account.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Contact.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(ContactGroup.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Partner.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PartnerCategory.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PartnerKey.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PartnerPhone.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateAddress.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateEntity.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateEntityKey.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(PrivateSegment.class)));
		
	}

}
