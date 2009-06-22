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

package org.helianto.core.test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.assertSame;

import java.io.Serializable;

import org.easymock.EasyMock;
import org.helianto.core.validation.AbstractSessionPropertyEditor;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;

/**
 * Base test to Hibernate <code>Session</code> property editors.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractHibernatePropertyEditorTest<T, P extends AbstractSessionPropertyEditor> {
	
	protected abstract Class<T> getTargetClazz();
	
	protected abstract Class<P> getPropertyEditorClazz();
	
	protected Class<?> getIdClazz() {
		return Integer.class;
	}
	
	private Serializable getId() {
		if (getIdClazz().equals(Integer.class)) {
			return Integer.parseInt("1");
		}
		if (getIdClazz().equals(Long.class)) {
			return Long.parseLong("1");
		}
		throw new IllegalArgumentException("Not a valid id class"+getIdClazz());
	}
	
	@Test
	public void testSetAsTextString() throws InstantiationException, IllegalAccessException {
		T target = getTargetClazz().newInstance();
		
		EasyMock.expect(session.load(getTargetClazz(), getId())).andReturn(target);
		EasyMock.replay(session);
		
		propertyEditor.setAsText("1");
		assertSame(target, propertyEditor.getValue());
		EasyMock.verify(session);
	}
	
	private P propertyEditor;
	private org.hibernate.classic.Session session;
	SessionFactory sessionFactory;
	
	@Before
	public void setUp() throws InstantiationException, IllegalAccessException {
		propertyEditor = getPropertyEditorClazz().newInstance();
		session = createMock(Session.class);
		sessionFactory = createMock(SessionFactory.class);
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		replay(sessionFactory);
		propertyEditor.setSessionFactory(sessionFactory);
	}
	
	public void tearDown() {
		reset(session);
		reset(sessionFactory);
	}

}
