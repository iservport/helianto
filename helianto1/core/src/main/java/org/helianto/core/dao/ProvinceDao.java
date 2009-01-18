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

package org.helianto.core.dao;

import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.Province;

/**
 * <code>Province</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ProvinceDao extends CommonOrmDao {
    
    /**
     * Persist <code>Province</code>.
     */
    public void persistProvince(Province province);
    
    /**
     * Merge <code>Province</code>.
     */
    public Province mergeProvince(Province province);
    
    /**
     * Remove <code>Province</code> from session.
     */
	public void evictProvince(Province province);
	
    /**
     * Remove <code>Province</code> from data store.
     */
    public void removeProvince(Province province);
    
    /**
     * Find <code>Province</code> by  operator and code.
     */
    public Province findProvinceByNaturalId(Operator operator, String code);
    
    /**
     * Find <code>Province</code> by  operator.
     */
    public List<Province> findProvinceByOperator(Operator operator);
    
    /**
     * Find <code>Province</code>s by criteria.
     */
    public List<Province> findProvinces(String criteria);
    
}
