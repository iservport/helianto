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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.easymock.EasyMock;
import org.helianto.core.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityPropertyEditorTests {
	
	@Test
	public void testGetAsText() {
		Entity entity = new Entity();
		entity.setAlias("ALIAS");
		entityPropertyEditor.setValue(entity);
		
		EasyMock.replay(sessionFactory);
		
		assertEquals("ALIAS", entityPropertyEditor.getAsText());
		EasyMock.verify(sessionFactory);
	}
	
	@Test
	public void testSetAsTextString() {
		Session session = EasyMock.createMock(Session.class);
		Entity entity = new Entity();
		
		EasyMock.expect(sessionFactory.getCurrentSession()).andReturn(session);
		EasyMock.replay(sessionFactory);
		
		EasyMock.expect(session.load(Entity.class, Long.MAX_VALUE)).andReturn(entity);
		EasyMock.replay(session);
		
		entityPropertyEditor.setAsText(String.valueOf(Long.MAX_VALUE));
		assertSame(entity, entityPropertyEditor.getValue());
		EasyMock.verify(sessionFactory);
		
		EasyMock.reset(session);
	}
	
	private EntityPropertyEditor entityPropertyEditor;
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		entityPropertyEditor = new EntityPropertyEditor();
		sessionFactory = EasyMock.createMock(SessionFactory.class);
		entityPropertyEditor.setSessionFactory(sessionFactory);
		
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(sessionFactory);
	}

}
