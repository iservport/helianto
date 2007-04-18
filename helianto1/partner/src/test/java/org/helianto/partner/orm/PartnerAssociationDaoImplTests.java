package org.helianto.partner.orm;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.dao.PartnerRegistryDao;
import org.helianto.partner.test.PartnerAssociationTestSupport;

/**
 * <code>PartnerAssociationDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private PartnerRegistryDao partnerRegistryDao;
    
    /*
     * Hook to persist one <code>PartnerAssociation</code>.
     */  
    protected PartnerRegistry writePartnerAssociation() {
        PartnerRegistry partnerRegistry = PartnerAssociationTestSupport.createPartnerAssociation();
        partnerRegistryDao.persistPartnerRegistry(partnerRegistry);
        partnerRegistryDao.flush();
        partnerRegistryDao.clear();
        return partnerRegistry;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartnerAssociation() {
        PartnerRegistry partnerRegistry = writePartnerAssociation();

        assertEquals(partnerRegistry,  partnerRegistryDao.findPartnerRegistryByNaturalId(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias()));
    }
    
    /*
     * Hook to persist a <code>PartnerAssociation</code> list.
     */  
    protected List<PartnerRegistry> writePartnerAssociationList() {
        int partnerAssociationListSize = 10;
        int entityListSize = 2;
        List<PartnerRegistry> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize, entityListSize);
        assertEquals(partnerAssociationListSize * entityListSize, partnerAssociationList.size());
        for (PartnerRegistry partnerRegistry: partnerAssociationList) {
            partnerRegistryDao.persistPartnerRegistry(partnerRegistry);
        }
        partnerRegistryDao.flush();
        partnerRegistryDao.clear();
        return partnerAssociationList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPartnerAssociation() {
        List<PartnerRegistry> partnerAssociationList = writePartnerAssociationList();

        PartnerRegistry partnerRegistry = partnerAssociationList.get((int) (Math.random()*partnerAssociationList.size()));
        assertEquals(partnerRegistry,  partnerRegistryDao.findPartnerRegistryByNaturalId(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testPartnerAssociationDuplicate() {
        PartnerRegistry partnerRegistry =  writePartnerAssociation();
        PartnerRegistry partnerRegistryCopy = PartnerAssociationTestSupport.createPartnerAssociation(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias());

        try {
            partnerRegistryDao.mergePartnerRegistry(partnerRegistryCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePartnerAssociation() {
        List<PartnerRegistry> partnerAssociationList = writePartnerAssociationList();
        PartnerRegistry partnerRegistry = partnerAssociationList.get((int) (Math.random()*partnerAssociationList.size()));
        partnerRegistryDao.removePartnerRegistry(partnerRegistry);

        assertNull(partnerRegistryDao.findPartnerRegistryByNaturalId(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias()));
    }

    //- setters

    public void setPartnerAssociationDao(PartnerRegistryDao partnerRegistryDao) {
        this.partnerRegistryDao = partnerRegistryDao;
    }
    
}

