package org.helianto.partner.orm;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.Phone;
import org.helianto.partner.dao.PhoneDao;
import org.helianto.partner.test.PhoneTestSupport;

/**
 * <code>PhoneDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class PhoneDaoImplTests extends AbstractPartnerDaoImplConfig {
    
    private PhoneDao phoneDao;
    
    /*
     * Hook to persist one <code>Phone</code>.
     */  
    protected Phone writePhone() {
        Phone phone = PhoneTestSupport.createPhone();
        phoneDao.persistPhone(phone);
        phoneDao.flush();
        phoneDao.clear();
        return phone;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOnePhone() {
        Phone phone = writePhone();

        assertEquals(phone,  phoneDao.findPhoneByNaturalId(phone.getAddress(), phone.getSequence()));
    }
    
    /*
     * Hook to persist a <code>Phone</code> list.
     */  
    protected List<Phone> writePhoneList() {
        int phoneListSize = 10;
        int addressListSize = 2;
        List<Phone> phoneList = PhoneTestSupport.createPhoneList(phoneListSize, addressListSize);
        assertEquals(phoneListSize * addressListSize, phoneList.size());
        for (Phone phone: phoneList) {
            phoneDao.persistPhone(phone);
        }
        phoneDao.flush();
        phoneDao.clear();
        return phoneList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListPhone() {
        List<Phone> phoneList = writePhoneList();

        Phone phone = phoneList.get((int) (Math.random()*phoneList.size()));
        assertEquals(phone,  phoneDao.findPhoneByNaturalId(phone.getAddress(), phone.getSequence()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testPhoneDuplicate() {
        Phone phone =  writePhone();
        Phone phoneCopy = PhoneTestSupport.createPhone(phone.getAddress(), phone.getSequence());

        try {
            phoneDao.mergePhone(phoneCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemovePhone() {
        List<Phone> phoneList = writePhoneList();
        Phone phone = phoneList.get((int) (Math.random()*phoneList.size()));
        phoneDao.removePhone(phone);

        assertNull(phoneDao.findPhoneByNaturalId(phone.getAddress(), phone.getSequence()));
    }

    //- setters

    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }
    
}

