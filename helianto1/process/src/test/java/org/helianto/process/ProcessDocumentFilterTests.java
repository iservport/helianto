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
		Document document = new ProcessDocument();
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
	
}
