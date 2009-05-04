package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.Agent;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>Agent</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AgentTestSupport {

    /**
     * Test support method to create a <code>Agent</code>.
     * @param partnerRegistry optional PartnerRegistry 
     */
    public static Agent createAgent(Object... args) {
        PartnerRegistry partnerRegistry;
        try {
            partnerRegistry = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        Agent agent = Agent.agentFactory(partnerRegistry);
        return agent;
    }

    /**
     * Test support method to create a <code>Agent</code> list.
     *
     * @param agentListSize
     */
    public static List<Agent> createAgentList(int agentListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(agentListSize);

        return createAgentList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Agent</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Agent> createAgentList(List<PartnerRegistry> partnerRegistryList) {
        List<Agent> agentList = new ArrayList<Agent>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
        	agentList.add(createAgent(partnerRegistry));
        }
        return agentList;
    }

}
