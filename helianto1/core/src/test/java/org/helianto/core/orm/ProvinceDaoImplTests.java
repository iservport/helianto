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

import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.core.test.ProvinceTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class ProvinceDaoImplTests extends AbstractHibernateIntegrationTest {

    private ProvinceDao provinceDao;
    
    /*
     * Hook to persist one <code>Province</code>.
     */  
    protected Province writeProvince(Province province) {
    	provinceDao.persistProvince(province);
    	provinceDao.flush();
    	provinceDao.clear();
        return province;
    }
    
    protected Province writeProvince() {
    	Province province = ProvinceTestSupport.createProvince();
    	writeProvince(province);
        return province;
    }
    

    public void testPersistProvince() {
        //write
        Province province = writeProvince();
        //read
        assertEquals(province,  provinceDao.findProvinceByNaturalId(province.getOperator(), province.getCode()));
    }
    
    private List<Province> writeProvinceList() {
        int i = 10;
        int o = 2;
        List<Province> provinceList = ProvinceTestSupport.createProvinceList(i, o);
        for (Province province: provinceList) {
        	provinceDao.persistProvince(province);
        }
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

    /**
     * Merge and duplicate.
     */  
    public void testProvinceDuplicate() {
    	Province province = writeProvince();
    	Province provinceCopy = ProvinceTestSupport.createProvince(province.getOperator(), province.getCode());

        try {
        	provinceDao.mergeProvince(provinceCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    @SuppressWarnings({ "unchecked", "deprecation" })
	public void testRemoveProvince() {
        // write list
        List<Province> provinceList = writeProvinceList();
        // remove
        Province province = provinceList.get((int) Math.random()*provinceList.size());
        provinceDao.removeProvince(province);
        provinceDao.flush();
        provinceDao.clear();
        // read
        List<Province> all = (ArrayList<Province>) sessionFactory.getCurrentSession().find("select province from Province province ");
        assertEquals(provinceList.size()-1, all.size());
        assertFalse(all.contains(province));
    }

    // mutators
    
    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

}
