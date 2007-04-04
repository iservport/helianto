package org.helianto.partner.orm;

import java.util.List;

import org.helianto.core.test.AbstractIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;

import org.helianto.partner.Contact;
import org.helianto.partner.dao.ContactDao;
import org.helianto.partner.test.ContactTestSupport;

/**
 * <code>ContactDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class ContactDaoImplTests extends AbstractIntegrationTest {
    
    private ContactDao contactDao;
    
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
     * Hook to persist one <code>Contact</code>.
     */  
    protected Contact writeContact() {
        Contact contact = ContactTestSupport.createContact();
        contactDao.persistContact(contact);
        contactDao.flush();
        contactDao.clear();
        return contact;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneContact() {
        Contact contact = writeContact();

        assertEquals(contact,  contactDao.findContactByNaturalId(contact.getPartnerAssociation(), contact.getSequence()));
    }
    
    /*
     * Hook to persist a <code>Contact</code> list.
     */  
    protected List<Contact> writeContactList() {
        int contactListSize = 10;
        int identityListSize = 2;
        List<Contact> contactList = ContactTestSupport.createContactList(contactListSize, identityListSize);
        assertEquals(contactListSize * identityListSize, contactList.size());
        for (Contact contact: contactList) {
            contactDao.persistContact(contact);
        }
        contactDao.flush();
        contactDao.clear();
        return contactList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListContact() {
        List<Contact> contactList = writeContactList();

        Contact contact = contactList.get((int) (Math.random()*contactList.size()));
        assertEquals(contact,  contactDao.findContactByNaturalId(contact.getPartnerAssociation(), contact.getSequence()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testContactDuplicate() {
        Contact contact =  writeContact();
        Contact contactCopy = ContactTestSupport.createContact(contact.getPartnerAssociation(), contact.getSequence());

        try {
            contactDao.mergeContact(contactCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveContact() {
        List<Contact> contactList = writeContactList();
        Contact contact = contactList.get((int) (Math.random()*contactList.size()));
        contactDao.removeContact(contact);

        assertNull(contactDao.findContactByNaturalId(contact.getPartnerAssociation(), contact.getSequence()));
    }

    //- setters

    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
    
}

