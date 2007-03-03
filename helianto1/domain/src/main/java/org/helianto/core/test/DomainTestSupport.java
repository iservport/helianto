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

import java.util.Date;

import junit.framework.TestCase;

public class DomainTestSupport extends TestCase {
    
    public static final String STRING_TEST_VALUE = "TEST";
    public static final long LONG_TEST_VALUE = Long.MAX_VALUE;
    public static final int INT_TEST_VALUE = Integer.MAX_VALUE;
    public static final Date DATE_TEST_VALUE = new Date(Long.MAX_VALUE);
    
    public static Object minimalEqualsTest(Object objectUnderTest) {
        assertNotNull("Cant test equals() with a null object", objectUnderTest);
        assertTrue(objectUnderTest.equals(objectUnderTest));
        assertFalse(objectUnderTest.equals(null));
        assertFalse(objectUnderTest.equals(new Object()));
        Object copy = null;
        try {
            copy = objectUnderTest.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(copy);
        assertFalse(objectUnderTest.equals(copy));
        return copy;
    }

}
