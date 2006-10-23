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

import java.util.Locale;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.creation.AuthorizationCreator;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.type.OperationMode;

/**
 * <code>ServerMgr</code> base class.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractServerMgr implements ServerMgr {

    protected ServiceManagementTemplate serviceManagementTemplate = new DefaultServiceManagementImpl();

    protected SystemConfigurationTemplate systemConfigurationTemplate = new DefaultSystemConfigurationImpl();

    public User createSystemConfiguration(Identity managerIdentity) {
        Entity defaultEntity = systemConfigurationTemplate
                .createDefaultEntity();
        UserRole adminManagerRole = serviceManagementTemplate
                .createManagerRole(defaultEntity, "ADMIN");
        User manager = systemConfigurationTemplate.createManager(
                adminManagerRole, managerIdentity);
        return manager;
    }

    // mutators

    public void setServiceManagementTemplate(
            ServiceManagementTemplate serviceManagementTemplate) {
        this.serviceManagementTemplate = serviceManagementTemplate;
    }

    public void setSystemConfigurationTemplate(
            SystemConfigurationTemplate systemConfigurationTemplate) {
        this.systemConfigurationTemplate = systemConfigurationTemplate;
    }

    // inner classes

    /**
     * Default implementation of <code>ServiceManagementTemplate</code>.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class DefaultServiceManagementImpl implements
            ServiceManagementTemplate {

        public UserRole createManagerRole(Entity entity, String serviceName) {
            UserGroup userGroup = AuthorizationCreator.userGroupFactory(entity,
                    null);
            Service emptyService = OperatorCreator.serviceFactory(entity
                    .getOperator(), serviceName);
            UserRole managerRole = AuthorizationCreator.userRoleFactory(
                    userGroup, emptyService, "MANAGER");
            return managerRole;
        }
    }

    /**
     * Default implementation of <code>SystemConfigurationTemplate</code>.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class DefaultSystemConfigurationImpl implements
            SystemConfigurationTemplate {

        public Entity createDefaultEntity() {
            Operator operator = OperatorCreator.operatorFactory("DEFAULT",
                    OperationMode.LOCAL, Locale.getDefault());
            return OperatorCreator.entityFactory(operator, "DEFAULT");
        }

        public User createManager(UserRole managerRole, Identity managerIdentity) {
            return AuthorizationCreator.userFactory(managerRole.getUserGroup(),
                    managerIdentity);
        }

    }

}
