package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class LicenseAgreementTests {

	@Test
	public void test() {
		assertTrue(new LicenseAgreement().equals(new LicenseAgreement()));
		assertFalse(new LicenseAgreement().equals(new Object()));
		assertFalse(new LicenseAgreement().equals(null));
		
		Operator operator = new Operator("DEFAULT");
		Entity entity = new Entity(operator, "E");
		Entity provider = new Entity(operator, "P");
		License one = new License(operator, "ONE");
		License two = new License(operator, "TWO");
		assertFalse(new LicenseAgreement(entity, one).equals(new LicenseAgreement(new Entity(new Operator("A")), one)));
		assertFalse(new LicenseAgreement(entity, one).equals(new LicenseAgreement(entity, two)));
		assertTrue(new LicenseAgreement(entity, two).equals(new LicenseAgreement(entity, two)));
		assertTrue(new LicenseAgreement(entity, two, provider).equals(new LicenseAgreement(entity, two, provider)));
		assertEquals(new LicenseAgreement(entity, two).hashCode(), new LicenseAgreement(entity, two).hashCode());
		
		LicenseAgreement licenseAgreement = new LicenseAgreement(entity, one, provider);
		assertSame(entity, licenseAgreement.getEntity());
		assertSame(one, licenseAgreement.getLicense());
		assertSame(provider, licenseAgreement.getProvider());
	}

}
