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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;

/**
 * Default implementation for <code>RoleDefinitionStrategy</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultRoleDefinitionStrategy implements RoleDefinitionStrategy {

	public UserGroup grant(Entity entity, Service service,
			String[] extensions) {
        UserGroup userGroup = UserGroup.userGroupFactory(entity, service.getServiceName());
        for (String extension: extensions) {
            UserRole userRole = UserRole.userRoleFactory(userGroup, service, extension);;
            if (logger.isDebugEnabled()) {
                logger.debug("Role granted: "+userRole);
            }
        }
        return userGroup;
	}
	
	public static final Log logger = LogFactory.getLog(DefaultRoleDefinitionStrategy.class);

}
