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

import org.helianto.core.UserRole;
import org.helianto.core.dao.UserRoleDao;
import org.helianto.core.hibernate.GenericDaoImpl;



import org.helianto.core.UserGroup;
import org.helianto.core.Service;
/**
 * Default implementation of <code>UserRole</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserRoleDaoImpl extends GenericDaoImpl implements UserRoleDao {
     
    public void persistUserRole(UserRole userRole) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+userRole);
        }
        persist(userRole);
    }
    
    public UserRole mergeUserRole(UserRole userRole) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+userRole);
        }
        return (UserRole) merge(userRole);
    }
    
    public void removeUserRole(UserRole userRole) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+userRole);
        }
        remove(userRole);
    }
    
    public UserRole findUserRoleByNaturalId(UserGroup userGroup, Service service, String serviceExtension) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique userRole with userGroup='"+userGroup+"' and service='"+service+"' and serviceExtension='"+serviceExtension+"' ");
        }
        return (UserRole) findUnique(UserRole.getUserRoleNaturalIdQueryString(), userGroup, service, serviceExtension);
    }
    
    
}
