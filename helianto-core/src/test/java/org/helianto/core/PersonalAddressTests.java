package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.def.AddressType;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PersonalAddressTests {
	
	@Test
	public void constructor() {
		PersonalAddress personalAddress = new PersonalAddress();
		assertEquals(AddressType.PERSONAL.getValue(), personalAddress.getAddressType());
		Identity identity = new Identity("PRINCIPAL");
		personalAddress = new PersonalAddress(identity, AddressType.COLLECTION);
		assertSame(identity, personalAddress.getIdentity());
		assertEquals(AddressType.COLLECTION.getValue(), personalAddress.getAddressType());
	}
	
	@Test
	public void equality() {
		PersonalAddress personalAddress = new PersonalAddress();
		assertFalse(personalAddress.equals(null));
		
		PersonalAddress other = new PersonalAddress();
		assertTrue(personalAddress.equals(other));
		
		Identity identity = new Identity("PRINCIPAL");
		personalAddress.setIdentity(identity);
		assertFalse(personalAddress.equals(other));
		personalAddress.setAddressTypeAsEnum(AddressType.COLLECTION);
		assertFalse(personalAddress.equals(other));

		other.setIdentity(identity);
		assertFalse(personalAddress.equals(other));
		other.setAddressTypeAsEnum(AddressType.COLLECTION);
		assertTrue(personalAddress.equals(other));
		assertEquals(personalAddress.hashCode(), other.hashCode());

		personalAddress.setIdentity(new Identity("OTHER"));
		assertFalse(personalAddress.equals(other));
		personalAddress.setAddressTypeAsEnum(AddressType.DELIVERY);
		assertFalse(personalAddress.equals(other));
}

}
