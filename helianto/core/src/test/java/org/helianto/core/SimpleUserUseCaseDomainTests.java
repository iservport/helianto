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
    Locale locale;
    MailTransportData mtd;
    MailAccessData mad;
    Supervisor supervisor;
    Entity entity;
    AddressableEntity addrent;
    Individual individual;
    Organization organization;
    DefaultEntity defent;
    User user;
    
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
        
        // locale
        locale = new Locale();
        locale.setParent(null);
        locale.setParent(new Locale());
        locale.setLanguage("");
        locale.setCountry("");
        locale.setLocaleType(LocaleType.COUNTRY.getValue());
        locale.setLocaleType(LocaleType.LANGUAGE.getValue());
        
        assertTrue(locale.equals(locale));
        assertFalse(locale.equals(null));
        assertFalse(locale.equals(new Object()));
        Locale l = new Locale();
        assertFalse(locale.equals(l));
        locale.setCountry(null);
        locale.setLanguage(null);
        assertTrue(locale.equals(l));
        locale.setCountry("UNIQUE_COUNTRY");
        locale.setLanguage("UNIQUE_LANGUAGE");
        l.setCountry("");
        l.setLanguage("UNIQUE_LANGUAGE");
        assertFalse(locale.equals(l));
        l.setCountry("UNIQUE_COUNTRY");
        assertTrue(locale.equals(l));
        
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
        
        // supervisor
        supervisor = new Supervisor();
        supervisor.setId(Integer.MAX_VALUE);
        supervisor.setId(Integer.MIN_VALUE);
        supervisor.setParent(null);
        supervisor.setParent(new Supervisor());
        supervisor.setSupervisorName("");
        supervisor.setHttpAddress("");
        supervisor.setSupervisorDesc("");
        supervisor.setAdded(new Date());
        supervisor.setLocale(locale);
        supervisor.setMailTransportData(mtd);
        supervisor.setMailAccessData(mad);
        
        assertTrue(supervisor.equals(supervisor));
        assertFalse(supervisor.equals(null));
        assertFalse(supervisor.equals(new Object()));
        Supervisor s = new Supervisor();
        assertFalse(supervisor.equals(s));
        supervisor.setSupervisorName(null);
        assertTrue(supervisor.equals(s));
        supervisor.setSupervisorName("UNIQUE");
        s.setSupervisorName("");
        assertFalse(supervisor.equals(s));
        s.setSupervisorName("UNIQUE");
        assertTrue(supervisor.equals(s));
        
        // entity
        entity = new Entity();
        entity.setId(Long.MAX_VALUE);
        entity.setId(Long.MIN_VALUE);
        entity.setAlias("");
        entity.setSupervisor(null);
        entity.setSupervisor(supervisor);
        
        assertTrue(entity.equals(entity));
        assertFalse(entity.equals(null));
        assertFalse(entity.equals(new Object()));
        Entity e = new Entity();
        assertFalse(entity.equals(e));
        entity.setAlias(null);
        entity.setSupervisor(null);
        assertTrue(entity.equals(e));
        entity.setAlias("UNIQUE_ALIAS");
        entity.setSupervisor(supervisor);
        e.setAlias("");
        e.setSupervisor(supervisor);
        assertFalse(entity.equals(e));
        e.setAlias("UNIQUE_ALIAS");
        assertTrue(entity.equals(e));
        
        // addressable entity
        addrent = new AddressableEntity();
        addrent.setEntityAddress1("");
        addrent.setEntityAddress2("");
        addrent.setEntityCityName("");
        addrent.setEntityPostalCode("");
        addrent.setEntityProvinceName("");
        
        // organization
        organization = new Organization();
        organization.setBusinessName("");
        
        // individual
        individual = new Individual();
        individual.setCredential(credential);
        
        // default entity
        defent = new DefaultEntity();
        defent.setDefaultEntity(entity);
        defent.setPriority(Integer.MAX_VALUE);
        defent.setPriority(Integer.MIN_VALUE);
        
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
        
        // downcasts
        Supervisor s1 = (Supervisor) defent;
        assertTrue(defent.equals(s1));
        AddressableEntity a1 = (AddressableEntity) individual;
        assertTrue(individual.equals(a1));
        AddressableEntity a2 = (AddressableEntity) organization;
        assertTrue(organization.equals(a2));
        Entity e1 = (Entity) addrent;
        assertTrue(addrent.equals(e1));
        
    }

}
