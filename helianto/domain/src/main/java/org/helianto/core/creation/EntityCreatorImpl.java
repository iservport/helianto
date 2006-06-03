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

/**
 * Default implementation for the <code>EntityCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class EntityCreatorImpl extends HomeCreatorImpl implements EntityCreator {
    
    public Entity entityFactory(Home home, String uniqueAlias) {
        Entity entity = new Entity();
        entity.setHome(home);
        entity.setAlias(uniqueAlias);
        return entity;
    }
    
    public AddressableEntity addressableEntityFactory(Home home, String uniqueAlias) {
        return addressableEntityFactory(home, null, uniqueAlias);
    }
    
    public AddressableEntity addressableEntityFactory(Province province, String uniqueAlias) {
        return addressableEntityFactory(province.getHome(), province, uniqueAlias);
    }
    
    private AddressableEntity addressableEntityFactory(Home home, Province province, String uniqueAlias) {
        AddressableEntity entity = new AddressableEntity();
        entity.setHome(home);
        entity.setAlias(uniqueAlias);
        entity.setEntityAddress1("");
        entity.setEntityAddress2("");
        entity.setEntityAddress3("");
        entity.setEntityCityName("");
        entity.setProvince(province);
        entity.setEntityPostalCode("");
        return entity;
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.EntityCreator#organizationFactory(org.helianto.core.Home, java.lang.String)
     */
    public Organization organizationFactory(Home home, String uniqueAlias) {
        return organizationFactory(home, uniqueAlias, uniqueAlias);
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.EntityCreator#organizationFactory(org.helianto.core.Home, java.lang.String, java.lang.String)
     */
    public Organization organizationFactory(Home home, String uniqueAlias, String businessName) {
        Organization organization = new Organization();
        organization.setHome(home);
        organization.setAlias(uniqueAlias);
        organization.setBusinessName(businessName);
        return organization;
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.EntityCreator#individualFactory(org.helianto.core.Home, org.helianto.core.Credential)
     */
    public Individual individualFactory(Home home, Credential credential) {
        Individual individual = new Individual();
        individual.setHome(home);
        individual.setAlias(credential.getPrincipal());
        individual.setCredential(credential);
        return individual;
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.EntityCreator#defaultEntityFactory(org.helianto.core.Entity)
     */
    public DefaultEntity defaultEntityFactory(Entity entity) {
        return defaultEntityFactory(entity, 0);
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.EntityCreator#defaultEntityFactory(org.helianto.core.Entity, int)
     */
    public DefaultEntity defaultEntityFactory(Entity entity, int priority) {
        DefaultEntity defaultEntity = new DefaultEntity();
        defaultEntity.setEntity(entity);
        defaultEntity.setPriority(priority);
        return defaultEntity;
    }

    public Province provinceFactory(Home home, String code, String name) {
        return provinceFactory(home, code, name, home.getCountry());
    }

    public Province provinceFactory(Home home, String code, String name, String country) {
        Province province = new Province();
        province.setHome(home);
        province.setCode(code);
        province.setProvinceName(name);
        province.setCountry(country);
        return province;
    }

    public EntityKey entityKeyFactory(Entity entity, KeyType keyType, String keyNumber) {
        EntityKey entityKey = new EntityKey();
        entityKey.setEntity(entity);
        entityKey.setKeyType(keyType.getValue());
        entityKey.setKeyNumber(keyNumber);
        return entityKey;
    }
    
}
