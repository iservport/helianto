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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.Server;
import org.helianto.core.dao.ServerDao;
import org.helianto.core.test.ServerTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class ServerDaoImplTests extends AbstractHibernateIntegrationTest {

    private ServerDao serverDao;
    
    /*
     * Hook to persist one <code>Server</code>.
     */  
    protected Server writeServer(Server server) {
    	serverDao.persistServer(server);
    	serverDao.flush();
    	serverDao.clear();
        return server;
    }
    
    protected Server writeServer() {
    	Server server = ServerTestSupport.createServer();
    	writeServer(server);
        return server;
    }
    

    public void testPersistServer() {
        //write
        Server server = ServerTestSupport.createServer();
        serverDao.persistServer(server);
        serverDao.flush();
        //read
        assertEquals(server,  serverDao.findServerByNaturalId(server.getOperator(), server.getServerName()));
    }
    
    public void testFindServer() {
        // write list
        int i = 12;
        int o = 2;
        List<Server> serverList = ServerTestSupport.createServerList(i, o);
        for (Server server: serverList) {
        	serverDao.persistServer(server);
        }
        serverDao.flush();
        assertEquals(i*o, serverList.size());
        // read
        Server server = serverList.get((int) Math.random()*serverList.size());
        List<Server> list = serverDao.findServerActive(server.getOperator());
        byte priority = 0;
        for (Server s: list) {
            assertEquals(server.getOperator(), s.getOperator());
            assertEquals(ActivityState.ACTIVE.getValue(), s.getServerState());
            assertTrue(s.getPriority()>=priority);
            priority = s.getPriority();
        }
    }

    public void testServerErrors() {
        try {
             serverDao.persistServer(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             serverDao.removeServer(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    /**
     * Merge and duplicate.
     */  
    public void testServerDuplicate() {
    	Server server =  writeServer();
    	Server serverCopy = ServerTestSupport.createServer(server.getOperator(), server.getServerName());

        try {
        	serverDao.mergeServer(serverCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	public void testRemoveServer() {
        // bulk write
        int i = 10;
        int o = 2;
        List<Server> serverList = ServerTestSupport.createServerList(i, o);
        for (Server server: serverList) {
        	serverDao.persistServer(server);
        }
        serverDao.flush();
        assertEquals(i*o, serverList.size());
        // remove
        Server server = serverList.get((int) Math.random()*i*o);
        serverDao.removeServer(server);
        serverDao.flush();
        serverDao.clear();
        // read
        List<Server> all = (ArrayList<Server>) sessionFactory.getCurrentSession().find("from Server");
        assertEquals(i*o-1, all.size());
        assertFalse(all.contains(server));
    }

    // mutators
    
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

}
