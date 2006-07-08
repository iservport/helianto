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

import org.helianto.core.Home;
import org.helianto.core.MailAccessData;
import org.helianto.core.MailTransportData;

/**
 * A factory method pattern interface to <code>Home</code>
 * related domain objects.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface HomeCreator {

    public Home homeFactory(String homeName);

    public Home homeFactory(String homeName, String language, String country);

    public MailTransportData mailTransportDataFactory(String host, String user,
            String password);

    public MailAccessData mailAccessDataFactory(String host, String user,
            String password);

}