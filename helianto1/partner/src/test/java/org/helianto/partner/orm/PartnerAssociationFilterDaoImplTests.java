package org.helianto.partner.orm;

import java.util.List;

import org.helianto.core.test.AbstractIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.PartnerAssociationFilter;
import org.helianto.partner.dao.PartnerAssociationFilterDao;
import org.helianto.partner.test.PartnerAssociationFilterTestSupport;

/**
 * <code>PartnerAssociationFilterDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationFilterDaoImplTests extends AbstractIntegrationTest {
    
    private PartnerAssociationFilterDao partnerAssociationFilterDao;
    
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
     * Hook to persist one <code>PartnerAssociationFilter</code>.
     */  
    protected PartnerAssociationFilter writePartnerAssociationFilter() {
        PartnerAssociationFilter partnerAssociationFilter = PartnerAssociationFilterTestSupport.createPartnerAssociationFilter();
        partnerAssociationFilterDao.persistPartnerAssociationFilter(partnerAssociationFilter);
        partnerAssociationFilterDao.flush();
        partnerAssociationFilterDao.clear();
        return partnerAssociationFilter;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePartnerAssociationFilter() {
        PartnerAssociationFilter partnerAssociationFilter = writePartnerAssociationFilter();

        assertEquals(partnerAssociationFilter,  partnerAssociationFilterDao.findPartnerAssociationFilterByNaturalId(partnerAssociationFilter.getEntity(), partnerAssociationFilter.getPartnerAssociationFilterAlias()));
    }
    
    /*
     * Hook to persist a <code>PartnerAssociationFilter</code> list.
     */  
    protected List<PartnerAssociationFilter> writePartnerAssociationFilterList() {
        int partnerAssociationFilterListSize = 10;
        int entityListSize = 2;
        List<PartnerAssociationFilter> partnerAssociationFilterList = PartnerAssociationFilterTestSupport.createPartnerAssociationFilterList(partnerAssociationFilterListSize, entityListSize);
        assertEquals(partnerAssociationFilterListSize * entityListSize, partnerAssociationFilterList.size());
        for (PartnerAssociationFilter partnerAssociationFilter: partnerAssociationFilterList) {
            partnerAssociationFilterDao.persistPartnerAssociationFilter(partnerAssociationFilter);
        }
        partnerAssociationFilterDao.flush();
        partnerAssociationFilterDao.clear();
        return partnerAssociationFilterList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPartnerAssociationFilter() {
        List<PartnerAssociationFilter> partnerAssociationFilterList = writePartnerAssociationFilterList();

        PartnerAssociationFilter partnerAssociationFilter = partnerAssociationFilterList.get((int) (Math.random()*partnerAssociationFilterList.size()));
        assertEquals(partnerAssociationFilter,  partnerAssociationFilterDao.findPartnerAssociationFilterByNaturalId(partnerAssociationFilter.getEntity(), partnerAssociationFilter.getPartnerAssociationFilterAlias()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testPartnerAssociationFilterDuplicate() {
        PartnerAssociationFilter partnerAssociationFilter =  writePartnerAssociationFilter();
        PartnerAssociationFilter partnerAssociationFilterCopy = PartnerAssociationFilterTestSupport.createPartnerAssociationFilter(partnerAssociationFilter.getEntity(), partnerAssociationFilter.getPartnerAssociationFilterAlias());

        try {
            partnerAssociationFilterDao.mergePartnerAssociationFilter(partnerAssociationFilterCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePartnerAssociationFilter() {
        List<PartnerAssociationFilter> partnerAssociationFilterList = writePartnerAssociationFilterList();
        PartnerAssociationFilter partnerAssociationFilter = partnerAssociationFilterList.get((int) (Math.random()*partnerAssociationFilterList.size()));
        partnerAssociationFilterDao.removePartnerAssociationFilter(partnerAssociationFilter);

        assertNull(partnerAssociationFilterDao.findPartnerAssociationFilterByNaturalId(partnerAssociationFilter.getEntity(), partnerAssociationFilter.getPartnerAssociationFilterAlias()));
    }

    //- setters

    public void setPartnerAssociationFilterDao(PartnerAssociationFilterDao partnerAssociationFilterDao) {
        this.partnerAssociationFilterDao = partnerAssociationFilterDao;
    }
    
}

