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

package org.helianto.core.mail;

import java.util.List;

import org.helianto.core.Server;
import org.helianto.core.type.ServerType;

/**
 * A factory bean to <code>ConfigurableMailSender</code>
 * instances. 
 * 
 * Holds a reference to <code>ServerUtilsTemplate</code> as
 * a strategy to select the appropriate <code>Server</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ConfigurableMailSenderFactoryImpl implements ConfigurableMailSenderFactory {
    
    public ConfigurableMailSender create(List<Server> serverList) {
        ConfigurableMailSender configurableMailSender = 
            new ConfigurableMailSenderImpl();
        configurableMailSender.setTransportServer(
                getServerUtilsTemplate().selectFirstAvailableMailTransportServer(serverList));
        configurableMailSender.setAccessServer(
                getServerUtilsTemplate().selectFirstAvailableMailAccessServer(serverList));
        configurableMailSender.init();
        return configurableMailSender;
    }

    private ServerUtilsTemplate serverUtilsTemplate = new DefaultServerUtils();

    public ServerUtilsTemplate getServerUtilsTemplate() {
        return serverUtilsTemplate;
    }

    public void setServerUtilsTemplate(ServerUtilsTemplate serverUtilsTemplate) {
        this.serverUtilsTemplate = serverUtilsTemplate;
    }
    
    /**
     * Default implementation for <code>ServerUtilsTemplate</code>.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class DefaultServerUtils implements ServerUtilsTemplate {
        
        public Server selectFirstAvailableMailTransportServer(List<Server> serverList) {
            return selectServer(serverList, ServerType.SMTP_SERVER);
        }
        
        public Server selectFirstAvailableMailAccessServer(List<Server> serverList) {
            return selectServer(serverList, ServerType.POP3_SERVER);
        }
        
        protected Server selectServer(List<Server> serverList, ServerType serverType) {
            for (Server s : serverList) {
                if (s.getServerType() == serverType.getValue()) {
                    return s;
                }
            }
            return null;
        }

    }
    
}
