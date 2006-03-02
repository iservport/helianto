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

package org.helianto.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import junit.framework.TestCase;

public class PasswordTests extends TestCase {
    
    /**
     * Logger for this class
     */
    protected static final Log logger = LogFactory.getLog(PasswordTests.class);
    
    public void test() {
        CoreMgrImpl coreMgr = new CoreMgrImpl();
        String password = coreMgr.generatePassword(8);
        logger.info("Password is: "+password);
        assertEquals(8, password.length());
    }

}
