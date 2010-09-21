package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Agent</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AgentTests {
    
    /**
     * Test <code>Agent</code> equals() method.
     */
	@Test
    public void agentEquals() {
        PrivateEntity partnerRegistry = new PrivateEntity();
        
        Agent agent = new Agent(partnerRegistry);
        Partner copy = (Agent) DomainTestSupport.minimalEqualsTest(agent);
        
        copy.setPrivateEntity(null);
        assertFalse(agent.equals(copy));

        copy.setPrivateEntity(partnerRegistry);
        assertTrue(agent.equals(copy));
    }

}
    
    
