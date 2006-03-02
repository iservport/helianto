//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Customer;
import org.helianto.core.Partner;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Owner domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerTests extends AbstractCoreTest {
    
    public PartnerTests() {
        setKeyType(Long.class);
        setBaseQuery("from Partner");
    }

    public Object first() {
        Partner partner = (Partner) getCollaborator(Partner.class);
        return partner;
    }

    public Object changeUniqueKey(Object object) {
        Partner partner = (Partner) object;
        partner.setAlias(generateKey());
        logger.info("\n         Unique key changed, [ " +
                object.toString()+
                " details [ id = "+partner.getId()+
                ", alias = "+partner.getAlias()+
                " ]]");
        return partner;
    }

    public Object third() {
        Partner partner = (Partner) getNewCollaborator(Partner.class);
        return partner;
    }
    
    public void testCustomer() {
        logger.info("\n         Customer created");
        Customer customer = (Customer) getCollaborator(Customer.class);
        getGenericDao().merge(customer);
        assertNotNull(customer.getId());
    }

}
