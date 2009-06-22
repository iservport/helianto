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

import javax.persistence.EntityManager;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.dao.AbstractJpaBasicDao;
import javax.persistence.Query;
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
		
		expect(em.createQuery("select user from User user where user.entity = ? AND user.internalNumber = ? ")).andReturn(result);
		replay(em);
		
		expect(result.setParameter(0, entity)).andReturn(result);
		expect(result.setParameter(1, internalNumber)).andReturn(result);
		expect(result.getResultList()).andReturn(resultList);
		replay(result);
		
		assertSame(resultUser, sampleDao.findUnique(entity, internalNumber));
		verify(em);
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
		sampleDao.setEntityManager(em);

		List<User> resultList = new ArrayList<User>();
		User resultUser = new User();
		resultList.add(resultUser);
		
		Query result = createMock(Query.class);
		
		expect(em.createQuery("select user from User user where user.KEY1 = ? AND user.KEY2 = ? ")).andReturn(result);
		replay(em);
		
		expect(result.setParameter(0, "VALUE1")).andReturn(result);
		expect(result.setParameter(1, "VALUE2")).andReturn(result);
		expect(result.getResultList()).andReturn(resultList);
		replay(result);
		
		assertSame(resultUser, sampleDao.findUnique("VALUE1", "VALUE2"));
		verify(em);
		verify(result);
	}
	
	@Test
	public void testFindWithClause() {
		List<User> resultList = new ArrayList<User>();
		
		Query result = createMock(Query.class);
		
		expect(em.createQuery("select user from User user where CLAUSE")).andReturn(result);
		replay(em);
		
		expect(result.getResultList()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find("CLAUSE"));
		verify(em);
		verify(result);
	}
	
	@Test
	public void testFindWithoutClause() {
		List<User> resultList = new ArrayList<User>();
		
		Query result = createMock(Query.class);
		
		expect(em.createQuery("select user from User user ")).andReturn(result);
		replay(em);
		
		expect(result.getResultList()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find(""));
		verify(em);
		verify(result);
	}
	
	@Test
	public void testMerge() {
		User user = new User(), managedUser = new User();
		
		expect(em.merge(user)).andReturn(managedUser);
		replay(em);
		
		assertSame(managedUser, sampleDao.merge(user));
		verify(em);
	}
	
	@Test
	public void testPersist() {
		User user = new User();
		
		em.persist(user);
		replay(em);
		
		sampleDao.persist(user);
		verify(em);
	}
	
//	@Test
//	public void testEvict() {
//		User user = new User();
//		
//		session.evict(user);
//		replay(em);
//		
//		sampleDao.evict(user);
//		verify(em);
//	}
	
	@Test
	public void testRemove() {
		User user = new User();
		
		em.remove(user);
		replay(em);
		
		sampleDao.remove(user);
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
	
	class SampleDao extends AbstractJpaBasicDao<User> {

		@Override
		public Class<? extends User> getClazz() {
			return User.class;
		}
	}
	
	SampleDao sampleDao;
	EntityManager em;
	
	@Before
	public void setUp() {
		em = createMock(EntityManager.class);
		sampleDao = new SampleDao();
		sampleDao.setEntityManager(em);
	}
	
	public void tearDown() {
		reset(em);
	}

}
