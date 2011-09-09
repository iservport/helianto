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
import org.helianto.core.filter.form.UserRoleForm;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleFormFilterAdapterTests {
	
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
    	userGroup = new UserGroup(EntityTestSupport.createEntity(), "UG");
    	userGroup.setId(1);
    	service = new Service(OperatorTestSupport.createOperator(), "SERVICE");
    	service.setId(2);
    	serviceExtension = "EXT";
        assertEquals(C0+C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void parentList() {
    	userGroup = new UserGroup(EntityTestSupport.createEntity(), "UG");
    	userGroup.setId(1);
    	UserGroup parentGroup = new UserGroup(userGroup.getEntity(), "PG");
    	parentGroup.setId(10);
    	List<UserGroup> parentList = new ArrayList<UserGroup>();
    	parentList.add(userGroup);
    	parentList.add(parentGroup);
    	form.setParentList(parentList);
        assertEquals(C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void activityState() {
    	userGroup = new UserGroup(EntityTestSupport.createEntity(), "UG");
    	userGroup.setId(1);
    	activityState = ActivityState.CANCELLED.getValue();
    	assertEquals(C0+C5, filter.createCriteriaAsString());
    }
    
    private UserRoleFormFilterAdapter filter;
    private UserRoleForm form;
    
	private UserGroup userGroup;
	private Service service;
	private String serviceExtension;
	private char activityState = ' ';
    
    @Before
    public void setUp() {
    	form = new UserRoleForm() {
    		private List<UserGroup> parentList;
			public void reset() { }
			public List<UserGroup> getParentList() { return parentList; }
			public void setParentList(List<UserGroup> parentList) { this.parentList = parentList; }
			public UserGroup getUserGroup() { return userGroup; }
			public Service getService() { return service; }
			public String getServiceExtension() { return serviceExtension; }
			public char getActivityState() { return activityState; }
     	};
    	filter = new UserRoleFormFilterAdapter(form);
    }

}
