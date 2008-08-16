package org.helianto.partner.orm;

import java.util.List;

import org.helianto.partner.Manufacturer;
import org.helianto.partner.dao.ManufacturerDao;
import org.helianto.partner.dao.PartnerDao;
import org.helianto.partner.test.ManufacturerTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>ManufacturerDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class ManufacturerDaoImplTests extends AbstractPartnerDaoImplConfig {

    private ManufacturerDao manufacturerDao;
    private PartnerDao partnerDao;
    
    /*
     * Hook to persist one <code>Manufacturer</code>.
     */  
    protected Manufacturer writeManufacturer() {
        Manufacturer manufacturer = ManufacturerTestSupport.createManufacturer();
        partnerDao.persistPartner(manufacturer);
        partnerDao.flush();
        partnerDao.clear();
        return manufacturer;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneManufacturer() {
        Manufacturer manufacturer = writeManufacturer();

        assertEquals(manufacturer,  manufacturerDao.findManufacturerByNaturalId(manufacturer.getPartnerRegistry()));
    }
    
    /*
     * Hook to persist a <code>Manufacturer</code> list.
     */  
    protected List<Manufacturer> writeManufacturerList() {
        int manufacturerListSize = 10;
        List<Manufacturer> manufacturerList = ManufacturerTestSupport.createManufacturerList(manufacturerListSize);
        assertEquals(manufacturerListSize, manufacturerList.size());
        for (Manufacturer manufacturer: manufacturerList) {
            partnerDao.persistPartner(manufacturer);
        }
        partnerDao.flush();
        partnerDao.clear();
        return manufacturerList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListManufacturer() {
        List<Manufacturer> manufacturerList = writeManufacturerList();

        Manufacturer manufacturer = manufacturerList.get((int) (Math.random()*manufacturerList.size()));
        assertEquals(manufacturer,  manufacturerDao.findManufacturerByNaturalId(manufacturer.getPartnerRegistry()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testManufacturerDuplicate() {
        Manufacturer manufacturer =  writeManufacturer();
        Manufacturer manufacturerCopy = ManufacturerTestSupport.createManufacturer(manufacturer.getPartnerRegistry());

        try {
            partnerDao.mergePartner(manufacturerCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveManufacturer() {
        List<Manufacturer> manufacturerList = writeManufacturerList();
        Manufacturer manufacturer = manufacturerList.get((int) (Math.random()*manufacturerList.size()));
        partnerDao.removePartner(manufacturer);

        assertNull(manufacturerDao.findManufacturerByNaturalId(manufacturer.getPartnerRegistry()));
    }

    //- setters

    public void setManufacturerDao(ManufacturerDao manufacturerDao) {
        this.manufacturerDao = manufacturerDao;
    }
    
    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }
    
}

