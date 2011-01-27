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

package org.helianto.core.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Unit;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.TestingFilter;
import org.helianto.core.repository.FilterDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UnitMgrImplTests {
    
    private UnitMgrImpl unitMgr;
    
    @Test
    public void findUnits() {
    	Filter unitFilter = new TestingFilter();
    	List<Unit> unitList = new ArrayList<Unit>();
    	
    	expect(unitDao.find(unitFilter)).andReturn(unitList);
    	replay(unitDao);
    	
    	assertSame(unitList, unitMgr.findUnits(unitFilter));
    	verify(unitDao);
    }
    
    private FilterDao<Unit> unitDao;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        unitMgr = new UnitMgrImpl();
        unitDao = createMock(FilterDao.class);
        unitMgr.setUnitDao(unitDao);
    }
    
    @After
    public void tearDown() {
        reset(unitDao);
    }
    
}
