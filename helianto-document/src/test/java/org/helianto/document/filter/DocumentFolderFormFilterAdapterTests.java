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
import org.helianto.document.form.AbstractCustomDocumentForm;
import org.helianto.document.form.DocumentFolderForm;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentFolderFormFilterAdapterTests {
	
	private String OB = "order by alias.priority ,  alias.folderCode ";
	private String C1 = "alias.entity.id = 1 ";
	private String C2 = "AND alias.folderCode = 'CODE' ";
	private String C3 = "AND alias.contentType = 'X' ";
	
	@Test
	public void empty() {
		assertEquals(C1+OB, filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		((AbstractCustomDocumentForm) form).setBuilderCode("CODE");
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void contentType() {
		((AbstractCustomDocumentForm) form).setContentType('X');
		assertEquals(C1+C3+OB, filter.createCriteriaAsString());
	}
	
	private DocumentFolderFormFilterAdapter<DocumentFolderForm> filter;
	private DocumentFolderForm form;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity();
		entity.setId(1);
		form = new AbstractCustomDocumentForm(entity) {};
		filter = new DocumentFolderFormFilterAdapter<DocumentFolderForm>(form);
	}
	
}
