package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.License;
import org.helianto.core.domain.Operator;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class LicenseTests {

	@Test
	public void test() {
		assertTrue(new License().equals(new License()));
		assertFalse(new License().equals(new Object()));
		assertFalse(new License().equals(null));
		
		Operator operator = new Operator("DEFAULT");
		assertFalse(new License(operator, "ONE").equals(new License(new Operator("A"), "ONE")));
		assertFalse(new License(operator, "ONE").equals(new License(operator, "TWO")));
		assertTrue(new License(operator, "TWO").equals(new License(operator, "TWO")));
		assertEquals(new License(operator, "TWO").hashCode(), new License(operator, "TWO").hashCode());
	}

}
