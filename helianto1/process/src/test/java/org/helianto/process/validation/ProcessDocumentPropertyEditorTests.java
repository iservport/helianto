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

package org.helianto.process.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.process.DocumentAssociation;
import org.helianto.process.ProcessDocument;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentPropertyEditorTests extends TestCase {
	
	private ProcessDocumentPropertyEditor processDocumentPropertyEditor;

	public void testGetAsText() {
		ProcessDocument processDocument = new ProcessDocument(){
			private static final long serialVersionUID = 1L;
			public DocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}
		};
		processDocument.setId(Integer.MAX_VALUE);
		processDocumentPropertyEditor.setValue(processDocument);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals(String.valueOf(Integer.MAX_VALUE), processDocumentPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		ProcessDocument processDocument = new ProcessDocument(){
			private static final long serialVersionUID = 1L;
			public DocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}
		};
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(ProcessDocument.class, Integer.MAX_VALUE)).andReturn(processDocument);
		EasyMock.replay(session);
		
		processDocumentPropertyEditor.setAsText(String.valueOf(Integer.MAX_VALUE));
		assertSame(processDocument, processDocumentPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		processDocumentPropertyEditor = new ProcessDocumentPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		processDocumentPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
