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

package org.helianto.core.creation;

import java.util.Date;

import org.springframework.util.Assert;

/**
 * Base class to domain object creators.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CreatorSupport {
    
    /**
     * Avoid subclasses to be directly coupled to 
     * @param object
     */
    protected static void assertNotNull(Object object) {
        Assert.notNull(object);
    }

    protected static void assertNotNull(Object object, String message) {
        Assert.notNull(object, message);
    }

    protected static Date currentDate() {
        if (currentDateNormalizer!=null) {
            return currentDateNormalizer;
        }
        return new Date();
    }
    
    static Date currentDateNormalizer = null;

}
