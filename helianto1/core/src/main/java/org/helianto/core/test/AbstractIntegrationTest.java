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
import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 * A base class for service layer integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractIntegrationTest extends AbstractTransactionalSpringContextTests {

    /**
     * Generate a not repeatable key.
     */
    public static String generateKey() {
        return String.valueOf(new Date().getTime());
    }

    /**
     * Generate a not repeatable key of a given size.
     */
    public static String generateKey(int size) {
        String localKey = generateKey();
        while (localKey.length()!=size) {
            if (localKey.length() > size) {
                localKey = localKey.substring(localKey.length()-size, localKey.length());
            } else if (localKey.length() < size) {
                localKey = localKey.concat(localKey);
            }
        }
        return localKey;
    }

    /**
     * Generate a not repeatable key of a given size.
     */
    public static String generateKey(int size, int index) {
        String localKey = index+"-"+generateKey();
        while (localKey.length()!=size) {
            if (localKey.length() > size) {
                localKey = localKey.substring(localKey.length()-size, localKey.length());
            } else if (localKey.length() < size) {
                localKey = localKey.concat(localKey);
            }
        }
        return localKey;
    }

    protected static Log logger = LogFactory.getLog(AbstractIntegrationTest.class);
    
}
