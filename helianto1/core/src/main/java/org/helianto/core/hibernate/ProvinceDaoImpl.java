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

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;

public class ProvinceDaoImpl extends GenericDaoImpl implements ProvinceDao {
    
    public void persistProvince(Province province) {
        persist(province);
    }
    
    public Province mergeProvince(Province province) {
        return (Province) merge(province);
    }
    
    public void removeProvince(Province province) {
        remove(province);
    }
    
    public Province findProvinceByNaturalId(Operator operator, String code) {
        return (Province) findUnique(PROVINCE_QRY, operator, code);
    }
    
    static String PROVINCE_QRY = "from Province province "+
        "where province.operator = ? and province.code = ? ";

	public List<Province> findProvinceByOperator(Operator operator) {
        return (ArrayList<Province>) find(PROVINCE_OPERATOR_QRY, operator);
	}
    
    static String PROVINCE_OPERATOR_QRY = "from Province province "+
        "where province.operator = ? ";

}
