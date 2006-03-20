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


import junit.framework.TestCase;

public class EntityCreatorImplTests extends TestCase {
    
    EntityCreatorImpl factory;
    Home home;
    
    public void setUp() {
        factory = new EntityCreatorImpl();
        home = 
            new HomeCreatorImpl().homeFactory();
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
        assertEquals("", entity.getEntityProvinceName());
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
