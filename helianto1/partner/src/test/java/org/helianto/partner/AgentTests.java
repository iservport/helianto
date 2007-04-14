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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Agent agent = Agent.agentFactory(partnerAssociation, sequence);
        
        assertSame(partnerAssociation, agent.getPartnerAssociation());
        assertEquals(sequence, agent.getSequence());
        assertTrue(partnerAssociation.getPartners().contains(agent));
        
    }
    
}
    
    
