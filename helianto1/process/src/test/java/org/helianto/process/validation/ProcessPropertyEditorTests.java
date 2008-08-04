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
import org.helianto.process.Process;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessPropertyEditorTests extends TestCase {
	
	private ProcessPropertyEditor processPropertyEditor;

	public void testGetAsText() {
		Process process = new Process();
		process.setId(Integer.MAX_VALUE);
		processPropertyEditor.setValue(process);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals(String.valueOf(Integer.MAX_VALUE), processPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Process process = new Process();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Process.class, Integer.MAX_VALUE)).andReturn(process);
		EasyMock.replay(session);
		
		processPropertyEditor.setAsText(String.valueOf(Integer.MAX_VALUE));
		assertSame(process, processPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		processPropertyEditor = new ProcessPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		processPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
