package org.helianto.core.service;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Identity;
import org.helianto.core.PersonalData;
import org.junit.Before;
import org.junit.Test;

public class DefaultPrincipalGenerationStrategyTests {
	
	private PrincipalGenerationStrategy principalGenerationStrategy;
	
    @Test
	public void principalGeneration() {
		Identity identity = new Identity();
		identity.setPrincipal("");
		identity.setOptionalAlias("alias");
		identity.setPersonalData(new PersonalData());
		identity.getPersonalData().setLastName("lastName");
		identity.getPersonalData().setFirstName("firstName");
		
		principalGenerationStrategy.generatePrincipal(identity, 0);
		assertEquals("flastname", identity.getPrincipal());
	}
	
	@Before
	public void setUp() {
		principalGenerationStrategy = new DefaultPrincipalGenerationStrategy();
	}

}
