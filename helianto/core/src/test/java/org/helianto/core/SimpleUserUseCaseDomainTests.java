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


import java.util.Date;
import junit.framework.TestCase;

public class SimpleUserUseCaseDomainTests extends TestCase {
    
    PersonalData personalData;
    Credential credential;
    MailTransportData mtd;
    MailAccessData mad;
    Home home;
    Entity entity;
    AddressableEntity addrent;
    Individual individual;
    Organization organization;
    DefaultEntity defaultEntity;
    User user;
    Province province;
    
    public void testGraph() {
        
        // personal data
        personalData = new PersonalData();
        personalData.setFirstName("");
        personalData.setLastName("");
        personalData.setAppellation(Appellation.MISS.getValue());
        personalData.setAppellation(Appellation.MR_OR_MRS.getValue());
        personalData.setAppellation(Appellation.MS.getValue());
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.FEMALE.getValue());
        personalData.setGender(Gender.MALE.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
        
        // credential
        credential = new Credential();
        credential.setId(Long.MAX_VALUE);
        credential.setId(Long.MIN_VALUE);
        credential.setPrincipal("");
        credential.setPassword("");
        credential.setCredentialType(CredentialType.NOT_ADDRESSABLE.getValue());
        credential.setCredentialType(CredentialType.ORGANIZATIONAL_EMAIL.getValue());
        credential.setCredentialType(CredentialType.PERSONAL_EMAIL.getValue());
        credential.setNotification(Notification.AUTOMATIC.getValue());
        credential.setNotification(Notification.BY_REQUEST.getValue());
        credential.setPersonalData(null);
        credential.setPersonalData(personalData);
        credential.setCreated(new Date());
        credential.setLastModified(new Date());
        credential.setExpired(new Date());
        credential.setCredentialState(CredentialState.ACTIVE.getValue());
        credential.setCredentialState(CredentialState.CANCELLED.getValue());
        credential.setCredentialState(CredentialState.IDLE.getValue());
        credential.setCredentialState(CredentialState.SUSPENDED.getValue());
        credential.getUsers().add(new User());
        
        assertTrue(credential.equals(credential));
        assertFalse(credential.equals(null));
        assertFalse(credential.equals(new Object()));
        Credential c = new Credential();
        assertFalse(credential.equals(c));
        credential.setPrincipal(null);
        assertTrue(credential.equals(c));
        credential.setPrincipal("UNIQUE");
        c.setPrincipal("");
        assertFalse(credential.equals(c));
        c.setPrincipal("UNIQUE");
        assertTrue(credential.equals(c));
        
        // mail transport data
        mtd = new MailTransportData();
        mtd.setSmtpHost("");
        mtd.setSmtpPassword("");
        mtd.setSmtpUser("");
        
        // mail access data
        mad = new MailAccessData();
        mad.setStoreType("");
        mad.setHost("");
        mad.setPassword("");
        mad.setUser("");
        
        // home
        home = new Home();
        home.setId(Integer.MAX_VALUE);
        home.setId(Integer.MIN_VALUE);
        home.setParent(null);
        home.setParent(new Home());
        home.setHomeName("");
        home.setHttpAddress("");
        home.setHomeDesc("");
        home.setAdded(new Date());
        home.setLanguage("");
        home.setCountry("");
        home.setMailTransportData(mtd);
        home.setMailAccessData(mad);
        
        assertTrue(home.equals(home));
        assertFalse(home.equals(null));
        assertFalse(home.equals(new Object()));
        Home h = new Home();
        assertFalse(home.equals(h));
        home.setHomeName(null);
        assertTrue(home.equals(h));
        home.setHomeName("UNIQUE");
        h.setHomeName("");
        assertFalse(home.equals(h));
        h.setHomeName("UNIQUE");
        assertTrue(home.equals(h));
        
        // entity
        entity = new Entity();
        entity.setId(Long.MAX_VALUE);
        entity.setId(Long.MIN_VALUE);
        entity.setAlias("");
        entity.setHome(null);
        entity.setHome(home);
        
        assertTrue(entity.equals(entity));
        assertFalse(entity.equals(null));
        assertFalse(entity.equals(new Object()));
        Entity e = new Entity();
        assertFalse(entity.equals(e));
        entity.setAlias(null);
        entity.setHome(null);
        assertTrue(entity.equals(e));
        entity.setAlias("UNIQUE_ALIAS");
        entity.setHome(home);
        e.setAlias("");
        e.setHome(home);
        assertFalse(entity.equals(e));
        e.setAlias("UNIQUE_ALIAS");
        assertTrue(entity.equals(e));
        
        // addressable entity
        addrent = new AddressableEntity();
        addrent.setEntityAddress1("");
        addrent.setEntityAddress2("");
        addrent.setEntityCityName("");
        addrent.setEntityPostalCode("");
        addrent.setProvince(province);
        
        // organization
        organization = new Organization();
        organization.setBusinessName("");
        
        // individual
        individual = new Individual();
        individual.setCredential(credential);
        
        // default entity
        defaultEntity = new DefaultEntity();
        defaultEntity.setId(Integer.MAX_VALUE);
        defaultEntity.setId(Integer.MIN_VALUE);
        defaultEntity.setEntity(entity);
        defaultEntity.setPriority(Integer.MAX_VALUE);
        defaultEntity.setPriority(Integer.MIN_VALUE);
        
        assertTrue(defaultEntity.equals(defaultEntity));
        assertFalse(defaultEntity.equals(null));
        assertFalse(defaultEntity.equals(new Object()));
        DefaultEntity d = new DefaultEntity();
        assertFalse(defaultEntity.equals(d));
        defaultEntity.setEntity(null);
        assertTrue(defaultEntity.equals(d));
        defaultEntity.setEntity(entity);
        assertFalse(defaultEntity.equals(d));
        d.setEntity(entity);
        assertTrue(defaultEntity.equals(d));
        
        // user
        user = new User();
        user.setId(Long.MAX_VALUE);
        user.setId(Long.MIN_VALUE);
        user.setEntity(entity);
        user.setCredential(credential);
        user.setParent(null);
        user.setParent(new User());
        user.setAccountNonExpired(true);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(true);
        user.setAccountNonLocked(false);
        user.setUserType(UserType.EXTERNAL.getValue());
        user.setUserType(UserType.INTERNAL.getValue());
        user.getRoles().add(new Role());
        
        assertTrue(user.equals(user));
        assertFalse(user.equals(null));
        assertFalse(user.equals(new Object()));
        User u = new User();
        assertFalse(user.equals(u));
        user.setEntity(null);
        user.setCredential(null);
        assertTrue(user.equals(u));
        user.setEntity(entity);
        user.setCredential(credential);
        u.setEntity(new Entity());
        u.setCredential(credential);
        assertFalse(user.equals(u));
        u.setEntity(entity);
        assertTrue(user.equals(u));
        
        //province
        province = new Province();
        province.setId(Integer.MAX_VALUE);
        province.setId(Integer.MIN_VALUE);
        province.setHome(home);
        province.setCode("");
        province.setProvinceName("");
        province.setCountry("");
        
        assertTrue(province.equals(province));
        assertFalse(province.equals(null));
        assertFalse(province.equals(new Object()));
        Province p = new Province();
        assertFalse(province.equals(p));
        province.setHome(home);
        province.setCode("ABC");
        p.setHome(new Home());
        p.setCode("ABC");
        assertFalse(province.equals(p));
        p.setHome(home);
        assertTrue(province.equals(p));
        
        // downcasts
        AddressableEntity a1 = (AddressableEntity) individual;
        assertTrue(individual.equals(a1));
        AddressableEntity a2 = (AddressableEntity) organization;
        assertTrue(organization.equals(a2));
        Entity e1 = (Entity) addrent;
        assertTrue(addrent.equals(e1));
        
    }

}
