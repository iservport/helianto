package org.helianto.inventory.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class JsonSerializationInventoryTests {

	@Test
	public void test() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Card.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(CardSet.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(GrossRequirement.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Inventory.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(InventoryTransaction.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Invoice.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Movement.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Picking.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(ProcessAgreement.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(ProcessRequirement.class)));
		assertTrue(objectMapper.canDeserialize(objectMapper.constructType(Shipment.class)));
		
	}

}
