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

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.junit.AbstractCredentialTest;
import org.springframework.dao.DataIntegrityViolationException;

public class CredentialDaoImplTests extends AbstractCredentialTest {
    
    private CredentialDao credentialDao;
    
    /*
     * Identity tests 
     */
    
    public void testPersistIdentity() {
        //write
        Identity identity = createAndPersistIdentity(credentialDao);
        hibernateTemplate.flush();
        //read
        assertEquals(identity, credentialDao.findIdentityByPrincipal(identity.getPrincipal()));
    }
    
    public void testFindIdentity() {
        // write list
        int i = 10;
        List<Identity> identityList = createAndPersistIdentityList(hibernateTemplate, i);
        assertEquals(i, identityList.size());
        // read
        Identity identity = identityList.get((int) Math.random()*i);
        assertEquals(identity, credentialDao.findIdentityByPrincipal(identity.getPrincipal()));
    }

    public void testIdentityErrors() {
        try {
            credentialDao.persistIdentity(null); fail();
        } catch (IllegalArgumentException e) { }
    }

    public void testIdentityDuplicate() {
        // write
        Identity identity = createAndPersistIdentity(credentialDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(identity); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveIdentity() {
        // bulk write
        int i = 10;
        List<Identity> identityList = createAndPersistIdentityList(hibernateTemplate, i);
        assertEquals(i, identityList.size());
        // remove
        Identity identity = identityList.get((int) Math.random()*i);
        credentialDao.removeIdentity(identity);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Identity> all = (ArrayList<Identity>) hibernateTemplate.find("from Identity");
        assertEquals(i-1, all.size());
        assertFalse(all.contains(identity));
    }

    /*
     * Credential tests 
     */
    
    public void testPersistCredential() {
        //write
        Credential credential = createAndPersistCredential(credentialDao);
        hibernateTemplate.flush();
        //read
        assertEquals(credential,  credentialDao.findCredentialByIdentity(credential.getIdentity()));
    }
    
    public void testFindCredential() {
        // write list
        int i = 10;
        List<Credential> credentialList = createAndPersistCredentialList(hibernateTemplate, i);
        assertEquals(i, credentialList.size());
        // read
        Credential credential = credentialList.get((int) Math.random()*i);
        assertEquals(credential,  credentialDao.findCredentialByIdentity(credential.getIdentity()));
    }

    public void testCredentialErrors() {
        try {
            credentialDao.persistCredential(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           credentialDao.findCredentialByIdentity(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testCredentialDuplicate() {
        // write
        Credential credential = createAndPersistCredential( credentialDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(credential); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveCredential() {
        // bulk write
        int i = 10;
        List<Credential> credentialList = createAndPersistCredentialList(hibernateTemplate, i);
        assertEquals(i, credentialList.size());
        // remove
        Credential credential = credentialList.get((int) Math.random()*i);
        credentialDao.removeCredential(credential);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<Credential> all = (ArrayList<Credential>) hibernateTemplate.find("from Credential");
        assertEquals(i-1, all.size());
        assertFalse(all.contains(credential));
    }
    //~ collaborator mutators
    
    public void setCredentialDao(CredentialDao credentialDao) {
        this.credentialDao = credentialDao;
    }

}
