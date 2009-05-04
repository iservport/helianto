package org.helianto.support.orm;

import java.util.List;

import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.support.Server;
import org.helianto.support.ServerType;
import org.helianto.support.dao.ServerDao;
import org.helianto.support.test.ServerTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>ServerDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class ServerDaoImplTests extends AbstractIntegrationTest {

    private ServerDao serverDao;
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/core.xml",
                "deploy/org.helianto.core.xml",
                "deploy/org.helianto.support.xml"
                };
    }
    
    /*
     * Hook to persist one <code>Server</code>.
     */  
    protected Server writeServer() {
        Server server = ServerTestSupport.createServer();
        serverDao.persistServer(server);
        serverDao.flush();
        serverDao.clear();
        return server;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneServer() {
        Server server = writeServer();

        assertEquals(server,  serverDao.findServerByNaturalId(server.getOperator(), server.getServerName()));
    }
    
    /*
     * Hook to persist a <code>Server</code> list.
     */  
    protected List<Server> writeServerList() {
        int serverListSize = 10;
        int operatorListSize = 2;
        List<Server> serverList = ServerTestSupport.createServerList(serverListSize, operatorListSize);
        assertEquals(serverListSize * operatorListSize, serverList.size());
        for (Server server: serverList) {
            serverDao.persistServer(server);
        }
        serverDao.flush();
        serverDao.clear();
        return serverList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListServer() {
        List<Server> serverList = writeServerList();

        Server server = serverList.get((int) (Math.random()*serverList.size()));
        assertEquals(server,  serverDao.findServerByNaturalId(server.getOperator(), server.getServerName()));
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
    
    /**
     * Remove.
     */  
    public void testRemoveServer() {
        List<Server> serverList = writeServerList();
        Server server = serverList.get((int) (Math.random()*serverList.size()));
        serverDao.removeServer(server);

        assertNull(serverDao.findServerByNaturalId(server.getOperator(), server.getServerName()));
    }
    
    //- additional
    
    public void testFindServerByOperatorAndType() {
        List<Server> serverList = writeServerList();

        for (Server s: serverList) {
            s.setServerType(ServerType.HTTP_SERVER.getValue());
            serverDao.mergeServer(s);
        }
        Server server = serverList.get((int) (Math.random()*serverList.size()));
        server.setServerType(ServerType.POP3_SERVER.getValue());
        serverDao.mergeServer(server);
        serverDao.flush();
        List<Server> resultList = serverDao.findServerByOperatorAndType(server.getOperator(), ServerType.POP3_SERVER);
        assertEquals(server,  resultList.iterator().next());
        assertEquals(1, resultList.size());
    }

    public void testFindServerByOperatorAndTypeOrder() {
        List<Server> serverList = writeServerList();

        int i = 0;
        for (Server s: serverList) {
            s.setPriority(i++);
            serverDao.mergeServer(s);
        }
        serverDao.flush();
        Server server = serverList.get((int) (Math.random()*serverList.size()));
        i = -1;
        List<Server> resultList = serverDao.findServerByOperatorAndType(server.getOperator(), ServerType.SMTP_SERVER);
        for (Server s: resultList) {
            assertEquals(server.getOperator(),  s.getOperator());
            assertEquals(ServerType.SMTP_SERVER.getValue(),  s.getServerType());
            assertTrue(s.getPriority() > i);
            i = s.getPriority();
        }
        assertEquals(1, resultList.size());
    }

    //- setters

    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }
    
}

