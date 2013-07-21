package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicAddressTests {

	@Test
	public void constructor() {
		PublicAddress publicAddress = new PublicAddress();
		assertTrue(publicAddress instanceof Serializable);
		
		Operator operator = new Operator("OPERATOR");
		publicAddress = new PublicAddress(operator, "POSTALCODE");
		assertSame(operator, publicAddress.getOperator());
		assertEquals("POSTALCODE", publicAddress.getPostalCode());
		
	}
	
	@Test
	public void equality() {
		Operator operator = new Operator("OPERATOR");
		PublicAddress publicAddress = new PublicAddress();
		PublicAddress other = new PublicAddress();
		
		assertTrue(publicAddress.equals(other));
		
		publicAddress.setOperator(operator);
		assertFalse(publicAddress.equals(other));
		publicAddress.setPostalCode("POSTALCODE");
		assertFalse(publicAddress.equals(other));
		other.setOperator(operator);
		assertFalse(publicAddress.equals(other));
		other.setPostalCode("POSTALCODE");
		assertTrue(publicAddress.equals(other));
		assertEquals(publicAddress.hashCode(), other.hashCode());
		publicAddress.setPostalCode("");
		assertFalse(publicAddress.equals(other));
		publicAddress.setOperator(new Operator());
		assertFalse(publicAddress.equals(other));
		
	}
}
