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

package org.helianto.core.junit;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.creation.ServiceCreator;
import org.helianto.core.dao.ServiceDao;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AbstractServiceTest extends AbstractUserTest {

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

    public static UserRole createAndPersistUserRole(ServiceDao serviceDao) {
        User user = createAndPersistUser(serviceDao);
        Service service = createAndPersistService(serviceDao);
        UserRole userRole = ServiceCreator.userRoleFactory(user, service, "");
        if (serviceDao!=null) {
            serviceDao.persistUserRole(userRole);
        }
        return userRole;
    }

    public static List<UserRole> createAndPersistUserRoleList(HibernateTemplate hibernateTemplate, int eSize, int iSize, int sSize, int rSize) {
        List<UserRole> userRoleList = createUserRoleList(eSize, iSize, sSize, rSize);
        for (UserRole x: userRoleList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return userRoleList;
    }

    public static List<UserRole> createUserRoleList(int eSize, int cSize, int sSize, int rSize) {
        List<User> userList = createUsers(eSize, cSize);
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

}
