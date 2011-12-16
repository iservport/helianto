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

package org.helianto.partner.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.form.CompositePartnerForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class PrivateEntityFilterAdapterTests {
	
    public static String C1 = "alias.entity.id = 1 ";
    public static String C2 = "AND alias.entityAlias = 'ALIAS' ";
    public static String C3 = "AND lower(alias.entityName) like '%name%' ";
    public static String C4 = "AND lower(alias.postalCode) like '12345%' ";
    public static String C5 = "AND alias.province.id = 10 ";
    public static String C6 = "AND lower(alias.cityName) like '%name%' ";

    @Test
    public void empty() {
        assertEquals(C1, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	((CompositePartnerForm) form).setEntityAlias("ALIAS");
        assertEquals(C1+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void name() {
        ((CompositePartnerForm) form).setEntityName("NAME");
        assertEquals(C1+C3, filter.createCriteriaAsString());
    }
    
    @Test
    public void postalCode() {
        ((CompositePartnerForm) form).setPostalCode("12345");
        assertEquals(C1+C4, filter.createCriteriaAsString());
    }
    
    @Test
    public void province() {
    	Province province = new Province(new Operator("DEFAULT"), "PROV");
    	province.setId(10);
        ((CompositePartnerForm) form).setProvince(province);
        assertEquals(C1+C5, filter.createCriteriaAsString());
    }
    
    @Test
    public void city() {
        ((CompositePartnerForm) form).setCityName("NAME");
        assertEquals(C1+C6, filter.createCriteriaAsString());
    }
    
    private PrivateEntityFormFilterAdapter filter;
    private CompositePartnerForm form;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	form = new CompositePartnerForm(entity);
    	filter = new PrivateEntityFormFilterAdapter(form);
    }
	
}
