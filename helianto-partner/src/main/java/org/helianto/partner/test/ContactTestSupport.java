package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Contact;
import org.helianto.partner.PrivateEntity;

/**
 * Class to support <code>ContactDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ContactTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Contact</code>.
     * @param partnerAssociation optional PartnerAssociation 
     * @param sequence optional int 
     * @param identity optional Identity 
     */
    public static Contact createContact(Object... args) {
        PrivateEntity partnerAssociation;
        try {
            partnerAssociation = (PrivateEntity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PrivateEntityTestSupport.createPartnerRegistry();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Identity identity;
        try {
            identity = (Identity) args[2];
        } catch(ArrayIndexOutOfBoundsException e) {
            identity = IdentityTestSupport.createIdentity();
        }
        Contact contact = new Contact(partnerAssociation, sequence, identity);
        return contact;
    }

    /**
     * Test support method to create a <code>Contact</code> list.
     *
     * @param contactListSize
     */
    public static List<Contact> createContactList(int contactListSize) {
        return createContactList(contactListSize, 1);
    }

    /**
     * Test support method to create a <code>Contact</code> list.
     *
     * @param contactListSize
     * @param partnerAssociationListSize
     */
    public static List<Contact> createContactList(int contactListSize, int partnerAssociationListSize) {
        List<PrivateEntity> partnerAssociationList = PrivateEntityTestSupport.createPartnerRegistryList(partnerAssociationListSize);

        return createContactList(contactListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Contact</code> list.
     *
     * @param contactListSize
     * @param partnerAssociationList
     */
    public static List<Contact> createContactList(int contactListSize, List<PrivateEntity> partnerAssociationList) {
        List<Contact> contactList = new ArrayList<Contact>();
        for (PrivateEntity privateEntity: partnerAssociationList) {
            for (int i=0;i<contactListSize;i++) {
                contactList.add(createContact(privateEntity));
            }
        }
        return contactList;
    }

}
