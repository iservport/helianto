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
import java.util.Random;

import org.helianto.core.AddressableEntity;
import org.helianto.core.Appellation;
import org.helianto.core.Credential;
import org.helianto.core.CredentialState;
import org.helianto.core.CredentialType;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.Gender;
import org.helianto.core.Individual;
import org.helianto.core.LocaleType;
import org.helianto.core.MailAccessData;
import org.helianto.core.MailTransportData;
import org.helianto.core.Notification;
import org.helianto.core.Organization;
import org.helianto.core.PersonalData;
import org.helianto.core.Supervisor;
import org.helianto.core.User;
import org.helianto.core.UserType;

/**
 * Core domain objects factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CoreFactoryImpl extends AbstractGenericService {
    
    public PersonalData personalDataFactory() {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName("");
        personalData.setLastName("");
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
        return personalData;
    }

    public Credential credentialFactory() {
        return credentialFactory("");
    }

    public Credential credentialFactory(String principal) {
        Credential credential = new Credential();
        credential.setPrincipal(principal);
        credential.setPassword(generatePassword(8));
        credential.setCreated(new Date());
        credential.setLastModified(credential.getCreated());
        credential.setExpired(credential.getCreated());
        credential.setCredentialType(CredentialType.NOT_ADDRESSABLE.getValue());
        credential.setNotification(Notification.BY_REQUEST.getValue());
        credential.setCredentialState(CredentialState.IDLE.getValue());
        PersonalData pd = personalDataFactory();
        credential.setPersonalData(pd);
        return credential;
    }
    
    public org.helianto.core.Locale localeFactory() {
        return localeFactory(java.util.Locale.getDefault());
    }
    
    public org.helianto.core.Locale localeFactory(java.util.Locale javaLocale) {
        org.helianto.core.Locale locale = new org.helianto.core.Locale();
        locale.setLanguage(javaLocale.getLanguage());
        locale.setCountry(javaLocale.getCountry());
        locale.setLocaleType(LocaleType.COUNTRY.getValue());
        return locale;
    }
    
    public org.helianto.core.Locale localeFactory(String language) {
        return localeFactory(language, "");
    }
    
    public org.helianto.core.Locale localeFactory(String language, String country) {
        org.helianto.core.Locale locale = new org.helianto.core.Locale();
        locale.setLanguage(language);
        locale.setCountry(country);
        if (country.compareTo("")==0) {
            locale.setLocaleType(LocaleType.LANGUAGE.getValue());
        } else {
            locale.setLocaleType(LocaleType.COUNTRY.getValue());
        }
        return locale;
    }
    
    public MailTransportData mailTransportDataFactory(String host, String user, String password) {
        MailTransportData mailTransportData = new MailTransportData();
        mailTransportData.setSmtpHost(host);
        mailTransportData.setSmtpUser(user);
        mailTransportData.setSmtpPassword(password);
        return mailTransportData;
    }
    
    public MailAccessData mailAccessDataFactory(String host, String user, String password) {
        MailAccessData mailAccessData = new MailAccessData();
        mailAccessData.setHost(host);
        mailAccessData.setUser(user);
        mailAccessData.setPassword(password);
        return mailAccessData;
    }
    
    public Supervisor supervisorFactory() {
        return supervisorFactory(localeFactory(), "");
    }
    
    public Supervisor supervisorFactory(org.helianto.core.Locale locale) {
        return supervisorFactory(locale, "");
    }
    
    public Supervisor supervisorFactory(org.helianto.core.Locale locale, String supervisorName) {
        Supervisor supervisor = new Supervisor();
        supervisor.setHttpAddress("");
        supervisor.setSupervisorName(supervisorName);
        supervisor.setSupervisorDesc("");
        supervisor.setLocale(locale);
        supervisor.setAdded(new Date());
        return supervisor;
    }
    
    public Entity entityFactory(Supervisor supervisor, String uniqueAlias) {
        Entity entity = new Entity();
        entity.setSupervisor(supervisor);
        entity.setAlias(uniqueAlias);
        return entity;
    }
    
    public AddressableEntity addressableEntityFactory(Supervisor supervisor, String uniqueAlias) {
        AddressableEntity entity = new AddressableEntity();
        entity.setSupervisor(supervisor);
        entity.setAlias(uniqueAlias);
        entity.setEntityAddress1("");
        entity.setEntityAddress2("");
        entity.setEntityCityName("");
        entity.setEntityProvinceName("");
        entity.setEntityPostalCode("");
        return entity;
    }
    
    public Organization organizationFactory(Supervisor supervisor, String uniqueAlias) {
        return organizationFactory(supervisor, uniqueAlias, uniqueAlias);
    }
    
    public Organization organizationFactory(Supervisor supervisor, String uniqueAlias, String businessName) {
        Organization organization = new Organization();
        organization.setSupervisor(supervisor);
        organization.setAlias(uniqueAlias);
        organization.setBusinessName(businessName);
        return organization;
    }
    
    public Individual individualFactory(Supervisor supervisor, Credential credential) {
        Individual individual = new Individual();
        individual.setSupervisor(supervisor);
        individual.setAlias(credential.getPrincipal());
        individual.setCredential(credential);
        return individual;
    }
    
    public DefaultEntity defaultEntityFactory() {
        return defaultEntityFactory("", 0);
    }
    
    public DefaultEntity defaultEntityFactory(String entityAlias) {
        return defaultEntityFactory(entityAlias, 0);
    }
    
    public DefaultEntity defaultEntityFactory(String entityAlias, int priority) {
        DefaultEntity defaultEntity = new DefaultEntity();
        defaultEntity.setSupervisorName(entityAlias);
        defaultEntity.setLocale(localeFactory());
        Entity entity = entityFactory((Supervisor) defaultEntity, entityAlias);
        defaultEntity.setDefaultEntity(entity);
        defaultEntity.setAdded(new Date());
        return defaultEntity;
    }
    
    public User userFactory(Entity entity, Credential credential) {
        User user = new User();
        user.setEntity(entity);
        user.setCredential(credential);
        user.setUserType(UserType.INTERNAL.getValue());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        return user;
    }

    public User userFactory(User parent, Credential credential) {
        User user = userFactory(parent.getEntity(), credential);
        user.setParent(parent);
        return user;
    }

    public String generatePassword(int size) {
        Random generator = new Random();
        String source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String password = ""; 
        for (int i=0; i<size; i++)  {
            int index = generator.nextInt(source.length());
            password += source.substring(index, index+1);
        }
        return password;
    }
        
}
