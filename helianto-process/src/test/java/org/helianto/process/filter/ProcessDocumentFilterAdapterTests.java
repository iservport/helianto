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

package org.helianto.process.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.InheritanceType;
import org.helianto.process.Operation;
import org.helianto.process.ProcessDocument;
import org.helianto.process.filter.ProcessDocumentFilterAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class ProcessDocumentFilterAdapterTests {
	
	@Test
	public void constructor() {
		Entity entity = new Entity();
		filter = new ProcessDocumentFilterAdapter(entity, "DOC_CODE");
		assertSame(filter.getEntity(), entity);
		assertEquals(filter.getFilter().getDocCode(), "DOC_CODE");
	}
	
	
    public static String OB = "order by alias.docCode ";
    public static String C0 = "alias.entity.id = 0 ";
    public static String C1 = "AND alias.class=Operation ";
    public static String C2 = "AND alias.docCode = 'DOCCODE' ";
    public static String C3 = "AND lower(alias.docName) like '%name%' ";
    public static String C4 = "AND alias.inheritanceType = 'C' ";
    public static String S1 = "select alias from ProcessDocument alias inner join alias.parentAssociations as parentAssociations ";
    public static String C5 = "AND parentAssociations.parent.id = 100 ";

    @Test
    public void empty() {
    	target.setDocCode("");
        assertEquals(C0+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterClazz() {
        filter.setClazz(Operation.class);
        assertEquals(C0+C1+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void select() {
    	target.setDocCode("DOCCODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
    	target.setDocName("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterInheritance() {
    	target.setInheritanceType(InheritanceType.CONCRETE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	ProcessDocument parent = new ProcessDocument();
    	parent.setId(100);
    	filter.setParent(parent);
        assertEquals(S1, filter.createSelectAsString());
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    private ProcessDocumentFilterAdapter filter;
    private ProcessDocument target;
    
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity();
    	target = new ProcessDocument(entity, "");
		filter = new ProcessDocumentFilterAdapter(target);
    }

}
