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
import org.helianto.core.Entity;
import org.helianto.core.Gender;
import org.helianto.core.LocaleType;
import org.helianto.core.MailAccessData;
import org.helianto.core.MailTransportData;
import org.helianto.core.Notification;
import org.helianto.core.PersonalData;
import org.helianto.core.Supervisor;

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

    public void testLocaleFactory() {
        java.util.Locale defaultLocale = java.util.Locale.getDefault();
        org.helianto.core.Locale locale = 
            factory.localeFactory();
        assertEquals(defaultLocale.getLanguage(),
                locale.getLanguage());
        assertEquals(defaultLocale.getCountry(),
                locale.getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                locale.getLocaleType());
    }

    public void testLocaleFactoryLocale() {
        java.util.Locale usLocale = java.util.Locale.US;
        org.helianto.core.Locale locale = 
            factory.localeFactory(usLocale);
        assertEquals(usLocale.getLanguage(),
                locale.getLanguage());
        assertEquals(usLocale.getCountry(),
                locale.getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                locale.getLocaleType());
        java.util.Locale frLocale = java.util.Locale.FRANCE;
        locale = factory.localeFactory(frLocale);
        assertEquals(frLocale.getLanguage(),
                locale.getLanguage());
        assertEquals(frLocale.getCountry(),
                locale.getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                locale.getLocaleType());
    }

    public void testLocaleFactoryString() {
        org.helianto.core.Locale locale = 
            factory.localeFactory("pt");
        assertEquals("pt", locale.getLanguage());
        assertEquals("", locale.getCountry());
        assertEquals(LocaleType.LANGUAGE.getValue(),
                locale.getLocaleType());
    }

    public void testLocaleFactoryStringString() {
        org.helianto.core.Locale locale = 
            factory.localeFactory("pt", "BR");
        assertEquals("pt", locale.getLanguage());
        assertEquals("BR", locale.getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                locale.getLocaleType());
        assertNull(locale.getParent());
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

    public void testSupervisorFactory() {
        Supervisor supervisor = 
            factory.supervisorFactory();
        assertEquals("", supervisor.getSupervisorName());
        java.util.Locale defaultLocale = java.util.Locale.getDefault();
        assertEquals(defaultLocale.getLanguage(),
                supervisor.getLocale().getLanguage());
        assertEquals(defaultLocale.getCountry(),
                supervisor.getLocale().getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                supervisor.getLocale().getLocaleType());
    }

    public void testSupervisorFactoryLocale() {
        org.helianto.core.Locale locale = 
            factory.localeFactory("pt", "BR");
        Supervisor supervisor = 
            factory.supervisorFactory(locale);
        assertEquals("pt",
                supervisor.getLocale().getLanguage());
        assertEquals("BR",
                supervisor.getLocale().getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                supervisor.getLocale().getLocaleType());
    }

    public void testSupervisorFactoryLocaleString() {
        org.helianto.core.Locale locale = 
            factory.localeFactory("pt", "BR");
        Supervisor supervisor = 
            factory.supervisorFactory(locale, "UNIQUE");
        assertEquals("pt",
                supervisor.getLocale().getLanguage());
        assertEquals("BR",
                supervisor.getLocale().getCountry());
        assertEquals(LocaleType.COUNTRY.getValue(),
                supervisor.getLocale().getLocaleType());
        assertEquals("UNIQUE", supervisor.getSupervisorName());
        assertTrue(supervisor.getAdded().compareTo(new Date()) < 1000);
        assertEquals("", supervisor.getHttpAddress());
        assertEquals("", supervisor.getSupervisorDesc());
        assertNull(supervisor.getParent());
        assertNull(supervisor.getMailAccessData());
        assertNull(supervisor.getMailTransportData());
    }

    public void testEntityFactory() {
        Supervisor supervisor = 
            factory.supervisorFactory();
        Entity entity = 
            factory.entityFactory(supervisor, "UNIQUE");
        assertSame(supervisor, entity.getSupervisor());
        assertEquals("UNIQUE", entity.getAlias());
    }

    public void testAddressableEntityFactory() {
        Supervisor supervisor = 
            factory.supervisorFactory();
        AddressableEntity entity = 
            factory.addressableEntityFactory(supervisor, "UNIQUE");
        assertSame(supervisor, entity.getSupervisor());
        assertEquals("UNIQUE", entity.getAlias());
        assertEquals("", entity.getEntityAddress1());
        assertEquals("", entity.getEntityAddress2());
        assertEquals("", entity.getEntityCityName());
        assertEquals("", entity.getEntityPostalCode());
        assertEquals("", entity.getEntityProvinceName());
    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.organizationFactory(Supervisor, String)'
     */
    public void testOrganizationFactorySupervisorString() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.organizationFactory(Supervisor, String, String)'
     */
    public void testOrganizationFactorySupervisorStringString() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.individualFactory(Supervisor, Credential)'
     */
    public void testIndividualFactory() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.defaultEntityFactory()'
     */
    public void testDefaultEntityFactory() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.defaultEntityFactory(String)'
     */
    public void testDefaultEntityFactoryString() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.defaultEntityFactory(String, int)'
     */
    public void testDefaultEntityFactoryStringInt() {
        // TODO Auto-generated method stub

    }

    /*
     * Test method for 'org.helianto.core.service.CoreFactoryImpl.userFactory(Entity, Credential)'
     */
    public void testUserFactoryEntityCredential() {
        // TODO Auto-generated method stub

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
