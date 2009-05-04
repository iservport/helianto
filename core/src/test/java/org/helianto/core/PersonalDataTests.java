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

package org.helianto.core;

import junit.framework.TestCase;

/**
 * <code>PersonalData</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PersonalDataTests extends TestCase {

    /**
     * Test <code>PersonalData</code> static factory method.
     */
    public void testPersonalDataFactory() {
        PersonalData personalData = 
            PersonalData.personalDataFactory("", "");
        
        assertEquals("", personalData.getFirstName());
        assertEquals("", personalData.getLastName());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                personalData.getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                personalData.getGender());
    }

}
