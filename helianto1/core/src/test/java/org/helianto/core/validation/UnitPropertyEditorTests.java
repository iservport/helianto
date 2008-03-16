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

package org.helianto.core.validation;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.helianto.core.Unit;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UnitPropertyEditorTests extends TestCase {
	
	private UnitPropertyEditor unitPropertyEditor;

	public void testGetAsText() {
		Unit unit = new Unit();
		unit.setUnitCode("CODE");
		unitPropertyEditor.setValue(unit);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("CODE", unitPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Unit unit = new Unit();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Unit.class, 1)).andReturn(unit);
		EasyMock.replay(session);
		
		unitPropertyEditor.setAsText("1");
		assertSame(unit, unitPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		unitPropertyEditor = new UnitPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		unitPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
