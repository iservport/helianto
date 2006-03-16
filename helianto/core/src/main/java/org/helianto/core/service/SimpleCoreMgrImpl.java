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

import org.helianto.core.Credential;
import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;

public class SimpleCoreMgrImpl extends CoreMgrImpl implements SimpleCoreMgr {

    public DefaultEntity createDefaultEntity(String alias) {
        DefaultEntity defaultEntity = defaultEntityFactory(alias);
        return defaultEntity;
    }

    public DefaultEntity createDefaultEntity(String alias, int priority) {
        // TODO Auto-generated method stub
        return null;
    }

    public DefaultEntity changeEntityToDefault(Entity entity, int priority) {
        // TODO Auto-generated method stub
        return null;
    }

    public DefaultEntity findDefaultEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    public DefaultEntity findDefaultEntity(int priority) {
        // TODO Auto-generated method stub
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
    
    public void persistDefaultEntity(DefaultEntity defaultEntity) {
    	getGenericDao().merge(defaultEntity);
    }

}
