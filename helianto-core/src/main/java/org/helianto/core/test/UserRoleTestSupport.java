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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.domain.Service;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;

/**
 * Class to support <code>UserRoleDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>UserRole</code>.
     * @param userGroup optional UserGroup 
     * @param service optional Service 
     * @param serviceExtension optional String 
     */
    public static UserRole createUserRole(Object... args) {
        UserGroup userGroup;
        try {
            userGroup = (UserGroup) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            userGroup = UserGroupTestSupport.createUserGroup();
        }
        Service service;
        try {
            service = (Service) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            service = ServiceTestSupport.createService();
        }
        String serviceExtension;
        try {
            serviceExtension = (String) args[2];
        } catch(ArrayIndexOutOfBoundsException e) {
            serviceExtension = DomainTestSupport.getNonRepeatableStringValue(testKey++, 8);
        }
        UserRole userRole = new UserRole(userGroup, service, serviceExtension);
        return userRole;
    }

    /**
     * Test support method to create a <code>UserRole</code> list.
     *
     * @param userRoleListSize
     */
    public static List<UserRole> createUserRoleList(int userRoleListSize) {
        return createUserRoleList(userRoleListSize, 1, 1);
    }

    /**
     * Test support method to create a <code>UserRole</code> list.
     *
     * @param userRoleListSize
     * @param userGroupListSize
     * @param serviceListSize
     */
    public static List<UserRole> createUserRoleList(int userRoleListSize, int userGroupListSize, int serviceListSize) {
        List<UserGroup> userGroupList = UserGroupTestSupport.createUserGroupList(userGroupListSize);
        List<Service> serviceList = ServiceTestSupport.createServiceList(serviceListSize);

        return createUserRoleList(userRoleListSize, userGroupList, serviceList);
    }

    /**
     * Test support method to create a <code>UserRole</code> list.
     *
     * @param userRoleListSize
     * @param userGroupList
     * @param serviceList
     */
    public static List<UserRole> createUserRoleList(int userRoleListSize, List<UserGroup> userGroupList, List<Service> serviceList) {
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        for (UserGroup userGroup: userGroupList) {
        for (Service service: serviceList) {
	        for (int i=0;i<userRoleListSize;i++) {
    	        userRoleList.add(createUserRole(userGroup, service));
        	}
        }
        }
        return userRoleList;
    }

    public static UserRole[] createUserRoles(UserGroup user, String... extensions) {
        UserRole[] roles = new UserRole[extensions.length];
        for (int i = 0;i<extensions.length;i++) {
            UserRole role = new UserRole();
            Service service = new Service();
            role.setUserGroup(user);
            user.getRoles().add(role);
            role.setService(service);
            role.setServiceExtension(extensions[i]);
            System.out.println(role);
            roles[i] = role;
        }
        return roles;
    }
    
}
