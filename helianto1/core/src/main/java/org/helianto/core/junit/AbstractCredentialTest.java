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

package org.helianto.core.junit;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.creation.AuthenticationCreator;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.test.EntityTestSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AbstractCredentialTest extends EntityTestSupport {

    public static Identity createAndPersistIdentity(AuthenticationDao credentialDao) {
        String principal = generateKey(20);
        String optionalAlias = generateKey(20);
        Identity identity = AuthenticationCreator.identityFactory(principal, optionalAlias);
        if (credentialDao!=null) {
            credentialDao.persistIdentity(identity);
        }
        return identity;
    }

    public static List<Identity> createAndPersistIdentityList(HibernateTemplate hibernateTemplate, int i) {
        List<Identity> identityList = createIdentities(i);
        hibernateTemplate.saveOrUpdateAll(identityList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return identityList;
    }
    
    public static List<Identity> createIdentities(int size) {
        List<Identity> identities = new ArrayList<Identity>();
        for (int i=0;i<size;i++) {
            identities.add(AuthenticationCreator.identityFactory("PRINCIPAL"+i, "ALIAS"+i));
        }
        return identities ;
    }

    public static Credential createAndPersistCredential(AuthenticationDao credentialDao) {
        Identity identity = createAndPersistIdentity(credentialDao);
        Credential credential = AuthenticationCreator.credentialFactory(identity, "");
        if (credentialDao!=null) {
            credentialDao.persistCredential(credential);
        }
        return credential;
    }

    public static List<Credential> createAndPersistCredentialList(HibernateTemplate hibernateTemplate, int i) {
        List<Credential> credentialList = createCredentialList(i);
        hibernateTemplate.saveOrUpdateAll(credentialList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return credentialList;
    }
    
    public static List<Credential> createCredentialList(int size) {
        List<Identity> identities = createIdentities(size);
        List<Credential> credentialList = new ArrayList<Credential>();
        for (Identity i: identities) {
            credentialList.add(AuthenticationCreator.credentialFactory(i, ""));
        }
        return credentialList ;
    }

}
