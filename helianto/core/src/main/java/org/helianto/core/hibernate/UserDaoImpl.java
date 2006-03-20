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

package org.helianto.core.hibernate;

import org.helianto.core.User;
import org.helianto.core.dao.UserDao;

public class UserDaoImpl extends GenericDaoImpl implements UserDao {

    public void persistUser(User user) {
        persist(user);
    }

    public void removeUser(User user) {
        remove(user);
    }

    public User findUserByEntityAliasAndPrincipal(String alias, String principal) {
        return (User) findUnique(USER_QRY, new Object[] {alias, principal});
    }
    
    static final String USER_QRY = 
        "from User user " +
        "where user.entity.alias = ? " +
        "and user.credential.principal = ?";

}
