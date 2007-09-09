package org.helianto.core.service;

import org.helianto.core.Identity;
import org.helianto.core.PersonalData;

import junit.framework.TestCase;

public class DefaultPrincipalGenerationStrategyTests extends TestCase {
	
	private PrincipalGenerationStrategy principalGenerationStrategy;
	
	public void testPrincipalGeneration() {
		Identity identity = new Identity();
		identity.setPrincipal("");
		identity.setOptionalAlias("alias");
		identity.setPersonalData(new PersonalData());
		identity.getPersonalData().setLastName("lastName");
		identity.getPersonalData().setFirstName("firstName");
		
		principalGenerationStrategy.generatePrincipal(identity, 0);
		assertEquals("flastname", identity.getPrincipal());
	}
	
	@Override
	public void setUp() {
		principalGenerationStrategy = new DefaultPrincipalGenerationStrategy();
	}

}
