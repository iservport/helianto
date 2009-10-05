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

import org.helianto.core.repository.AbstractBasicDao;
import org.helianto.core.repository.PersistenceStrategy;
import org.junit.Before;
import org.junit.Test;


/**
 * Base class to BasicDao tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("unchecked")
public abstract class AbstractHibernateBasicDaoTest<T, D extends AbstractBasicDao<T>> {
	
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
	public void findWithClause() {
		List<T> resultList = new ArrayList<T>();
		
		expect(persistenceStrategy.find(getSelectQueryString()+"where CLAUSE")).andReturn(resultList);
		replay(persistenceStrategy);
		
		assertSame(resultList, sampleDao.find("CLAUSE"));
		verify(persistenceStrategy);
	}
	
	@Test
	public void findWithoutClause() {
		List<T> resultList = new ArrayList<T>();
		
		expect(persistenceStrategy.find(getSelectQueryString())).andReturn(resultList);
		replay(persistenceStrategy);
		
		assertSame(resultList, sampleDao.find(""));
		verify(persistenceStrategy);
	}
	
	@Test
	public void merge() {
		T target = doCreateTarget(), managedTarget = doCreateTarget();
		
		expect(persistenceStrategy.merge(target)).andReturn(managedTarget);
		replay(persistenceStrategy);
		
		assertSame(managedTarget, sampleDao.merge(target));
		verify(persistenceStrategy);
	}
	
	@Test
	public void persist() {
		T target = doCreateTarget();
		
		persistenceStrategy.persist(target);
		replay(persistenceStrategy);
		
		sampleDao.persist(target);
		verify(persistenceStrategy);
	}
	
	@Test
	public void evict() {
		T target = doCreateTarget();
		
		persistenceStrategy.evict(target);
		replay(persistenceStrategy);
		
		sampleDao.evict(target);
		verify(persistenceStrategy);
	}
	
	@Test
	public void remove() {
		T target = doCreateTarget();
		
		persistenceStrategy.remove(target);
		replay(persistenceStrategy);
		
		sampleDao.remove(target);
		verify(persistenceStrategy);
	}
	
	@Test
	public void flush() {
		persistenceStrategy.flush();
		replay(persistenceStrategy);
		
		sampleDao.flush();
		verify(persistenceStrategy);
	}
	
	@Test
	public void clear() {
		persistenceStrategy.clear();
		replay(persistenceStrategy);
		
		sampleDao.clear();
		verify(persistenceStrategy);
	}
	
	PersistenceStrategy persistenceStrategy;
	AbstractBasicDao<T> sampleDao;
	
	@Before
	public void setUp() {
		persistenceStrategy = createMock(PersistenceStrategy.class);
		sampleDao = doCreateDao();
		sampleDao.setPersistenceStrategy(persistenceStrategy);
	}
	
	public void tearDown() {
		reset(persistenceStrategy);
	}

}
