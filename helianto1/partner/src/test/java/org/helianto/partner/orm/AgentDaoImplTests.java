package org.helianto.partner.orm;

import java.util.List;

import org.helianto.partner.Agent;
import org.helianto.partner.Partner;
import org.helianto.partner.dao.AgentDao;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.test.AgentTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>PartnerDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class AgentDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private PartnerDao partnerDao;
    private AgentDao agentDao;
    
    /*
     * Hook to persist one <code>Partner</code>.
     */  
    protected Agent writeAgent() {
    	Agent partner = AgentTestSupport.createAgent();
        partnerDao.persistPartner(partner);
        partnerDao.flush();
        partnerDao.clear();
        return partner;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartner() {
    	Agent partner = writeAgent();

        assertEquals(partner,  agentDao.findAgentByNaturalId(partner.getPartnerRegistry(), partner.getSequence()));
    }
    
    /*
     * Hook to persist a <code>Agent</code> list.
     */  
    protected List<Agent> writeAgentList() {
        int agentListSize = 10;
        int partnerAssociationListSize = 2;
        List<Agent> agentList = AgentTestSupport.createAgentList(agentListSize, partnerAssociationListSize);
        assertEquals(agentListSize * partnerAssociationListSize, agentList.size());
        for (Partner partner: agentList) {
            partnerDao.persistPartner(partner);
        }
        partnerDao.flush();
        partnerDao.clear();
        return agentList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListAgent() {
        List<Agent> agentList = writeAgentList();

        Agent agent = agentList.get((int) (Math.random()*agentList.size()));
        assertEquals(agent,  agentDao.findAgentByNaturalId(agent.getPartnerRegistry(), agent.getSequence()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testAgentDuplicate() {
        Agent agent =  writeAgent();
        Agent agentCopy = AgentTestSupport.createAgent(agent.getPartnerRegistry(), agent.getSequence());

        try {
            partnerDao.mergePartner(agentCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveAgent() {
        List<Agent> agentList = writeAgentList();
        Agent agent = agentList.get((int) (Math.random()*agentList.size()));
        partnerDao.removePartner(agent);

        assertNull(agentDao.findAgentByNaturalId(agent.getPartnerRegistry(), agent.getSequence()));
    }

    //- setters

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }
    
}

