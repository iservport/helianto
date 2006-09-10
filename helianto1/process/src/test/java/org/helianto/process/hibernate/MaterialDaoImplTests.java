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

package org.helianto.process.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.process.Unit;
import org.helianto.process.dao.MaterialDao;
import org.helianto.process.junit.AbstractMaterialTest;
import org.springframework.dao.DataIntegrityViolationException;

public class MaterialDaoImplTests extends AbstractMaterialTest {

    // class (interface) under test
    private MaterialDao materialDao;

    public void testPersistUnit() {
        //write
        Unit unit = createAndPersistUnit(materialDao);
        hibernateTemplate.flush();
        //read
        assertEquals(unit,  materialDao.findUnitByNaturalId(unit.getEntity(), unit.getUnitCode()));
    }
    
    public void testFindUnit() {
        // write list
        int i = 10;
        int e = 2;
        List<Unit> unitList = createAndPersistUnitList(hibernateTemplate, i, e);
        assertEquals(i*e, unitList.size());
        // read
        Unit unit = unitList.get((int) Math.random()*i);
        assertEquals(unit,  materialDao.findUnitByNaturalId(unit.getEntity(), unit.getUnitCode()));
        // TODO add some more finders
    }

    public void testUnitErrors() {
        try {
            materialDao.persistUnit(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           materialDao.removeUnit(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
        // TODO add some more methods
    }

    public void testUnitDuplicate() {
        // write
        Unit unit = createAndPersistUnit( materialDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(unit); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveUnit() {
        // bulk write
        int i = 10;
        int e = 2;
        List<Unit> unitList = createAndPersistUnitList(hibernateTemplate, i, e);
        assertEquals(i*e, unitList.size());
        // remove
        Unit unit = unitList.get((int) Math.random()*i);
        materialDao.removeUnit(unit);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Unit> all = (ArrayList<Unit>) hibernateTemplate.find("from Unit");
        assertEquals(i*e-1, all.size());
        assertFalse(all.contains(unit));
    }
    
    //

    public void setMaterialDao(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

}
