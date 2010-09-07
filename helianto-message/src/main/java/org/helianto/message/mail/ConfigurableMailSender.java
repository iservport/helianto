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

package org.helianto.message.mail;

import org.helianto.core.Server;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * An extension to the <code>JavaMailSender</code> interface to
 * allow configuration via the <code>Server</code>
 * abstraction.
 * 
 * Required by <code>ConfigurableMailSenderFactory</code> to
 * create instances after an <code>Operator</code> server list
 * is provided. 
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public interface ConfigurableMailSender extends JavaMailSender {
    
    /**
     * The transport server.
     * 
     * @param transportServer
     */
    public void setTransportServer(Server transportServer);

    /**
     * The access server.
     * 
     * @param accessServer
     */
    public void setAccessServer(Server accessServer);
    
    /**
     * Configures this instace.
     *   
     * @throws IllegalStateException
     */
    public void init() throws IllegalStateException;

}
