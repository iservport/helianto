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
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.helianto.core.dao.AbstractJpaBasicDao;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;


/**
 * Base class to BasicDao tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractJpaBasicDaoTest<T, D extends AbstractJpaBasicDao<T>> {
	
	/**
	 * Hook to create a test target;
	 */
	protected abstract T doCreateTarget();
	
	/**
	 * Hook to create the expected select string;
	 */
	protected abstract String getSelectQueryString();
	
	/**
	 * Hook to create the dao;
	 */
	protected abstract D doCreateDao();
	
	@Test
	public void testFindWithClause() {
		List<T> resultList = new ArrayList<T>();
		
		Query resultQuery = createMock(Query.class);
		
		expect(em.createQuery(getSelectQueryString()+"where CLAUSE")).andReturn(resultQuery);
		replay(em);
		
		expect(resultQuery.getResultList()).andReturn(resultList);
		replay(resultQuery);
		
		assertSame(resultList, sampleDao.find("CLAUSE"));
		verify(em);
		verify(resultQuery);
	}
	
	@Test
	public void testFindWithoutClause() {
		List<T> resultList = new ArrayList<T>();
		
		Query result = createMock(Query.class);
		
		expect(em.createQuery(getSelectQueryString())).andReturn(result);
		replay(em);
		
		expect(result.getResultList()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find(""));
		verify(em);
		verify(result);
	}
	
	@Test
	public void testMerge() {
		T target = doCreateTarget(), managedTarget = doCreateTarget();
		
		expect(em.merge(target)).andReturn(managedTarget);
		replay(em);
		
		assertSame(managedTarget, sampleDao.merge(target));
		verify(em);
	}
	
	@Test
	public void testPersist() {
		T target = doCreateTarget();
		
		em.persist(target);
		replay(em);
		
		sampleDao.persist(target);
		verify(em);
	}
	
//	@Test
//	public void testEvict() {
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
	@Test
	public void testRemove() {
		T target = doCreateTarget();
		
		em.remove(target);
		replay(em);
		
		sampleDao.remove(target);
		verify(em);
	}
	
	@Test
	public void testFlush() {
		em.flush();
		replay(em);
		
		sampleDao.flush();
		verify(em);
	}
	
	@Test
	public void testClear() {
		em.clear();
		replay(em);
		
		sampleDao.clear();
		verify(em);
	}
	
	AbstractJpaBasicDao<T> sampleDao;
	EntityManager em;
	
	@Before
	public void setUp() {
		em = createMock(EntityManager.class);
		sampleDao = doCreateDao();
		sampleDao.setEntityManager(em);
	}
	
	public void tearDown() {
		reset(em);
	}

}
