package org.helianto.partner.orm;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.dao.PartnerAssociationDao;
import org.helianto.partner.test.PartnerAssociationTestSupport;

/**
 * <code>PartnerAssociationDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private PartnerAssociationDao partnerAssociationDao;
    
    /*
     * Hook to persist one <code>PartnerAssociation</code>.
     */  
    protected PartnerAssociation writePartnerAssociation() {
        PartnerAssociation partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
        partnerAssociationDao.persistPartnerAssociation(partnerAssociation);
        partnerAssociationDao.flush();
        partnerAssociationDao.clear();
        return partnerAssociation;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartnerAssociation() {
        PartnerAssociation partnerAssociation = writePartnerAssociation();

        assertEquals(partnerAssociation,  partnerAssociationDao.findPartnerAssociationByNaturalId(partnerAssociation.getEntity(), partnerAssociation.getPartnerAlias()));
    }
    
    /*
     * Hook to persist a <code>PartnerAssociation</code> list.
     */  
    protected List<PartnerAssociation> writePartnerAssociationList() {
        int partnerAssociationListSize = 10;
        int entityListSize = 2;
        List<PartnerAssociation> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize, entityListSize);
        assertEquals(partnerAssociationListSize * entityListSize, partnerAssociationList.size());
        for (PartnerAssociation partnerAssociation: partnerAssociationList) {
            partnerAssociationDao.persistPartnerAssociation(partnerAssociation);
        }
        partnerAssociationDao.flush();
        partnerAssociationDao.clear();
        return partnerAssociationList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPartnerAssociation() {
        List<PartnerAssociation> partnerAssociationList = writePartnerAssociationList();

        PartnerAssociation partnerAssociation = partnerAssociationList.get((int) (Math.random()*partnerAssociationList.size()));
        assertEquals(partnerAssociation,  partnerAssociationDao.findPartnerAssociationByNaturalId(partnerAssociation.getEntity(), partnerAssociation.getPartnerAlias()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testPartnerAssociationDuplicate() {
        PartnerAssociation partnerAssociation =  writePartnerAssociation();
        PartnerAssociation partnerAssociationCopy = PartnerAssociationTestSupport.createPartnerAssociation(partnerAssociation.getEntity(), partnerAssociation.getPartnerAlias());

        try {
            partnerAssociationDao.mergePartnerAssociation(partnerAssociationCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePartnerAssociation() {
        List<PartnerAssociation> partnerAssociationList = writePartnerAssociationList();
        PartnerAssociation partnerAssociation = partnerAssociationList.get((int) (Math.random()*partnerAssociationList.size()));
        partnerAssociationDao.removePartnerAssociation(partnerAssociation);

        assertNull(partnerAssociationDao.findPartnerAssociationByNaturalId(partnerAssociation.getEntity(), partnerAssociation.getPartnerAlias()));
    }

    //- setters

    public void setPartnerAssociationDao(PartnerAssociationDao partnerAssociationDao) {
        this.partnerAssociationDao = partnerAssociationDao;
    }
    
}

