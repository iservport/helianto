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


package org.helianto.core.orm;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.dao.AbstractBasicDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractBasicDaoTests {
	
	@Test
	public void testFindUnique() {
		List<User> resultList = new ArrayList<User>();
		User resultUser = new User();
		resultList.add(resultUser);
		
		Entity entity = new Entity();
		long internalNumber = Long.MAX_VALUE;
		
		Query result = createMock(Query.class);
		
		expect(session.createQuery("select user from User user where user.entity = ? AND user.internalNumber = ? ")).andReturn(result);
		replay(session);
		
		expect(result.setParameter(0, entity)).andReturn(result);
		expect(result.setParameter(1, internalNumber)).andReturn(result);
		expect(result.list()).andReturn(resultList);
		replay(result);
		
		assertSame(resultUser, sampleDao.findUnique(entity, internalNumber));
		verify(session);
		verify(sessionFactory);
		verify(result);
	}
	
	@Test
	public void testFindUniqueWithParams() {
		
		sampleDao = new SampleDao() {
			@Override
			protected String[] getParams() {
				return new String[] { "KEY1", "KEY2" };
			}
		};
		sampleDao.setSessionFactory(sessionFactory);

		List<User> resultList = new ArrayList<User>();
		User resultUser = new User();
		resultList.add(resultUser);
		
		Query result = createMock(Query.class);
		
		expect(session.createQuery("select user from User user where user.KEY1 = ? AND user.KEY2 = ? ")).andReturn(result);
		replay(session);
		
		expect(result.setParameter(0, "VALUE1")).andReturn(result);
		expect(result.setParameter(1, "VALUE2")).andReturn(result);
		expect(result.list()).andReturn(resultList);
		replay(result);
		
		assertSame(resultUser, sampleDao.findUnique("VALUE1", "VALUE2"));
		verify(session);
		verify(sessionFactory);
		verify(result);
	}
	
	@Test
	public void testFindWithClause() {
		List<User> resultList = new ArrayList<User>();
		
		Query result = createMock(Query.class);
		
		expect(session.createQuery("select user from User user where CLAUSE")).andReturn(result);
		replay(session);
		
		expect(result.list()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find("CLAUSE"));
		verify(session);
		verify(sessionFactory);
		verify(result);
	}
	
	@Test
	public void testFindWithoutClause() {
		List<User> resultList = new ArrayList<User>();
		
		Query result = createMock(Query.class);
		
		expect(session.createQuery("select user from User user ")).andReturn(result);
		replay(session);
		
		expect(result.list()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find(""));
		verify(session);
		verify(sessionFactory);
		verify(result);
	}
	
	@Test
	public void testMerge() {
		User user = new User(), managedUser = new User();
		
		expect(session.merge(user)).andReturn(managedUser);
		replay(session);
		
		assertSame(managedUser, sampleDao.merge(user));
		verify(session);
		verify(sessionFactory);
	}
	
	@Test
	public void testPersist() {
		User user = new User();
		
		session.persist(user);
		replay(session);
		
		sampleDao.persist(user);
		verify(session);
		verify(sessionFactory);
	}
	
	@Test
	public void testEvict() {
		User user = new User();
		
		session.evict(user);
		replay(session);
		
		sampleDao.evict(user);
		verify(session);
		verify(sessionFactory);
	}
	
	@Test
	public void testRemove() {
		User user = new User();
		
		session.delete(user);
		replay(session);
		
		sampleDao.remove(user);
		verify(session);
		verify(sessionFactory);
	}
	
	@Test
	public void testFlush() {
		session.flush();
		replay(session);
		
		sampleDao.flush();
		verify(session);
		verify(sessionFactory);
	}
	
	@Test
	public void testClear() {
		session.clear();
		replay(session);
		
		sampleDao.clear();
		verify(session);
		verify(sessionFactory);
	}
	
	class SampleDao extends AbstractBasicDao<User> {

		@Override
		public Class<? extends User> getClazz() {
			return User.class;
		}
	}
	
	Session session;
	SampleDao sampleDao;
	SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		session = createMock(Session.class);
		sessionFactory = createMock(SessionFactory.class);
		sampleDao = new SampleDao();
		sampleDao.setSessionFactory(sessionFactory);
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		replay(sessionFactory);
	}
	
	public void tearDown() {
		reset(session);
		reset(sessionFactory);
	}

}
