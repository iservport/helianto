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

import java.util.Locale;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Encription;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Individual;
import org.helianto.core.OperationMode;
import org.helianto.core.Operator;
import org.helianto.core.Organization;
import org.helianto.core.Province;
import org.helianto.core.Server;
import org.helianto.core.ServerType;

/**
 * Operator required classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorCreator extends CreatorSupport {
    
    /**
     * Default <code>Operator</code> creator.
     *  
     * @param operatorName
     * @param operationMode if null, default is OperationMode.LOCAL
     * @param locale if null, default is Locale.getDefault()
     * 
     * @see OperationMode
     */
    public static Operator operatorFactory(String operatorName, OperationMode operationMode, Locale locale) {
        Operator operator = new Operator();
        
        operator.setOperatorName(operatorName);
        if (operationMode==null) {
            operator.setOperationMode(OperationMode.LOCAL.getValue());
        } else {
            operator.setOperationMode(operationMode.getValue());
        }
        if (locale==null) {
            operator.setLocale(Locale.getDefault());
        } else {
            operator.setLocale(locale);
        }
        operator.setOperatorSourceMailAddress("operator@helianto.org");
        operator.setDefaultEncoding("ISO-8859-1");
        operator.setOperatorHostAddress("http://www.helianto.org");
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+operator);
        }
        return operator;
    }

    /**
     * Default <code>Server</code> creator.
     * 
     * Set ServerState to ActivityState.ACTIVE and
     * RequiredEncription to Encription.PLAIN_PASSWORD by default.
     * 
     * @param requiredOperator
     * @param serverName
     * @param serverType if null, default is ServerType.SMTP_SERVER
     * @param credential if null, create one with serverName and empty password
     * 
     * @see ServerType
     * @see ActivityState
     * @see Encription
     */
    public static Server serverFactory(Operator requiredOperator, String serverName, ServerType serverType, Credential credential) {
        assertNotNull(requiredOperator);
        Server server = new Server();

        server.setOperator(requiredOperator);
        server.setServerName(serverName);
        server.setServerHostAddress("");
        server.setServerPort(-1);
        server.setServerDesc("");
        if (serverType==null) {
            server.setServerType(ServerType.SMTP_SERVER.getValue());
        } else {
            server.setServerType(serverType.getValue());
        }
        server.setPriority((byte) 1);
        server.setServerState(ActivityState.ACTIVE.getValue());
        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        if (credential==null) {
            Identity identity = AuthenticationCreator.identityFactory("", "");
            credential = AuthenticationCreator.credentialFactory(identity, "");
            credential.getIdentity().setPrincipal(serverName);
        } 
        server.setCredential(credential);
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+server);
        }
        return server;
    }

    /**
     * Default <code>Province</code> creator.
     * 
     * @param requiredOperator
     * @param code
     * @param provinceName
     */
    public static Province provinceFactory(Operator requiredOperator, String code, String provinceName) {
        assertNotNull(requiredOperator);
        Province province = new Province();

        province.setOperator(requiredOperator);
        province.setCode(code);
        province.setProvinceName(provinceName);
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+province);
        }
        return province;
    }

    private static Entity internalEntityFactory(Class<? extends Entity> clazz, Operator requiredOperator, String uniqueAlias) {
        assertNotNull(requiredOperator);
        try {
            Entity entity = clazz.newInstance();

            entity.setOperator(requiredOperator);
            entity.setAlias(uniqueAlias);
            if (logger.isDebugEnabled()) {
                logger.debug("Created: "+entity);
            }
            return entity;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create class "+clazz, e);
        }
    }

    /**
     * Default <code>Entity</code> creator.
     * 
     * @param requiredOperator
     * @param uniqueAlias
     */
    public static Entity entityFactory(Operator requiredOperator, String uniqueAlias) {
        return internalEntityFactory(Entity.class, requiredOperator, uniqueAlias);
    }

    /**
     * Default <code>Organization</code> creator.
     * 
     * @param requiredOperator
     * @param uniqueAlias
     * @param businessName
     */
    public static Organization organizationFactory(Operator requiredOperator, String uniqueAlias, String businessName) {
        Organization organization = (Organization) internalEntityFactory(Organization.class, requiredOperator, uniqueAlias);
        organization.setBusinessName(businessName);
        
        return organization;
    }

    /**
     * Default <code>Individual</code> creator.
     * Makes alias same as the <code>Identity</code> principal
     * 
     * @param requiredOperator
     * @param requiredIentity
     */
    public static Individual individualFactory(Operator requiredOperator, Identity requiredIentity) {
        assertNotNull(requiredIentity);
        Individual individual = (Individual) internalEntityFactory(Individual.class, requiredOperator, requiredIentity.getPrincipal());
        individual.setIdentity(requiredIentity);
        
        return individual;
    }

}
