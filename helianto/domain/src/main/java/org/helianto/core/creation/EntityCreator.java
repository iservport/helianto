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
 * A factory method pattern interface to <code>Entity</code>
 * related domain objects.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public interface EntityCreator {

    public Entity entityFactory(Home home, String uniqueAlias);

    public AddressableEntity addressableEntityFactory(Home home,
            String uniqueAlias);

    public Organization organizationFactory(Home home, String uniqueAlias);

    public Organization organizationFactory(Home home, String uniqueAlias,
            String businessName);

    public Individual individualFactory(Home home, Credential credential);

    public DefaultEntity defaultEntityFactory(Entity entity);

    public DefaultEntity defaultEntityFactory(Entity entity, int priority);
    
    public Province provinceFactory(Home home, String code, String name);

    public Province provinceFactory(Home home, String code, String name, String country);
    
    public EntityKey entityKeyFactory(Entity entity, KeyType keyType, String keyNumber);

}