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

import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.core.test.AbstractHibernateIntegrationTest;
import org.helianto.core.test.ProvinceTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class ProvinceDaoImplTests extends AbstractHibernateIntegrationTest {

    private ProvinceDao provinceDao;
    
    public void testPersistProvince() {
        //write
        Province province = ProvinceTestSupport.createAndPersistProvince(provinceDao);
        hibernateTemplate.flush();
        //read
        assertEquals(province,  provinceDao.findProvinceByNaturalId(province.getOperator(), province.getCode()));
    }
    
    private List<Province> writeProvinceList() {
        int i = 10;
        int o = 2;
        List<Province> provinceList = ProvinceTestSupport.createAndPersistProvinceList(hibernateTemplate, i, o);
        assertEquals(i*o, provinceList.size());
        return provinceList;
    }
    
    public void testFindProvince() {
        // write list
        List<Province> provinceList = writeProvinceList();
        // read
        Province province = provinceList.get((int) Math.random()*provinceList.size());
        assertEquals(province,  provinceDao.findProvinceByNaturalId(province.getOperator(), province.getCode()));
    }

    public void testProvinceErrors() {
        try {
             provinceDao.persistProvince(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             provinceDao.removeProvince(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testProvinceDuplicate() {
        // write
        Province province = ProvinceTestSupport.createAndPersistProvince( provinceDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(province); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveProvince() {
        // write list
        List<Province> provinceList = writeProvinceList();
        // remove
        Province province = provinceList.get((int) Math.random()*provinceList.size());
        provinceDao.removeProvince(province);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Province> all = (ArrayList<Province>) hibernateTemplate.find("from Province");
        assertEquals(provinceList.size()-1, all.size());
        assertFalse(all.contains(province));
    }

    // mutators
    
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

}
