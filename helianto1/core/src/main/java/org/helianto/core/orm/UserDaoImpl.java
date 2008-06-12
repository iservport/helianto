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
import org.helianto.core.dao.UserDao;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>User</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl implements UserDao {
     
    public void persistUser(User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+user);
        }
        persist(user);
    }
    
    public User mergeUser(User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+user);
        }
        return (User) merge(user);
    }
    
    public void removeUser(User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+user);
        }
        remove(user);
    }
    
    public User findUserByNaturalId(Entity entity, Identity identity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique user with entity='"+entity+"' and identity='"+identity+"' ");
        }
        return (User) findUnique(User.getUserNaturalIdQueryString(), entity, identity);
    }
    
    
	static String USER_ENTITY_QRY = "select user from User user "+
	    "where user.entity = ? ";

	public List<User> findUserByCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
