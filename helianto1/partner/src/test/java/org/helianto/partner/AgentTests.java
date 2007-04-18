package org.helianto.partner;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

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
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Agent agent = Agent.agentFactory(partnerRegistry, sequence);
        
        assertSame(partnerRegistry, agent.getPartnerRegistry());
        assertEquals(sequence, agent.getSequence());
        assertTrue(partnerRegistry.getPartners().contains(agent));
        
    }
    
}
    
    
