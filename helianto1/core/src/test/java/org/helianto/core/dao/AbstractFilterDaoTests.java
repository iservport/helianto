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


package org.helianto.core.dao;

import static org.junit.Assert.assertEquals;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractFilterDaoTests {
	
	@Test
	public void testCreateBuilder() {
		CriteriaBuilder criteriaBuilder = filterDao.createCriteriaBuilder();
		assertEquals("OBJECT_ALIAS", criteriaBuilder.getPrefix());
	}
	
	@Test(expected=Exception.class)
	public void testCreateCriteriaAsStringException() {
		FilterStub filter = new FilterStub();
		filterDao.createCriteriaAsString(filter);
	}
	
	@Test
	public void testCreateCriteriaAsString() {
		FilterStub filter = new FilterStub();
		
		String criteria = filterDao.createCriteriaAsString(filter, false);
		assertEquals("", criteria);
	}
	
	AbstractFilterDao<TargetStub, FilterStub> filterDao;
	String key = "";
		
	@Before
	public void setUp() {
		filterDao = new FilterDaoStub();
	}

	/** Target stub */
	class TargetStub {}
	
	/** Filter stub */
	class FilterStub extends AbstractUserBackedCriteriaFilter {
		private static final long serialVersionUID = 1L;
		public void reset() {
		}
	}
	
	/** Dao stub */
	class FilterDaoStub extends AbstractFilterDao<TargetStub, FilterStub> {
		@Override
		protected void preProcessFilter(FilterStub filter, CriteriaBuilder mainCriteriaBuilder) { }
		@Override
		protected void doFilter(FilterStub filter, CriteriaBuilder mainCriteriaBuilder) {}
		@Override
		protected void doSelect(FilterStub filter, CriteriaBuilder mainCriteriaBuilder) {}
		@Override
		protected boolean isSelection(FilterStub filter) { return false; }
		@Override
		protected void postProcessFilter(FilterStub filter, CriteriaBuilder mainCriteriaBuilder) {}
		@Override
		public Class<? extends TargetStub> getClazz() { return TargetStub.class; }
		@Override
		public String getObjectAlias() { return "OBJECT_ALIAS"; }
	}
	
}
