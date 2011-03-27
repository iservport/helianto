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

import org.helianto.core.repository.base.AbstractBasicDao;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;


/**
 * Refactor this to test persistence strategy in isolation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class PersistenceStrategyTest<T, D extends AbstractBasicDao<T>> {
	
//	/**
//	 * Hook to create a test target;
//	 */
//	protected abstract T doCreateTarget();
//	
//	/**
//	 * Hook to create the expected select string;
//	 */
//	protected abstract String getSelectQueryString();
//	
//	/**
//	 * Hook to create the dao;
//	 */
//	protected abstract D doCreateDao();
//	
//	@Test
//	public void findWithClause() {
//		List<T> resultList = new ArrayList<T>();
//		
//		Query resultQuery = createMock(Query.class);
//		
//		expect(session.createQuery(getSelectQueryString()+"where CLAUSE")).andReturn(resultQuery);
//		replay(session);
//		
//		expect(resultQuery.list()).andReturn(resultList);
//		replay(resultQuery);
//		
//		assertSame(resultList, sampleDao.find("CLAUSE"));
//		verify(session);
//		verify(sessionFactory);
//		verify(resultQuery);
//	}
//	
//	@Test
//	public void findWithoutClause() {
//		List<T> resultList = new ArrayList<T>();
//		
//		Query result = createMock(Query.class);
//		
//		expect(session.createQuery(getSelectQueryString())).andReturn(result);
//		replay(session);
//		
//		expect(result.list()).andReturn(resultList);
//		replay(result);
//		
//		assertSame(resultList, sampleDao.find(""));
//		verify(session);
//		verify(sessionFactory);
//		verify(result);
//	}
//	
//	@Test
//	public void merge() {
//		T target = doCreateTarget(), managedTarget = doCreateTarget();
//		
//		expect(session.merge(target)).andReturn(managedTarget);
//		replay(session);
//		
//		assertSame(managedTarget, sampleDao.merge(target));
//		verify(session);
//		verify(sessionFactory);
//	}
//	
//	@Test
//	public void persist() {
//		T target = doCreateTarget();
//		
//		session.persist(target);
//		replay(session);
//		
//		sampleDao.persist(target);
//		verify(session);
//		verify(sessionFactory);
//	}
//	
//	@Test
//	public void evict() {
//		T target = doCreateTarget();
//		
//		session.evict(target);
//		replay(session);
//		
//		sampleDao.evict(target);
//		verify(session);
//		verify(sessionFactory);
//	}
//	
//	@Test
//	public void remove() {
//		T target = doCreateTarget();
//		
//		session.delete(target);
//		replay(session);
//		
//		sampleDao.remove(target);
//		verify(session);
//		verify(sessionFactory);
//	}
//	
//	@Test
//	public void flush() {
//		session.flush();
//		replay(session);
//		
//		sampleDao.flush();
//		verify(session);
//		verify(sessionFactory);
//	}
//	
//	@Test
//	public void clear() {
//		session.clear();
//		replay(session);
//		
//		sampleDao.clear();
//		verify(session);
//		verify(sessionFactory);
//	}
	
	Session session;
	SessionFactory sessionFactory;
	AbstractBasicDao<T> sampleDao;
	
	@Before
	public void setUp() {
		session = createMock(Session.class);
		sessionFactory = createMock(SessionFactory.class);
//		sampleDao = doCreateDao();
//		sampleDao.setSessionFactory(sessionFactory);
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		replay(sessionFactory);
	}
	
	public void tearDown() {
		reset(session);
		reset(sessionFactory);
	}

}
