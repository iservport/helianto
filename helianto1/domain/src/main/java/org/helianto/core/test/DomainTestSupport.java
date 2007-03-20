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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.TestCase;

/**
 * Classs to support domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DomainTestSupport extends TestCase {
    
    public static final String STRING_TEST_VALUE = "TEST";
    public static final long LONG_TEST_VALUE = Long.MAX_VALUE;
    public static final int INT_TEST_VALUE = Integer.MAX_VALUE;
    public static final Date DATE_TEST_VALUE = new Date(Long.MAX_VALUE);
    
    /**
     * Provide a new object and do the minimal equalty test.
     * 
     * @param objectUnderTest
     * @return an object of the same type.
     */
    public static Object minimalEqualsTest(Object objectUnderTest) {
        assertNotNull("Cant test equals() with a null object", objectUnderTest);
        if (logger.isDebugEnabled()) {
            logger.debug("Object under test is is "+objectUnderTest);
        }
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
        if (logger.isDebugEnabled()) {
            logger.debug("Copy is "+copy);
        }
        return copy;
    }
    
    /**
     * Create a non-repeatable string value
     */
    public static String getNonRepeatableStringValue(int testKey) {
        return getNonRepeatableStringValue(testKey, 20);
    }

    /**
     * Create a non-repeatable string value with a given size. 
     */
    public static String getNonRepeatableStringValue(int testKey, int size) {
        String localKey = testKey+"-"+String.valueOf(new Date().getTime());
        while (localKey.length()!=size) {
            if (localKey.length() > size) {
                localKey = localKey.substring(0, size);
            } else if (localKey.length() < size) {
                localKey = localKey.concat(localKey);
            }
        }
        return localKey;
    }

    /**
     * Create a non-repeatable int value. 
     */
    public static int getNonRepeatableIntValue(int testKey) {
        return testKey;
    }

    /**
     * Create a non-repeatable int value. 
     */
    public static long getNonRepeatableLongValue(int testKey) {
        return testKey;
    }

    /**
     * Create a non-repeatable int value. 
     */
    public static Date getNonRepeatableDateValue(int testKey) {
        return new Date(testKey);
    }

    protected static Log logger = LogFactory.getLog(DomainTestSupport.class);
    
}
