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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Unit;
import org.helianto.core.UnitFilter;
import org.helianto.core.dao.FilterDao;

public class UnitMgrImplTests extends TestCase {
    
    private UnitMgrImpl unitMgr;
    
    public void testFindUnits() {
    	UnitFilter unitFilter = new UnitFilter();
    	List<Unit> unitList = new ArrayList<Unit>();
    	
    	expect(unitDao.find(unitFilter)).andReturn(unitList);
    	replay(unitDao);
    	
    	assertSame(unitList, unitMgr.findUnits(unitFilter));
    	verify(unitDao);
    }
    
    public void testStoreCategory() {
    	Unit unit = new Unit();
    	Unit UnitCategory = new Unit();
    	
    	expect(unitDao.merge(unit)).andReturn(UnitCategory);
    	replay(unitDao);
    	
    	assertSame(UnitCategory, unitMgr.storeUnit(unit));
    	verify(unitDao);
    }
    
    private FilterDao<Unit, UnitFilter> unitDao;
    
    @SuppressWarnings("unchecked")
	@Override
    public void setUp() {
        unitMgr = new UnitMgrImpl();
        unitDao = createMock(FilterDao.class);
        unitMgr.setUnitDao(unitDao);
    }
    
    @Override
    public void tearDown() {
        reset(unitDao);
    }
    
}
