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
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.filter.classic.OperatorFilter;
import org.helianto.core.repository.FilterDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorMgrImplTests {
    
    private NamespaceMgrImpl operatorMgr;
    
    @Test
    public void persistOperator() {
        Operator operator = new Operator(), managedOperator = new Operator();
        expect(operatorDao.merge(operator)).andReturn(managedOperator);
        replay(operatorDao);
        
        assertSame(managedOperator, operatorMgr.storeOperator(operator));
        verify(operatorDao);
    }
    
    @Test
    public void findOperatorAll() {
        List<Operator> operatorList = new ArrayList<Operator>();
        Operator operator = new Operator();
        operatorList.add(operator);
        
        expect(operatorDao.find(isA(OperatorFilter.class))).andReturn(operatorList);
        replay(operatorDao);
        
        assertSame(operatorList, operatorMgr.findOperator());
        verify(operatorDao);
    }

    @Test
    public void findOperatorByName() {
        Operator operator = new Operator();
        
        expect(operatorDao.findUnique("TEST")).andReturn(operator);
        replay(operatorDao);
        
        assertSame(operator, operatorMgr.findOperatorByName("TEST"));
        verify(operatorDao);
    }

    // collabs
    
    private FilterDao<Operator, OperatorFilter> operatorDao;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        operatorDao = createMock(FilterDao.class);
        operatorMgr = new NamespaceMgrImpl();
        operatorMgr.setOperatorDao(operatorDao);
    }
    
    @After
    public void tearDown() {
        reset(operatorDao);
    }
    
}
