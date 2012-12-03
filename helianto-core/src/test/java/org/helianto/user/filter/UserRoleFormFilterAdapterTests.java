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

package org.helianto.user.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.def.ActivityState;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.form.UserRoleForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    public static String C5 = "lower(alias.serviceExtension) like '%read%' ";
    public static String C6 = "alias.activityState = 'C' ";

    @Test
    public void empty() {
        assertEquals("", filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	Mockito.when(form.getUserGroupParentId()).thenReturn(1);
    	Mockito.when(form.getServiceId()).thenReturn(2);
    	Mockito.when(form.getServiceExtension()).thenReturn("EXT");
        assertEquals(C0+C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void parentList() {
    	Mockito.when(form.getUserGroupParentIdArray()).thenReturn(new int[] {1,10});
        assertEquals(C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void serviceExtension() {
    	Mockito.when(form.getServiceExtension()).thenReturn("READ");
    	assertEquals(C5, filter.createCriteriaAsString());
    }
    
    @Test
    public void activityState() {
    	Mockito.when(form.getActivityState()).thenReturn(ActivityState.CANCELLED.getValue());
    	assertEquals(C6, filter.createCriteriaAsString());
    }
    
    private UserRoleFormFilterAdapter filter;
    private UserRoleForm form;
    private UserGroup userGroup;
    
    @Before
    public void setUp() {
    	userGroup = new UserGroup(EntityTestSupport.createEntity(), "UG");
    	userGroup.setId(1);
     	form = Mockito.mock(UserRoleForm.class);
    	filter = new UserRoleFormFilterAdapter(form);
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }

}
