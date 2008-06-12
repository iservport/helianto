package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Service;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.ServiceTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>ServiceDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class ServiceDaoImplTests extends AbstractHibernateIntegrationTest {

    private ServiceDao serviceDao;
    
    /*
     * Hook to persist one <code>Service</code>.
     */  
    protected Service writeService() {
        Service service = ServiceTestSupport.createService();
        serviceDao.persistService(service);
        serviceDao.flush();
        serviceDao.clear();
        return service;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneService() {
        Service service = writeService();

        assertEquals(service,  serviceDao.findServiceByNaturalId(service.getOperator(), service.getServiceName()));
    }
    
    /*
     * Hook to persist a <code>Service</code> list.
     */  
    protected List<Service> writeServiceList() {
        int serviceListSize = 10;
        int operatorListSize = 2;
        List<Service> serviceList = ServiceTestSupport.createServiceList(serviceListSize, operatorListSize);
        assertEquals(serviceListSize * operatorListSize, serviceList.size());
        for (Service service: serviceList) {
            serviceDao.persistService(service);
        }
        serviceDao.flush();
        serviceDao.clear();
        return serviceList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListService() {
        List<Service> serviceList = writeServiceList();

        Service service = serviceList.get((int) (Math.random()*serviceList.size()));
        assertEquals(service,  serviceDao.findServiceByNaturalId(service.getOperator(), service.getServiceName()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testServiceDuplicate() {
        Service service =  writeService();
        Service serviceCopy = ServiceTestSupport.createService(service.getOperator(), service.getServiceName());

        try {
            serviceDao.mergeService(serviceCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveService() {
        List<Service> serviceList = writeServiceList();
        Service service = serviceList.get((int) (Math.random()*serviceList.size()));
        serviceDao.removeService(service);

        assertNull(serviceDao.findServiceByNaturalId(service.getOperator(), service.getServiceName()));
    }

    //- setters

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
    
}

