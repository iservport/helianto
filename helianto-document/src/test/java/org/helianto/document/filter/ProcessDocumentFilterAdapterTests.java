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

package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.def.InheritanceType;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.form.ProcessDocumentForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class ProcessDocumentFilterAdapterTests {
	
    public static String OB = "order by alias.docCode ";
    public static String C0 = "alias.entity.id = 1 ";
    public static String C1 = "AND alias.class=Operation ";
    public static String C2 = "AND alias.docCode = 'DOCCODE' ";
    public static String C3 = "AND lower(alias.docName) like '%name%' ";
    public static String C4 = "AND alias.inheritanceType = 'C' ";
    public static String S1 = "select alias from ProcessDocument alias inner join alias.parentAssociations as parentAssociations ";
    public static String C5 = "AND parentAssociations.parent.id = 100 ";

    @Test
    public void empty() {
        assertEquals(C0+OB, filter.createCriteriaAsString());
	}

    @Test
    public void select() {
		Mockito.when(form.getDocCode()).thenReturn("DOCCODE");
        assertEquals(C0+C2, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterName() {
		Mockito.when(form.getDocName()).thenReturn("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void filterInheritance() {
		Mockito.when(form.getInheritanceType()).thenReturn(InheritanceType.CONCRETE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString());
    }
    
    @Test
    public void parent() {
    	ProcessDocument parent = new ProcessDocument();
    	parent.setId(100);
		Mockito.when(form.getParent()).thenReturn(parent);
        assertEquals(S1, filter.createSelectAsString());
        assertEquals(C0+C5+OB, filter.createCriteriaAsString());
    }
    
    private ProcessDocumentForm form;
    private ProcessDocumentFormFilterAdapter filter;
     
    @Before
    public void setUp() {
    	Entity entity = EntityTestSupport.createEntity(1);
    	form = Mockito.mock(ProcessDocumentForm.class);
		Mockito.when(form.getEntity()).thenReturn(entity);
		filter = new ProcessDocumentFormFilterAdapter(form);
    }

	@After
	public void tearDown() {
		Mockito.reset(form);
	}

}
