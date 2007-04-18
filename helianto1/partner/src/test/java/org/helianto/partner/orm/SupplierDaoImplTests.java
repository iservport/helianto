package org.helianto.partner.orm;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.Supplier;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.dao.SupplierDao;
import org.helianto.partner.test.SupplierTestSupport;

/**
 * <code>SupplierDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class SupplierDaoImplTests extends AbstractPartnerDaoImplConfig {

    private SupplierDao supplierDao;
    private PartnerDao partnerDao;
    
    /*
     * Hook to persist one <code>Supplier</code>.
     */  
    protected Supplier writeSupplier() {
        Supplier supplier = SupplierTestSupport.createSupplier();
        partnerDao.persistPartner(supplier);
        partnerDao.flush();
        partnerDao.clear();
        return supplier;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneSupplier() {
        Supplier supplier = writeSupplier();

        assertEquals(supplier,  supplierDao.findSupplierByNaturalId(supplier.getPartnerRegistry(), supplier.getSequence()));
    }
    
    /*
     * Hook to persist a <code>Supplier</code> list.
     */  
    protected List<Supplier> writeSupplierList() {
        int supplierListSize = 10;
        int partnerAssociationListSize = 2;
        List<Supplier> supplierList = SupplierTestSupport.createSupplierList(supplierListSize, partnerAssociationListSize);
        assertEquals(supplierListSize * partnerAssociationListSize, supplierList.size());
        for (Supplier supplier: supplierList) {
            partnerDao.persistPartner(supplier);
        }
        partnerDao.flush();
        partnerDao.clear();
        return supplierList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListSupplier() {
        List<Supplier> supplierList = writeSupplierList();

        Supplier supplier = supplierList.get((int) (Math.random()*supplierList.size()));
        assertEquals(supplier,  supplierDao.findSupplierByNaturalId(supplier.getPartnerRegistry(), supplier.getSequence()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testSupplierDuplicate() {
        Supplier supplier =  writeSupplier();
        Supplier supplierCopy = SupplierTestSupport.createSupplier(supplier.getPartnerRegistry(), supplier.getSequence());

        try {
            partnerDao.mergePartner(supplierCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveSupplier() {
        List<Supplier> supplierList = writeSupplierList();
        Supplier supplier = supplierList.get((int) (Math.random()*supplierList.size()));
        partnerDao.removePartner(supplier);

        assertNull(supplierDao.findSupplierByNaturalId(supplier.getPartnerRegistry(), supplier.getSequence()));
    }

    //- setters

    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
    
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}

