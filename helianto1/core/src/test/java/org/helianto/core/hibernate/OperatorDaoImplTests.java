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

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.test.OperatorTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class OperatorDaoImplTests extends OperatorTestSupport {

    private OperatorDao operatorDao;
    
    /*
     * Operator
     */

    public void testPersistOperator() {
        //write
        Operator operator = OperatorTestSupport.createOperator();
        operatorDao.persistOperator(operator);
        operatorDao.flush();
        //read
        assertEquals(operator,  operatorDao.findOperatorByNaturalId(operator.getOperatorName()));
    }
    
    public void testFindOperator() {
        // write list
        int i = 10;
        List<Operator> operatorList = createAndPersistOperatorList(hibernateTemplate, i);
        assertEquals(i, operatorList.size());
        // read
        Operator operator = operatorList.get((int) Math.random()*i);
        assertEquals(operator,  operatorDao.findOperatorByNaturalId(operator.getOperatorName()));
        // all
        List<Operator> all = operatorDao.findOperatorAll();
        for (Operator op: all) {
            assertTrue(operatorList.contains(op));
        }
    }

    public void testOperatorErrors() {
        try {
             operatorDao.persistOperator(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             operatorDao.removeOperator(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    /**
     * Merge and duplicate.
     */  
    public void testOperatorDuplicate() {
        Operator operator =  OperatorTestSupport.createOperator();
        operatorDao.mergeOperator(operator);
        operatorDao.flush();
        operatorDao.clear();
        Operator operatorCopy = OperatorTestSupport.createOperator(operator.getOperatorName());
        System.out.println("Original: "+operatorCopy.getOperatorName());
        System.out.println("Copy    : "+operatorCopy.getOperatorName());
        System.out.println("\n ");

        try {
            operatorDao.mergeOperator(operatorCopy);
            operatorDao.flush();
            fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testRemoveOperator() {
        // bulk write
        int i = 10;
        List<Operator> operatorList = createAndPersistOperatorList(hibernateTemplate, i);
        assertEquals(i, operatorList.size());
        // remove
        Operator operator = operatorList.get((int) Math.random()*i);
        operatorDao.removeOperator(operator);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Operator> all = (ArrayList<Operator>) hibernateTemplate.find("from Operator");
        assertEquals(i-1, all.size());
        assertFalse(all.contains(operator));
    }

    /*
     * Operator
     */

    public void testPersistKeyType() {
        //write
        KeyType keyType = createAndPersistKeyType(operatorDao);
        hibernateTemplate.flush();
        //read
        assertEquals(keyType,  operatorDao.findKeyTypeByNaturalId(keyType.getOperator(), keyType.getKeyCode()));
    }
    
    public void testFindKeyType() {
        // write list
        int i = 10;
        int o = 2;
        List<KeyType> keyTypeList = createAndPersistKeyTypeList(hibernateTemplate, i, o);
        assertEquals(i*o, keyTypeList.size());
        // read
        KeyType keyType = keyTypeList.get((int) Math.random()*i*o);
        assertEquals(keyType,  operatorDao.findKeyTypeByNaturalId(keyType.getOperator(), keyType.getKeyCode()));
    }

    public void testKeyTypeErrors() {
        try {
             operatorDao.persistKeyType(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             operatorDao.removeKeyType(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testKeyTypeDuplicate() {
        // write
        KeyType keyType = createAndPersistKeyType( operatorDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(keyType); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveKeyType() {
        // bulk write
        int i = 10;
        int o = 2;
        List<KeyType> keyTypeList = createAndPersistKeyTypeList(hibernateTemplate, i, o);
        assertEquals(i*o, keyTypeList.size());
        // remove
        KeyType keyType = keyTypeList.get((int) Math.random()*i*o);
        operatorDao.removeKeyType(keyType);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<KeyType> all = (ArrayList<KeyType>) hibernateTemplate.find("from KeyType");
        assertEquals(i*o-1, all.size());
        assertFalse(all.contains(keyType));
    }

    /*
     * Server
     */

    public void testPersistServer() {
        //write
        Server server = createAndPersistServer(operatorDao);
        hibernateTemplate.flush();
        //read
        assertEquals(server,  operatorDao.findServerByNaturalId(server.getOperator(), server.getServerName()));
    }
    
    public void testFindServer() {
        // write list
        int i = 12;
        int o = 2;
        List<Server> serverList = createAndPersistServerList(hibernateTemplate, i, o);
        assertEquals(i*o, serverList.size());
        // read
        Server server = serverList.get((int) Math.random()*serverList.size());
        List<Server> list = operatorDao.findServerActive(server.getOperator());
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
             operatorDao.persistServer(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             operatorDao.removeServer(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testServerDuplicate() {
        // write
        Server server = createAndPersistServer( operatorDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(server); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveServer() {
        // bulk write
        int i = 10;
        int o = 2;
        List<Server> serverList = createAndPersistServerList(hibernateTemplate, i, o);
        assertEquals(i*o, serverList.size());
        // remove
        Server server = serverList.get((int) Math.random()*i*o);
        operatorDao.removeServer(server);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Server> all = (ArrayList<Server>) hibernateTemplate.find("from Server");
        assertEquals(i*o-1, all.size());
        assertFalse(all.contains(server));
    }

    public void testPersistService() {
        //write
        Service service = createAndPersistService(operatorDao);
        hibernateTemplate.flush();
        //read
        assertEquals(service,  operatorDao.findServiceByNaturalId(service.getOperator(), service.getServiceName()));
    }
    
    private List<Service> writeServiceList() {
        int i = 10;
        int o = 2;
        List<Service> serviceList = createAndPersistServiceList(hibernateTemplate, i, o);
        assertEquals(i*o, serviceList.size());
        return serviceList;
    }
    
    public void testFindService() {
        // write
        List<Service> serviceList = writeServiceList();
        // read
        Service service = serviceList.get((int) Math.random()*serviceList.size());
        assertEquals(service,  operatorDao.findServiceByNaturalId(service.getOperator(), service.getServiceName()));
    }

    public void testServiceErrors() {
        try {
             operatorDao.persistService(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             operatorDao.removeService(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testServiceDuplicate() {
        // write
        Service service = createAndPersistService(operatorDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(service); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveService() {
        // write
        List<Service> serviceList = writeServiceList();
        // remove
        Service service = serviceList.get((int) Math.random()*serviceList.size());
        operatorDao.removeService(service);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Service> all = (ArrayList<Service>) hibernateTemplate.find("from Service");
        assertEquals(serviceList.size()-1, all.size());
        assertFalse(all.contains(service));
    }

    // mutators
    
    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

}
