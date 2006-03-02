//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Service;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Owner domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceTests extends AbstractCoreTest {
    
    public ServiceTests() {
        setKeyType(Integer.class);
        setBaseQuery("from Service");
    }

    public Object first() {
        Service obj = (Service) getCollaborator(Service.class);
        return obj;
    }

    public Object changeUniqueKey(Object object) {
        Service service = (Service) object;
        service.setServiceName(generateKey());
        logger.info("\n         Unique key changed, [ " +
                service.toString()+
                " details [ serviceName = "+service.getServiceName()+
                " ]]");
        return service;
    }

    public Object third() {
        Service obj = (Service) getNewCollaborator(Service.class);
        return obj;
    }

}
