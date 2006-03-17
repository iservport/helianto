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

import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.PersonalData;
import org.helianto.core.User;

public class SimpleCoreMgrImpl extends CoreMgrImpl implements SimpleCoreMgr {

    public Entity createDefaultEntity(String alias) {
        Home home = homeFactory(alias);
        Entity entity = entityFactory(home, alias);
        return entity;
    }

    public void persistDefaultEntity(Entity entity) {
        persistEntity(entity);
    }

    public void changeEntityToDefault(Entity entity) {
        // TODO Auto-generated method stub
        
    }

    public Entity findDefaultEntity() {
        
        return null;
    }

    public User createSimpleUser(String principal, PersonalData pd) {
        // TODO Auto-generated method stub
        return null;
    }

    public User createSimpleUser(Entity entity, String principal, PersonalData pd) {
        // TODO Auto-generated method stub
        return null;
    }

    public void validatePassowrd(Credential cred, String verification) {
        // TODO Auto-generated method stub
        
    }
    
}
