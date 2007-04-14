package org.helianto.partner.orm;

import java.util.List;

import org.helianto.partner.Address;
import org.helianto.partner.dao.AddressDao;
import org.helianto.partner.test.AddressTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>AddressDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class AddressDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private AddressDao addressDao;
    
    /*
     * Hook to persist one <code>Address</code>.
     */  
    protected Address writeAddress() {
        Address address = AddressTestSupport.createAddress();
        addressDao.persistAddress(address);
        addressDao.flush();
        addressDao.clear();
        return address;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneAddress() {
        Address address = writeAddress();

        assertEquals(address,  addressDao.findAddressByNaturalId(address.getPartnerAssociation(), address.getSequence()));
    }
    
    /*
     * Hook to persist a <code>Address</code> list.
     */  
    protected List<Address> writeAddressList() {
        int addressListSize = 10;
        int partnerAssociationListSize = 2;
        List<Address> addressList = AddressTestSupport.createAddressList(addressListSize, partnerAssociationListSize);
        assertEquals(addressListSize * partnerAssociationListSize, addressList.size());
        for (Address address: addressList) {
            addressDao.persistAddress(address);
        }
        addressDao.flush();
        addressDao.clear();
        return addressList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListAddress() {
        List<Address> addressList = writeAddressList();

        Address address = addressList.get((int) (Math.random()*addressList.size()));
        assertEquals(address,  addressDao.findAddressByNaturalId(address.getPartnerAssociation(), address.getSequence()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testAddressDuplicate() {
        Address address =  writeAddress();
        Address addressCopy = AddressTestSupport.createAddress(address.getPartnerAssociation(), address.getSequence());

        try {
            addressDao.mergeAddress(addressCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveAddress() {
        List<Address> addressList = writeAddressList();
        Address address = addressList.get((int) (Math.random()*addressList.size()));
        addressDao.removeAddress(address);

        assertNull(addressDao.findAddressByNaturalId(address.getPartnerAssociation(), address.getSequence()));
    }

    //- setters

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    
}

