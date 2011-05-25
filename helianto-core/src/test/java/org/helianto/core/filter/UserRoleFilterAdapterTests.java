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

package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleFilterAdapterTests {
	
    public static String C0 = "alias.userGroup.id = 1 ";
    public static String C1 = "AND alias.service.id = 2 ";
    public static String C2 = "AND alias.serviceExtension = 'EXT' ";
    public static String C3 = "AND lower(alias.categoryName) like '%name_like%' ";
    public static String C4 = "alias.userGroup.id in (1, 10) ";
    public static String C5 = "AND alias.activityState = 'C' ";

    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	form.setUserGroup(userGroup);
    	form.setService(service);
    	form.setServiceExtension("EXT");
        assertEquals(C0+C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void parentList() {
    	UserGroup parentGroup = new UserGroup(userGroup.getEntity(), "PG");
    	parentGroup.setId(10);
    	List<UserGroup> parentList = new ArrayList<UserGroup>();
    	parentList.add(userGroup);
    	parentList.add(parentGroup);
    	filter.setParentList(parentList);
        assertEquals(C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void activityState() {
    	form.setUserGroup(userGroup);
    	form.setActivityStateAsEnum(ActivityState.CANCELLED);
    	assertEquals(C0+C5, filter.createCriteriaAsString());
    }
    
    private UserRoleFilterAdapter filter;
    private UserRole form;
    private UserGroup userGroup;
    private Service service;
    
    @Before
    public void setUp() {
    	userGroup = new UserGroup(EntityTestSupport.createEntity(), "UG");
    	userGroup.setId(1);
    	service = new Service(OperatorTestSupport.createOperator(), "SERVICE");
    	service.setId(2);
    	form = new UserRole();
    	filter = new UserRoleFilterAdapter(form);
    }

}
