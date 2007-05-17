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

package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.hibernate.GenericDaoImpl;
/**
 * Default implementation of <code>UserGroup</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupDaoImpl extends GenericDaoImpl implements UserGroupDao {
     
    public void persistUserGroup(UserGroup userGroup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+userGroup);
        }
        persist(userGroup);
    }
    
    public UserGroup mergeUserGroup(UserGroup userGroup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+userGroup);
        }
        return (UserGroup) merge(userGroup);
    }
    
    public void removeUserGroup(UserGroup userGroup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+userGroup);
        }
        remove(userGroup);
    }
    
    public UserGroup findUserGroupByNaturalId(Entity entity, Identity identity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique userGroup with entity='"+entity+"' and identity='"+identity+"' ");
        }
        return (UserGroup) findUnique(UserGroup.getUserGroupNaturalIdQueryString(), entity, identity);
    }
    
    
	static String USERGROUP_ENTITY_QRY = "select userGroup from UserGroup userGroup "+
	    "where userGroup.entity = ? ";


	public List<User> findUserByCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserGroup> findUserGroupByCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserGroup> findUserGroupByEntity(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
