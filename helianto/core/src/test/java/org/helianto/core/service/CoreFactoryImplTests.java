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


import java.util.Date;

import org.helianto.core.AddressableEntity;
import org.helianto.core.Appellation;
import org.helianto.core.Credential;
import org.helianto.core.CredentialState;
import org.helianto.core.CredentialType;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Gender;
import org.helianto.core.Individual;
import org.helianto.core.MailAccessData;
import org.helianto.core.MailTransportData;
import org.helianto.core.Notification;
import org.helianto.core.Organization;
import org.helianto.core.PersonalData;
import org.helianto.core.Home;
import org.helianto.core.User;
import org.helianto.core.UserType;

import junit.framework.TestCase;

public class CoreFactoryImplTests extends TestCase {
    
    CoreFactoryImpl factory;
    
    public void setUp() {
        factory = new CoreFactoryImpl();
    }

    public void testPersonalDataFactory() {
        PersonalData personalData = 
            factory.personalDataFactory();
        assertEquals("", personalData.getFirstName());
        assertEquals("", personalData.getLastName());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                personalData.getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                personalData.getGender());
    }

    public void testCredentialFactory() {
        Credential credential = factory.credentialFactory();
        assertEquals("", credential.getPrincipal());
    }

    public void testCredentialFactoryString() {
        Credential credential = 
            factory.credentialFactory("UNIQUE");
        assertEquals("UNIQUE", credential.getPrincipal());
        assertEquals(8, credential.getPassword().length());
        assertTrue(credential.getCreated().compareTo(new Date()) < 1000);
        assertSame(credential.getCreated(), credential.getLastModified());
        assertSame(credential.getCreated(), credential.getExpired());
        assertEquals(CredentialType.NOT_ADDRESSABLE.getValue(), credential.getCredentialType());
        assertEquals(Notification.BY_REQUEST.getValue(), credential.getNotification());
        assertEquals(CredentialState.IDLE.getValue(), credential.getCredentialState());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                credential.getPersonalData().getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                credential.getPersonalData().getGender());
        assertEquals(0, credential.getUsers().size());
    }

    public void testMailTransportDataFactory() {
        MailTransportData mailTransportData = 
            factory.mailTransportDataFactory("host", "user", "password");
        assertEquals("host", mailTransportData.getSmtpHost());
        assertEquals("user", mailTransportData.getSmtpUser());
        assertEquals("password", mailTransportData.getSmtpPassword());
    }

    public void testMailAccessDataFactory() {
        MailAccessData mailAccessData = 
            factory.mailAccessDataFactory("host", "user", "password");
        assertEquals("host", mailAccessData.getHost());
        assertEquals("user", mailAccessData.getUser());
        assertEquals("password", mailAccessData.getPassword());
    }

    public void testHomeFactory() {
        Home home = 
            factory.homeFactory();
        assertEquals("", home.getHomeName());
    }

    public void testHomeFactoryString() {
        Home home = 
            factory.homeFactory("UNIQUE");
        assertEquals("UNIQUE", home.getHomeName());
    }

    public void testHomeFactoryStringStringString() {
        Home home = 
            factory.homeFactory("UNIQUE", "LANGUAGE", "COUNTRY");
        assertEquals("LANGUAGE", home.getLanguage());
        assertEquals("COUNTRY", home.getCountry());
        assertEquals("UNIQUE", home.getHomeName());
        assertTrue(home.getAdded().compareTo(new Date()) < 1000);
        assertEquals("", home.getHttpAddress());
        assertEquals("", home.getHomeDesc());
        assertNull(home.getParent());
        assertNull(home.getMailAccessData());
        assertNull(home.getMailTransportData());
    }

    public void testEntityFactory() {
        Home home = 
            factory.homeFactory();
        Entity entity = 
            factory.entityFactory(home, "UNIQUE");
        assertSame(home, entity.getHome());
        assertEquals("UNIQUE", entity.getAlias());
    }

    public void testAddressableEntityFactory() {
        Home home = 
            factory.homeFactory();
        AddressableEntity entity = 
            factory.addressableEntityFactory(home, "UNIQUE");
        assertSame(home, entity.getHome());
        assertEquals("UNIQUE", entity.getAlias());
        assertEquals("", entity.getEntityAddress1());
        assertEquals("", entity.getEntityAddress2());
        assertEquals("", entity.getEntityCityName());
        assertEquals("", entity.getEntityPostalCode());
        assertEquals("", entity.getEntityProvinceName());
    }

    public void testOrganizationFactorySupervisorString() {
        Home home = 
            factory.homeFactory();
        Organization organization = 
            factory.organizationFactory(home, "UNIQUE");
        assertEquals("UNIQUE", organization.getAlias());
        assertEquals("UNIQUE", organization.getBusinessName());
    }

    public void testOrganizationFactorySupervisorStringString() {
        Home home = 
            factory.homeFactory();
        Organization organization = 
            factory.organizationFactory(home, "UNIQUE", "BSN_NAME");
        assertEquals("UNIQUE", organization.getAlias());
        assertEquals("BSN_NAME", organization.getBusinessName());
    }

    public void testIndividualFactory() {
        Credential credential = 
            factory.credentialFactory("UNIQUE");
        Home home = 
            factory.homeFactory();
        Individual individual = 
            factory.individualFactory(home, credential);
        assertEquals("UNIQUE", individual.getAlias());
        assertSame(credential, individual.getCredential());
    }
    
    public void testDefaultEntityFactory() {
        Home home = 
            factory.homeFactory();
        Entity entity = 
            factory.entityFactory(home, "UNIQUE");
        DefaultEntity defaultEntity = 
            factory.defaultEntityFactory(entity);
        assertSame(entity, defaultEntity.getEntity());
        Organization organization = 
            factory.organizationFactory(home, "UNIQUE", "BSN_NAME");
        DefaultEntity defaultOrganization = 
            factory.defaultEntityFactory((Entity) organization);
        assertSame(organization, defaultOrganization.getEntity());
    }

    public void testUserFactoryEntityCredential() {
        Home home = 
            factory.homeFactory();
        Credential credential = 
            factory.credentialFactory("UNIQUE");
        Entity entity = 
            factory.entityFactory(home, "UNIQUE_ENTITY");
        User user = factory.userFactory(entity, credential);
        assertSame(entity, user.getEntity());
        assertSame(credential, user.getCredential());
        assertNull(user.getParent());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertEquals(UserType.INTERNAL.getValue(), 
                user.getUserType());
        assertEquals(0, user.getRoles().size());
    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.userFactory(User, Credential)'
     */
    public void testUserFactoryUserCredential() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.generatePassword(int)'
     */
    public void testGeneratePassword() {
        // TODO Auto-generated method stub

    }

}
