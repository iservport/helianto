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

import org.helianto.core.domain.Operator;
import org.helianto.core.filter.form.ServiceForm;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceFormFilterAdapterTests {
	
    public static String C0 = "alias.operator.id = 1 ";
    public static String C1 = "AND alias.serviceName = 'NAME' ";
    public static String C2 = "AND lower(alias.serviceName) like '%name_like%' ";

    @Test
    public void empty() {
        assertEquals(C0, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	serviceName = "NAME";
        assertEquals(C0+C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void serviceNameLike() {
    	serviceNameLike = "NAME_LIKE";
    	assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    private ServiceFormFilterAdapter filter;
    private ServiceForm form;
    private Operator operator;
    private String serviceName;
    private String serviceNameLike;
    
    @SuppressWarnings("serial")
	@Before
    public void setUp() {
    	operator = OperatorTestSupport.createOperator();
    	operator.setId(1);
    	form = new ServiceForm() {
			public Operator getOperator() { return operator; }
			public String getServiceName() { return serviceName; }
			public String getServiceNameLike() { return serviceNameLike; }
    	};
    	filter = new ServiceFormFilterAdapter(form);
    }

}
