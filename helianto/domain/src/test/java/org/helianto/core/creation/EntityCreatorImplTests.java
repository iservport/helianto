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


import org.helianto.core.AddressableEntity;
import org.helianto.core.Credential;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.EntityKey;
import org.helianto.core.Home;
import org.helianto.core.Individual;
import org.helianto.core.Organization;
import org.helianto.core.Province;
import org.helianto.core.creation.EntityCreatorImpl;
import org.helianto.core.creation.HomeCreatorImpl;
import org.helianto.core.creation.KeyType;
import org.helianto.core.creation.UserCreatorImpl;

import junit.framework.TestCase;

public class EntityCreatorImplTests extends TestCase {
    
    EntityCreatorImpl factory;
    Home home;
    
    public void setUp() {
        factory = new EntityCreatorImpl();
        home = 
            new HomeCreatorImpl().homeFactory();
    }
    
    public void testEntityKey() {
        Entity entity = 
            factory.entityFactory(home, "UNIQUE");
        EntityKey entityKey = factory.entityKeyFactory(entity, KeyType.COUNTRY_WIDE, "123");
        assertSame(entity, entityKey.getEntity());
        assertEquals(KeyType.COUNTRY_WIDE.getValue(), entityKey.getKeyType());
        assertEquals("123", entityKey.getKeyNumber());
    }

    public void testProvinceFactoryDefaultCountry() {
        home.setCountry("AB");
        Province province = factory.provinceFactory(home, "ABC", "Name");
        assertEquals("AB", province.getCountry());
    }

    public void testProvinceFactory() {
        home.setCountry("AB");
        Province province = factory.provinceFactory(home, "ABC", "NAME", "CD");
        assertEquals("CD", province.getCountry());
        assertSame(home, province.getHome());
        assertEquals("ABC", province.getCode());
        assertEquals("NAME", province.getProvinceName());
    }

    public void testEntityFactory() {
        Entity entity = 
            factory.entityFactory(home, "UNIQUE");
        assertSame(home, entity.getHome());
        assertEquals("UNIQUE", entity.getAlias());
    }

    public void testAddressableEntityFactory() {
        AddressableEntity entity = 
            factory.addressableEntityFactory(home, "UNIQUE");
        assertSame(home, entity.getHome());
        assertEquals("UNIQUE", entity.getAlias());
        assertEquals("", entity.getEntityAddress1());
        assertEquals("", entity.getEntityAddress2());
        assertEquals("", entity.getEntityCityName());
        assertEquals("", entity.getEntityPostalCode());
        assertNull(entity.getProvince());
    }

    public void testAddressableEntityFactoryProvince() {
        Province province = factory.provinceFactory(home, "", "");
        AddressableEntity entity = 
            factory.addressableEntityFactory(province, "UNIQUE");
        assertSame(home, entity.getHome());
        assertSame(province, entity.getProvince());
    }

    public void testOrganizationFactorySupervisorString() {
        Organization organization = 
            factory.organizationFactory(home, "UNIQUE");
        assertEquals("UNIQUE", organization.getAlias());
        assertEquals("UNIQUE", organization.getBusinessName());
    }

    public void testOrganizationFactorySupervisorStringString() {
        Organization organization = 
            factory.organizationFactory(home, "UNIQUE", "BSN_NAME");
        assertEquals("UNIQUE", organization.getAlias());
        assertEquals("BSN_NAME", organization.getBusinessName());
    }

    public void testIndividualFactory() {
        Credential credential = 
            new UserCreatorImpl().credentialFactory("UNIQUE");
        Individual individual = 
            factory.individualFactory(home, credential);
        assertEquals("UNIQUE", individual.getAlias());
        assertSame(credential, individual.getCredential());
    }
    
    public void testDefaultEntityFactory() {
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
    
}
