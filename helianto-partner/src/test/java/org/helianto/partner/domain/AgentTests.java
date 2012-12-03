package org.helianto.partner.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity2;
import org.helianto.partner.domain.nature.Agent;
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
		Entity entity = new Entity(new Operator("DEFAULT"));
        PrivateEntity2 partnerRegistry = new PrivateEntity2(entity, "TEST");
        
        Agent agent = new Agent();
        Agent other = new Agent();
        
        assertTrue(agent.equals(other));
        
        agent.setPrivateEntity(partnerRegistry);
        assertFalse(agent.equals(other));
        other.setPrivateEntity(partnerRegistry);
        assertTrue(agent.equals(other));
        assertEquals(agent.hashCode(), other.hashCode());
        assertFalse(agent.equals(new Partner(partnerRegistry)));
    }

}
    
    
