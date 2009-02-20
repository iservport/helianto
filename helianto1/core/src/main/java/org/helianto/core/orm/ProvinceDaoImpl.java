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
import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of <code>Province</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("provinceDao")
public class ProvinceDaoImpl extends GenericDaoImpl implements ProvinceDao {
    
	public void persistProvince(Province province) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+province);
        }
        persist(province);
    }
    
	public Province mergeProvince(Province province) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+province);
        }
        return (Province) merge(province);
    }
    
	public void evictProvince(Province province) {
        if (logger.isDebugEnabled()) {
            logger.debug("Evicting "+province);
        }
        evict(province);
    }
    
	public void removeProvince(Province province) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+province);
        }
        remove(province);
    }
    
	public Province findProvinceByNaturalId(Operator operator, String code) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique province with operator='"+operator+"' and provinceCode='"+code+"' ");
        }
        return (Province) findUnique(Province.getProvinceNaturalIdQueryString(), operator, code);
    }
    
	public List<Province> findProvinceByOperator(Operator operator) {
        return (ArrayList<Province>) findProvinces("province.operator.id = "+operator.getId());
	}
    
	@SuppressWarnings("unchecked")
	public List<Province> findProvinces(String criteria) {
        if (criteria!=null && !criteria.equals("")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding unit list with criteria='"+criteria+"' ");
            }
            return (ArrayList<Province>) find(Province.getProvinceQueryStringBuilder().append("where ").append(criteria));
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding full entity list");
            }
            return (ArrayList<Province>) find(Province.getProvinceQueryStringBuilder());
        }
	}
    
    
}
