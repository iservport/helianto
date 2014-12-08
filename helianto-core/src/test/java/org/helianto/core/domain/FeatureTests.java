package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.domain.Operator;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FeatureTests {

	@Test
	public void domain() {
		Operator context = new Operator("DEAFULT");
		Feature feature = new Feature(context, "CA001", 'A'
				, "Some context wide action");
		assertSame(context, feature.getContext());
		assertEquals("CA001", feature.getFeatureCode());
		assertSame('A', feature.getFeatureType());
		assertEquals("Some context wide action", feature.getFeatureName());
		assertEquals(feature, new Feature(context, "CA001"));
	}

}
