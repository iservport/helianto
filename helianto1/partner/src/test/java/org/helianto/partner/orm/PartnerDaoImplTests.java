package org.helianto.partner.orm;

import java.util.List;

import org.helianto.core.test.AbstractIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.Partner;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.test.PartnerTestSupport;

/**
 * <code>PartnerDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerDaoImplTests extends AbstractIntegrationTest {
    
    private PartnerDao partnerDao;
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/core.xml",
                "deploy/org.helianto.partner.xml"
                };
    }
    
    /*
     * Hook to persist one <code>Partner</code>.
     */  
    protected Partner writePartner() {
        Partner partner = PartnerTestSupport.createPartner();
        partnerDao.persistPartner(partner);
        partnerDao.flush();
        partnerDao.clear();
        return partner;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartner() {
        Partner partner = writePartner();

        assertEquals(partner,  partnerDao.findPartnerByNaturalId(partner.getPartnerAssociation(), partner.getSequence()));
    }
    
    /*
     * Hook to persist a <code>Partner</code> list.
     */  
    protected List<Partner> writePartnerList() {
        int partnerListSize = 10;
        int partnerAssociationListSize = 2;
        List<Partner> partnerList = PartnerTestSupport.createPartnerList(partnerListSize, partnerAssociationListSize);
        assertEquals(partnerListSize * partnerAssociationListSize, partnerList.size());
        for (Partner partner: partnerList) {
            partnerDao.persistPartner(partner);
        }
        partnerDao.flush();
        partnerDao.clear();
        return partnerList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPartner() {
        List<Partner> partnerList = writePartnerList();

        Partner partner = partnerList.get((int) (Math.random()*partnerList.size()));
        assertEquals(partner,  partnerDao.findPartnerByNaturalId(partner.getPartnerAssociation(), partner.getSequence()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testPartnerDuplicate() {
        Partner partner =  writePartner();
        Partner partnerCopy = PartnerTestSupport.createPartner(partner.getPartnerAssociation(), partner.getSequence());

        try {
            partnerDao.mergePartner(partnerCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePartner() {
        List<Partner> partnerList = writePartnerList();
        Partner partner = partnerList.get((int) (Math.random()*partnerList.size()));
        partnerDao.removePartner(partner);

        assertNull(partnerDao.findPartnerByNaturalId(partner.getPartnerAssociation(), partner.getSequence()));
    }

    //- setters

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}

