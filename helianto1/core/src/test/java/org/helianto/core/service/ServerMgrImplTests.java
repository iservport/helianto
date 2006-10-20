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
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import org.helianto.core.Operator;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.type.OperationMode;

import junit.framework.TestCase;

public class ServerMgrImplTests extends TestCase {
    
    // class under test
    private ServerMgrImpl serverMgr;
    
    //
    
    public void testPersistOperator() {
        Operator operator = new Operator();
        operatorDao.persistOperator(operator);
        replay(operatorDao);
        
        serverMgr.persistOperator(operator);
        verify(operatorDao);
    }
    
    public void testFindOperatorAll() {
        List<Operator> operatorList = new ArrayList<Operator>();
        expect(operatorDao.findOperatorAll()).andReturn(operatorList);
        replay(operatorDao);
        
        assertSame(operatorList, serverMgr.findOperator());
        verify(operatorDao);
    }

    public void testCreateLocalDefaultOperator() {
        Operator operator = serverMgr.createLocalDefaultOperator();
        assertEquals("DEFAULT", operator.getOperatorName());
        assertEquals(OperationMode.LOCAL.getValue(), operator.getOperationMode());
        assertEquals(Locale.getDefault(), operator.getLocale());
    }

    // collabs
    
    private OperatorDao operatorDao;
    
    @Override
    public void setUp() {
        operatorDao = createMock(OperatorDao.class);
        serverMgr = new ServerMgrImpl();
        serverMgr.setOperatorDao(operatorDao);
        reset(operatorDao);
    }
    
}
