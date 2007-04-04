package org.helianto.partner.orm;

import java.util.List;

import org.helianto.core.test.AbstractIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.PartnerKey;
import org.helianto.partner.dao.PartnerKeyDao;
import org.helianto.partner.test.PartnerKeyTestSupport;

/**
 * <code>PartnerKeyDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerKeyDaoImplTests extends AbstractIntegrationTest {
    
    private PartnerKeyDao partnerKeyDao;
    
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
     * Hook to persist one <code>PartnerKey</code>.
     */  
    protected PartnerKey writePartnerKey() {
        PartnerKey partnerKey = PartnerKeyTestSupport.createPartnerKey();
        partnerKeyDao.persistPartnerKey(partnerKey);
        partnerKeyDao.flush();
        partnerKeyDao.clear();
        return partnerKey;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartnerKey() {
        PartnerKey partnerKey = writePartnerKey();

        assertEquals(partnerKey,  partnerKeyDao.findPartnerKeyByNaturalId(partnerKey.getPartnerAssociation(), partnerKey.getKeyType()));
    }
    
    /*
     * Hook to persist a <code>PartnerKey</code> list.
     */  
    protected List<PartnerKey> writePartnerKeyList() {
        int partnerAssociationListSize = 2;
        int keyTypeListSize = 3;
        List<PartnerKey> partnerKeyList = PartnerKeyTestSupport.createPartnerKeyList(partnerAssociationListSize, keyTypeListSize);
        assertEquals(partnerAssociationListSize * keyTypeListSize, partnerKeyList.size());
        for (PartnerKey partnerKey: partnerKeyList) {
            partnerKeyDao.persistPartnerKey(partnerKey);
        }
        partnerKeyDao.flush();
        partnerKeyDao.clear();
        return partnerKeyList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPartnerKey() {
        List<PartnerKey> partnerKeyList = writePartnerKeyList();

        PartnerKey partnerKey = partnerKeyList.get((int) (Math.random()*partnerKeyList.size()));
        assertEquals(partnerKey,  partnerKeyDao.findPartnerKeyByNaturalId(partnerKey.getPartnerAssociation(), partnerKey.getKeyType()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testPartnerKeyDuplicate() {
        PartnerKey partnerKey =  writePartnerKey();
        PartnerKey partnerKeyCopy = PartnerKeyTestSupport.createPartnerKey(partnerKey.getPartnerAssociation(), partnerKey.getKeyType());

        try {
            partnerKeyDao.mergePartnerKey(partnerKeyCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePartnerKey() {
        List<PartnerKey> partnerKeyList = writePartnerKeyList();
        PartnerKey partnerKey = partnerKeyList.get((int) (Math.random()*partnerKeyList.size()));
        partnerKeyDao.removePartnerKey(partnerKey);

        assertNull(partnerKeyDao.findPartnerKeyByNaturalId(partnerKey.getPartnerAssociation(), partnerKey.getKeyType()));
    }

    //- setters

    public void setPartnerKeyDao(PartnerKeyDao partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    
}

