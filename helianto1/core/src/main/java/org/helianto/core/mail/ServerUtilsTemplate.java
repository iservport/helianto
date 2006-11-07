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
 * An interface to retrieve <code>Server</code>s from a
 * list.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ServerUtilsTemplate {

    /**
     * Selects the first available transport <code>Server</code>.
     * 
     * @param serverList
     */
    public Server selectFirstAvailableMailTransportServer(List<Server> serverList);
    
    /**
     * Selects the first available access <code>Server</code>.
     * 
     * @param serverList
     */
    public Server selectFirstAvailableMailAccessServer(List<Server> serverList);
    
}
