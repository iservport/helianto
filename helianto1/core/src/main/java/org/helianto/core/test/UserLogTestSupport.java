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
import java.util.Date;
import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;

/**
 * Class to support <code>UserLogDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserLogTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>UserLog</code>.
     * @param user optional User 
     * @param lastEvent optional Date 
     */
    public static UserLog createUserLog(Object... args) {
        User user;
        try {
            user = (User) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            user = UserTestSupport.createUser();
        }
        Date lastEvent;
        try {
            lastEvent = (Date) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            lastEvent = DomainTestSupport.getNonRepeatableDateValue(testKey++);
        }
        UserLog userLog = UserLog.userLogFactory(user, lastEvent);
        return userLog;
    }

    /**
     * Test support method to create a <code>UserLog</code> list.
     *
     * @param userLogListSize
     */
    public static List<UserLog> createUserLogList(int userLogListSize) {
        return createUserLogList(userLogListSize, 1);
    }

    /**
     * Test support method to create a <code>UserLog</code> list.
     *
     * @param userLogListSize
     * @param userListSize
     */
    public static List<UserLog> createUserLogList(int userLogListSize, int userListSize) {
        List<User> userList = UserTestSupport.createUserList(userListSize);

        return createUserLogList(userLogListSize, userList);
    }

    /**
     * Test support method to create a <code>UserLog</code> list.
     *
     * @param userLogListSize
     * @param userList
     */
    public static List<UserLog> createUserLogList(int userLogListSize, List<User> userList) {
        List<UserLog> userLogList = new ArrayList<UserLog>();
        for (UserGroup user: userList) {
	        for (int i=0;i<userLogListSize;i++) {
    	        userLogList.add(createUserLog(user));
        	}
        }
        return userLogList;
    }

}
