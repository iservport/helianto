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

/**
 * The <code>ConfigurableMailSender</code> factory interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ConfigurableMailSenderFactory {
    
    /**
     * Creates a new <code>ConfigurableMailSender</code>.
     * 
     * @param serverList
     */
    public ConfigurableMailSender create(List<Server> serverList);
    
    /**
     * A strategy to handle the internals of the supplied <code>Server</code>.
     * Subclasses can override this to restrict how the mail session 
     * properties are set up.
     * 
     * @param serverUtilsTemplate
     */
    public void setServerUtilsTemplate(ServerUtilsTemplate serverUtilsTemplate);

}
