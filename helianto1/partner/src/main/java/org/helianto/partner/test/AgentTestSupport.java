package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Agent;
import org.helianto.partner.PartnerAssociation;

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
        PartnerAssociation partnerAssociation;
        try {
            partnerAssociation = (PartnerAssociation) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
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
        List<PartnerAssociation> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize);

        return createAgentList(agentListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Agent</code> list.
     *
     * @param agentListSize
     * @param partnerAssociationList
     */
    public static List<Agent> createAgentList(int agentListSize, List<PartnerAssociation> partnerAssociationList) {
        List<Agent> agentList = new ArrayList<Agent>();
        for (PartnerAssociation partnerAssociation: partnerAssociationList) {
	        for (int i=0;i<agentListSize;i++) {
	        	agentList.add(createAgent(partnerAssociation));
        	}
        }
        return agentList;
    }

}