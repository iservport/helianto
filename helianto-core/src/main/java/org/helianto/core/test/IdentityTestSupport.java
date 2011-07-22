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

import org.helianto.core.Identity;

/**
 * Class to support <code>IdentityDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Identity</code>.
     * @param principal optional String 
     * @param optionalAlias optional String 
     */
    public static Identity createIdentity(Object... args) {
        String principal;
        try {
            principal = (String) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            principal = DomainTestSupport.getNonRepeatableStringValue(testKey++, 64);
        }
        String optionalAlias;
        try {
            optionalAlias = (String) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            optionalAlias = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        Identity identity = new Identity(principal, optionalAlias);
        return identity;
    }

    /**
     * Test support method to create a <code>Identity</code> list.
     *
     * @param identityListSize
     */
    public static List<Identity> createIdentityList(int identityListSize) {
        return createIdentityList(identityListSize, false);
    }

    /**
     * Test support method to create a <code>Identity</code> list.
     *
     * @param identityListSize
     */
    public static List<Identity> createIdentityList(int identityListSize, boolean createId) {
        List<Identity> identityList = new ArrayList<Identity>();
            for (int i=0;i<identityListSize;i++) {
            	Identity identity = createIdentity();
            	if (createId) {
                	identity.setId(i);
            	}
                identityList.add(identity);
            }
        return identityList;
    }

}
