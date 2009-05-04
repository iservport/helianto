package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

/**
 * <code>Agent</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AgentTests extends TestCase {
    
    /**
     * Test <code>Agent</code> static factory method.
     */
    public void testAgentFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Agent agent = Agent.agentFactory(partnerRegistry);
        
        assertSame(partnerRegistry, agent.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(agent));
        
    }
    
    /**
     * Test <code>Agent</code> equals() method.
     */
    public void testAgentEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Agent agent = Agent.agentFactory(partnerRegistry);
        Partner copy = (Agent) DomainTestSupport.minimalEqualsTest(agent);
        
        copy.setPartnerRegistry(null);
        assertFalse(agent.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(agent.equals(copy));
    }

}
    
    
