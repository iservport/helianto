package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Agent;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>AgentDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AgentTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Agent</code>.
     * @param partnerAssociation optional PartnerAssociation 
     * @param sequence optional int 
     */
    public static Agent createAgent(Object... args) {
        PartnerRegistry partnerAssociation;
        try {
            partnerAssociation = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Agent agent = Agent.agentFactory(partnerAssociation, sequence);
        return agent;
    }

    /**
     * Test support method to create a <code>Agent</code> list.
     *
     * @param agentListSize
     */
    public static List<Agent> createAgentList(int agentListSize) {
        return createAgentList(agentListSize, 1);
    }

    /**
     * Test support method to create a <code>Agent</code> list.
     *
     * @param agentListSize
     * @param partnerAssociationListSize
     */
    public static List<Agent> createAgentList(int agentListSize, int partnerAssociationListSize) {
        List<PartnerRegistry> partnerAssociationList = PartnerRegistryTestSupport.createPartnerRegistryList(partnerAssociationListSize);

        return createAgentList(agentListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Agent</code> list.
     *
     * @param agentListSize
     * @param partnerAssociationList
     */
    public static List<Agent> createAgentList(int agentListSize, List<PartnerRegistry> partnerAssociationList) {
        List<Agent> agentList = new ArrayList<Agent>();
        for (PartnerRegistry partnerRegistry: partnerAssociationList) {
	        for (int i=0;i<agentListSize;i++) {
	        	agentList.add(createAgent(partnerRegistry));
        	}
        }
        return agentList;
    }

}
