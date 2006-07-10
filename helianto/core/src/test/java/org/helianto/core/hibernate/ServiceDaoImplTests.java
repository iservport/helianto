/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.creation.ServiceCreator;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ServiceDaoImplTests extends AbstractIntegrationTest {
    
    private ServiceDao serviceDao;

    /*
     * Service tests 
     */
    
    public static Service createAndPersistService(ServiceDao serviceDao) {
        Service service = ServiceCreator.serviceFactory(generateKey(12));
        if (serviceDao!=null) {
            serviceDao.persistService(service);
        }
        return service;
    }

    public static List<Service> createAndPersistServiceList(HibernateTemplate hibernateTemplate, int i) {
        List<Service> serviceList = createServiceList(i);
        hibernateTemplate.saveOrUpdateAll(serviceList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return serviceList;
    }
    
    public static List<Service> createServiceList(int size) {
        List<Service> serviceList = new ArrayList<Service>();
        for (int i=0;i<size;i++) {
            serviceList.add(ServiceCreator.serviceFactory("SERVICE"+i));
        }
        return serviceList ;
    }
    
    public void testPersistService() {
        //write
        Service service = createAndPersistService(serviceDao);
        hibernateTemplate.flush();
        //read
        assertEquals(service,  serviceDao.findServiceByName(service.getServiceName()));
    }
    
    public void testFindService() {
        // write list
        int i = 10;
        List<Service> serviceList = createAndPersistServiceList(hibernateTemplate, i);
        assertEquals(i, serviceList.size());
        // read
        Service service = serviceList.get((int) Math.random()*i);
        assertEquals(service, serviceDao.findServiceByName(service.getServiceName()));
    }

    public void testServiceErrors() {
        try {
            serviceDao.persistService(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           serviceDao.removeService(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testServiceDuplicate() {
        // write
        Service service = createAndPersistService(serviceDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(service); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveService() {
        // bulk write
        int i = 10;
        List<Service> serviceList = createAndPersistServiceList(hibernateTemplate, i);
        assertEquals(i, serviceList.size());
        // remove
        Service service = serviceList.get((int) Math.random()*i);
        serviceDao.removeService(service);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Service> all = (ArrayList<Service>) hibernateTemplate.find("from Service");
        assertEquals(i-1, all.size());
        assertFalse(all.contains(service));
    }
    
    /*
     * UserRole tests 
     */
    
    public static UserRole createAndPersistUserRole(ServiceDao serviceDao) {
        User user = UserDaoImplTests.createAndPersistUser(serviceDao);
        Service service = createAndPersistService(serviceDao);
        UserRole userRole = ServiceCreator.userRoleFactory(user, service, "");
        if (serviceDao!=null) {
            serviceDao.persistUserRole(userRole);
        }
        return userRole;
    }

    public static List<UserRole> createAndPersistUserRoleList(HibernateTemplate hibernateTemplate, int eSize, int iSize, int sSize, int rSize) {
        List<UserRole> userRoleList = createUserRoleList(eSize, iSize, sSize, rSize);
        hibernateTemplate.saveOrUpdateAll(userRoleList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return userRoleList;
    }
    
    public static List<UserRole> createUserRoleList(int eSize, int iSize, int sSize, int rSize) {
        List<User> userList = UserDaoImplTests.createUsers(eSize, iSize);
        List<Service> serviceList = createServiceList(sSize);
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        for (User u: userList) {
            for (Service s: serviceList) {
                for (int i = 0;i<rSize;i++) {
                    if (i==0) {
                        userRoleList.add(ServiceCreator.userRoleFactory(u, s, ""));
                    } else {
                        userRoleList.add(ServiceCreator.userRoleFactory(u, s, "EXT"+i));
                    }
                }
            }
        }
        return userRoleList ;
    }

    
    
    public void testPersistUserRole() {
        //write
        UserRole userRole = createAndPersistUserRole( serviceDao);
        hibernateTemplate.flush();
        //read
        assertEquals(userRole,  serviceDao.findUserRole(userRole.getUser(), userRole.getService(), userRole.getServiceExtension()));
    }
    
    public void testFindUserRole() {
        // write list
        int e = 3, i = 4, s = 2, r = 2;
        List<UserRole> userRoleList = createAndPersistUserRoleList(hibernateTemplate, e, i, s, r);
        assertEquals(e*i*s*r, userRoleList.size());
        // read
        UserRole userRole = userRoleList.get((int) Math.random()*i);
        assertEquals(userRole,  serviceDao.findUserRole(userRole.getUser(), userRole.getService(), userRole.getServiceExtension()));
    }

    public void testUserRoleErrors() {
        try {
            serviceDao.persistUserRole(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           serviceDao.removeUserRole(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testUserRoleDuplicate() {
        // write
        UserRole userRole = createAndPersistUserRole(serviceDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(userRole); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveUserRole() {
        // bulk write
        int e = 3, i = 4, s = 2, r = 2;
        List<UserRole> userRoleList = createAndPersistUserRoleList(hibernateTemplate, e, i, s, r);
        assertEquals(e*i*s*r, userRoleList.size());
        // remove
        UserRole userRole = userRoleList.get((int) Math.random()*e*i*s*r);
        serviceDao.removeUserRole(userRole);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<UserRole> all = (ArrayList<UserRole>) hibernateTemplate.find("from UserRole");
        assertEquals(e*i*s*r-1, all.size());
        assertFalse(all.contains(userRole));
    }

    //~ collaborator mutators
    
    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

}
