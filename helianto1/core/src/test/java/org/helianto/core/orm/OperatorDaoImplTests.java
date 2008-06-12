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

import org.helianto.core.Operator;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.test.OperatorTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class OperatorDaoImplTests extends AbstractHibernateIntegrationTest {

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
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(i);
        for (Operator operator: operatorList) {
            operatorDao.persistOperator(operator);
        }
        operatorDao.flush();
        operatorDao.clear();
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

    @SuppressWarnings({ "deprecation", "unchecked" })
	public void testRemoveOperator() {
        // write list
        int i = 10;
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(i);
        for (Operator operator: operatorList) {
            operatorDao.persistOperator(operator);
        }
        operatorDao.flush();
        operatorDao.clear();
        assertEquals(i, operatorList.size());
        // remove
        Operator operator = operatorList.get((int) Math.random()*i);
        operatorDao.removeOperator(operator);
        operatorDao.flush();
        operatorDao.clear();
        // read
        List<Operator> all = (ArrayList<Operator>) sessionFactory.getCurrentSession().find("from Operator");
        assertEquals(i-1, all.size());
        assertFalse(all.contains(operator));
    }

    // mutators
    
    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }

}
