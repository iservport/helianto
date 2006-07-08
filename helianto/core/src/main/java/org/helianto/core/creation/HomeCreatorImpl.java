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


import java.util.Date;

import org.helianto.core.Home;
import org.helianto.core.MailAccessData;
import org.helianto.core.MailTransportData;

/**
 * Default implementation for the <code>HomeCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class HomeCreatorImpl implements HomeCreator {
    
    public Home homeFactory(String homeName) {
        java.util.Locale javaLocale = java.util.Locale.getDefault();
        return homeFactory(homeName, javaLocale.getLanguage(), javaLocale.getCountry());
    }
    
    public Home homeFactory(String homeName, String language, String country) {
        Home home = new Home();
        home.setHttpAddress("");
        home.setHomeName(homeName);
        home.setHomeDesc("");
        home.setLanguage(language);
        home.setCountry(country);
        home.setAdded(new Date());
        return home;
    }
    
    public MailTransportData mailTransportDataFactory(String host, String user, String password) {
        MailTransportData mailTransportData = new MailTransportData();
        mailTransportData.setSmtpHost(host);
        mailTransportData.setSmtpUser(user);
        mailTransportData.setSmtpPassword(password);
        return mailTransportData;
    }
    
    public MailAccessData mailAccessDataFactory(String host, String user, String password) {
        MailAccessData mailAccessData = new MailAccessData();
        mailAccessData.setHost(host);
        mailAccessData.setUser(user);
        mailAccessData.setPassword(password);
        return mailAccessData;
    }
    
}
