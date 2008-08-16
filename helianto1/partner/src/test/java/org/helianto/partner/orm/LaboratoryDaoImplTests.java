package org.helianto.partner.orm;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.Laboratory;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.dao.LaboratoryDao;
import org.helianto.partner.test.LaboratoryTestSupport;

/**
 * <code>LaboratoryDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class LaboratoryDaoImplTests extends AbstractPartnerDaoImplConfig {

    private LaboratoryDao laboratoryDao;
    private PartnerDao partnerDao;
    
    /*
     * Hook to persist one <code>Laboratory</code>.
     */  
    protected Laboratory writeLaboratory() {
        Laboratory laboratory = LaboratoryTestSupport.createLaboratory();
        partnerDao.persistPartner(laboratory);
        partnerDao.flush();
        partnerDao.clear();
        return laboratory;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneLaboratory() {
        Laboratory laboratory = writeLaboratory();

        assertEquals(laboratory,  laboratoryDao.findLaboratoryByNaturalId(laboratory.getPartnerRegistry()));
    }
    
    /*
     * Hook to persist a <code>Laboratory</code> list.
     */  
    protected List<Laboratory> writeLaboratoryList() {
        int laboratoryListSize = 10;
        List<Laboratory> laboratoryList = LaboratoryTestSupport.createLaboratoryList(laboratoryListSize);
        assertEquals(laboratoryListSize, laboratoryList.size());
        for (Laboratory laboratory: laboratoryList) {
            partnerDao.persistPartner(laboratory);
        }
        partnerDao.flush();
        partnerDao.clear();
        return laboratoryList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListLaboratory() {
        List<Laboratory> laboratoryList = writeLaboratoryList();

        Laboratory laboratory = laboratoryList.get((int) (Math.random()*laboratoryList.size()));
        assertEquals(laboratory,  laboratoryDao.findLaboratoryByNaturalId(laboratory.getPartnerRegistry()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testLaboratoryDuplicate() {
        Laboratory laboratory =  writeLaboratory();
        Laboratory laboratoryCopy = LaboratoryTestSupport.createLaboratory(laboratory.getPartnerRegistry());

        try {
            partnerDao.mergePartner(laboratoryCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveLaboratory() {
        List<Laboratory> laboratoryList = writeLaboratoryList();
        Laboratory laboratory = laboratoryList.get((int) (Math.random()*laboratoryList.size()));
        partnerDao.removePartner(laboratory);

        assertNull(laboratoryDao.findLaboratoryByNaturalId(laboratory.getPartnerRegistry()));
    }

    //- setters

    public void setLaboratoryDao(LaboratoryDao laboratoryDao) {
        this.laboratoryDao = laboratoryDao;
    }
    
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}

