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
import org.helianto.core.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityPropertyEditorTests extends TestCase {
	
	private EntityPropertyEditor entityPropertyEditor;

	public void testGetAsText() {
		Entity entity = new Entity();
		entity.setId(Integer.MAX_VALUE);
		entityPropertyEditor.setValue(entity);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals(String.valueOf(Integer.MAX_VALUE), entityPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}

	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Entity entity = new Entity();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Entity.class, 1)).andReturn(entity);
		EasyMock.replay(session);
		
		entityPropertyEditor.setAsText("1");
		assertSame(entity, entityPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private SessionFactory sessionFactory;
	
	@Override
	public void setUp() {
		entityPropertyEditor = new EntityPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		entityPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@Override
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
