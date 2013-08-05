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

package org.helianto.core.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

public class ProvinceTests {

	@Test
    public void province() {
        Province province = new Province();
        province.setId(Integer.MAX_VALUE);
        province.setId(Integer.MIN_VALUE);

        province.setOperator(new Operator());
        province.setProvinceCode("");
        province.setProvinceName("");
        province.setCountry(new Country());
    }

	@Test
    public void provinceEquals() {
        Province copy, province = new Province();
        province.setOperator(new Operator());
        province.setProvinceCode("TEST");
        copy = (Province) DomainTestSupport.minimalEqualsTest(province);

        copy.setOperator(province.getOperator());
        assertFalse(province.equals(copy));

        copy.setOperator(null);
        copy.setProvinceCode("TEST");
        assertFalse(province.equals(copy));

        copy.setOperator(province.getOperator());
        copy.setProvinceCode("TEST");
        assertTrue(province.equals(copy));
    }

}
