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

package org.helianto.process;

import java.io.Serializable;

import junit.framework.TestCase;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.test.UserTestSupport;
import org.helianto.document.AbstractDocument;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Maurício Fernandes de Castro
 */
public class ProcessDocumentFilterTests extends TestCase {
	
	public void testConstructor() {
		ProcessDocumentFilter processDocumentFilter = new ProcessDocumentFilter();
		assertTrue(processDocumentFilter instanceof Serializable);
		assertTrue(processDocumentFilter instanceof AbstractUserBackedCriteriaFilter);
	}
	
	public void testFactoryUser() {
		User user = new User();
		Class<? extends ProcessDocument> clazz = Process.class;
		ProcessDocumentFilter processDocumentFilter = ProcessDocumentFilter.processDocumentFilterFactory(user, clazz);
		assertSame(processDocumentFilter.getUser(), user);
		assertEquals(processDocumentFilter.getClazz(), Process.class);
	}
	
	public void testFactoryHierarchy() {
		User user = new User();
		ProcessDocument document = new Process();
		ProcessDocumentFilter processDocumentFilter = ProcessDocumentFilter.processDocumentFilterFactory(user, document);
		assertSame(processDocumentFilter.getUser(), user);
		assertEquals(processDocumentFilter.getClazz(), Process.class);
		assertEquals(processDocumentFilter.getDocument(), document);
	}
	
	public void testDocument() {
		AbstractDocument document = new ProcessDocument() {
			private static final long serialVersionUID = 1L;
			public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}
		};
		ProcessDocumentFilter processDocumentFilter = 
			ProcessDocumentFilter.processDocumentFilterFactory(new User(), Process.class);
		processDocumentFilter.setDocument(document);
		assertSame(processDocumentFilter.getDocument(), document);
	}
	
	public void testClass() {
		Class<? extends ProcessDocument> clazz = Process.class;
		ProcessDocumentFilter processDocumentFilter = 
			ProcessDocumentFilter.processDocumentFilterFactory(new User(), Process.class);
		processDocumentFilter.setClazz(clazz);
		assertEquals(processDocumentFilter.getClazz(), Process.class);
	}
	
    public static String OB = "order by processdocument.docCode ";
    public static String C0 = "processdocument.entity.id = 0 ";
    public static String C1 = "AND processdocument.class=Operation ";
    public static String C2 = "AND processdocument.docCode = 'DOCCODE' ";
    public static String C3 = "AND lower(processdocument.docName) like '%name%' ";
    public static String C4 = "AND processdocument.inheritanceType = 'C' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterClazz() {
        filter.setClazz(Operation.class);
        assertEquals(C0+C1+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testSelect() {
    	filter.setDocCode("DOCCODE");
        assertEquals(C0+C2, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterName() {
        filter.setDocNameLike("NAME");
        assertEquals(C0+C3+OB, filter.createCriteriaAsString(false));
    }
    
    @Test
    public void testFilterInheritance() {
        filter.setInheritanceType(InheritanceType.CONCRETE.getValue());
        assertEquals(C0+C4+OB, filter.createCriteriaAsString(false));
    }
    
    private ProcessDocumentFilter filter;
    private User user;
    
    @Before
    public void setUp() {
    	user = UserTestSupport.createUser();
    	filter = ProcessDocumentFilter.processDocumentFilterFactory(user, ProcessDocument.class);
    }

}
