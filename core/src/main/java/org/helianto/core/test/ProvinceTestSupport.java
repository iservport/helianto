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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ProvinceTestSupport {

    private static int testKey = 1;

    public static Province createProvince(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = OperatorTestSupport.createOperator();
        }
        String code;
        try {
        	code = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
        	code = DomainTestSupport.getNonRepeatableStringValue(20, testKey++);
        }
        Province province = Province.provinceFactory(operator, code, DomainTestSupport.getNonRepeatableStringValue(20, testKey));
        return province;
    }

    public static List<Province> createProvinceList(int size) {
        return createProvinceList(size, 1);
    }

    public static List<Province> createProvinceList(int size, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);
        return createProvinceList(size, operatorList);
    }

    public static List<Province> createProvinceList(int size, List<Operator> operatorList) {
        List<Province> provinceList = new ArrayList<Province>();
        for (Operator x: operatorList) {
            for (int i=0;i<size;i++) {
            	Province province = createProvince(x);
                provinceList.add(province);
            }
        }
        return provinceList;
    }

    public static List<Province> createAndPersistProvinceList(HibernateTemplate hibernateTemplate, int i, int operatorListSize) {
        List<Province> provinceList = createProvinceList(i, operatorListSize);
        for (Province x: provinceList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return provinceList;
    }
    
}
