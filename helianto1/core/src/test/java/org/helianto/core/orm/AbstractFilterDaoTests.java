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
import org.helianto.core.Identity;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.IdentityFilter;
import org.helianto.core.test.UserTestSupport;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractFilterDaoTests {
	
	@Test
	public void testFilter() {
		List<Identity> resultList = new ArrayList<Identity>();
		
		Query result = createMock(Query.class);
		
		expect(session.createQuery("select identity from Identity identity " +
				"where identity.field1 = V1 " +
				"AND identity.field2 = V2 ")).andReturn(result);
		replay(session);
		
		expect(result.list()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find(filter));
		verify(session);
		verify(sessionFactory);
		verify(result);
	}
	
	@Test
	public void testFilterSelection() {
		selection = true;
		List<Identity> resultList = new ArrayList<Identity>();
		
		Query result = createMock(Query.class);
		
		expect(session.createQuery("select identity from Identity identity " +
				"where identity.field1 = V1 " +
				"AND identity.field3 = V3 ")).andReturn(result);
		replay(session);
		
		expect(result.list()).andReturn(resultList);
		replay(result);
		
		assertSame(resultList, sampleDao.find(filter));
		verify(session);
		verify(sessionFactory);
		verify(result);
	}
	
	boolean selection = false;

	class SampleDao extends AbstractFilterDao<Identity, IdentityFilter> {

		public Class<? extends Identity> getClazz() {
			return Identity.class;
		}

		@Override
		protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
			mainCriteriaBuilder.appendSegment("field1", "=").append("V1");
		}

		@Override
		protected boolean isSelection(IdentityFilter filter) {
			return selection;
		}

		@Override
		protected void doFilter(IdentityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
			mainCriteriaBuilder.appendAnd().appendSegment("field2", "=").append("V2");
		}

		@Override
		protected void doSelect(IdentityFilter filter, CriteriaBuilder mainCriteriaBuilder) {
			mainCriteriaBuilder.appendAnd().appendSegment("field3", "=").append("V3");
		}

	}
	
	Session session;
	SampleDao sampleDao;
	SessionFactory sessionFactory;
	IdentityFilter filter = IdentityFilter.filterFactory(IdentityFilter.class, UserTestSupport.createUser());
	
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
