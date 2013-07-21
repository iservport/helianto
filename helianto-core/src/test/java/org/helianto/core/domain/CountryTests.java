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

import org.helianto.core.domain.Country;
import org.helianto.core.domain.Operator;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

public class CountryTests {

	@Test
    public void country() {
        Country country = new Country();
        country.setId(Integer.MAX_VALUE);
        country.setId(Integer.MIN_VALUE);

        country.setOperator(new Operator());

        country.setCountryCode("");

        country.setCountryName("");
    }

	@Test
    public void countryEquals() {
        Country copy, country = new Country();
        country.setOperator(new Operator());
        country.setCountryCode("TEST");
        copy = (Country) DomainTestSupport.minimalEqualsTest(country);

        copy.setOperator(country.getOperator());
        assertFalse(country.equals(copy));

        copy.setOperator(null);
        copy.setCountryCode("TEST");
        assertFalse(country.equals(copy));

        copy.setOperator(country.getOperator());
        copy.setCountryCode("TEST");
        assertTrue(country.equals(copy));
    }

}
