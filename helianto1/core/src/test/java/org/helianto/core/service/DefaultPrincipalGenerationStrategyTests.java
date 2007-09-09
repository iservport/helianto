package org.helianto.core.service;

import org.helianto.core.Identity;

import junit.framework.TestCase;

public class DefaultPrincipalGenerationStrategyTests extends TestCase {
	
	private PrincipalGenerationStrategy principalGenerationStrategy;
	
	public void testPrincipalGeneration() {
		Identity identity = new Identity();
		
		principalGenerationStrategy.generatePrincipal(identity, 0);
	}
	
	@Override
	public void setUp() {
		principalGenerationStrategy = new DefaultPrincipalGenerationStrategy();
	}

}
