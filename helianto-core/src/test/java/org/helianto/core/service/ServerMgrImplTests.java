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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.filter.classic.ServerFilter;
import org.helianto.core.repository.FilterDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ServerMgrImplTests {
    
	@Test
    public void findActiveServerNull() {
    	Operator operator = new Operator();
    	List<Server> serverList = new ArrayList<Server>();
    	
    	expect(serverDao.find(isA(ServerFilter.class))).andReturn(serverList);
    	replay(serverDao);
    	
    	assertNull(serverMgr.findActiveServer(operator, 0));
    	verify(serverDao);
    }
    
	@Test
    public void findActiveServer() {
    	Operator operator = new Operator();
    	List<Server> serverList = new ArrayList<Server>();
    	Server server = new Server();
    	serverList.add(server);
    	
    	expect(serverDao.find(isA(ServerFilter.class))).andReturn(serverList);
    	replay(serverDao);
    	
    	assertSame(serverList.get(0), serverMgr.findActiveServer(operator, 0));
    	verify(serverDao);
    }
    
    private ServerMgrImpl serverMgr;
    private FilterDao<Server> serverDao;
    
    @SuppressWarnings("unchecked")
	@Before
    public void setUp() {
        serverMgr = new ServerMgrImpl();
        serverDao = createMock(FilterDao.class);
        serverMgr.setServerDao(serverDao);
    }
    
    @After
    public void tearDown() {
        reset(serverDao);
    }
    
}
