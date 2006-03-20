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

package org.helianto.core;


import java.util.Date;
import org.helianto.core.service.AbstractGenericService;

/**
 * Default implementation for the <code>HomeCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class HomeCreatorImpl extends AbstractGenericService implements HomeCreator {
    
    /* (non-Javadoc)
     * @see org.helianto.core.HomeCreator#mailTransportDataFactory(java.lang.String, java.lang.String, java.lang.String)
     */
    public MailTransportData mailTransportDataFactory(String host, String user, String password) {
        MailTransportData mailTransportData = new MailTransportData();
        mailTransportData.setSmtpHost(host);
        mailTransportData.setSmtpUser(user);
        mailTransportData.setSmtpPassword(password);
        return mailTransportData;
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.HomeCreator#mailAccessDataFactory(java.lang.String, java.lang.String, java.lang.String)
     */
    public MailAccessData mailAccessDataFactory(String host, String user, String password) {
        MailAccessData mailAccessData = new MailAccessData();
        mailAccessData.setHost(host);
        mailAccessData.setUser(user);
        mailAccessData.setPassword(password);
        return mailAccessData;
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.HomeCreator#homeFactory()
     */
    public Home homeFactory() {
        return homeFactory("");
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.HomeCreator#homeFactory(java.lang.String)
     */
    public Home homeFactory(String homeName) {
        java.util.Locale javaLocale = java.util.Locale.getDefault();
        return homeFactory(homeName, javaLocale.getLanguage(), javaLocale.getCountry());
    }
    
    /* (non-Javadoc)
     * @see org.helianto.core.HomeCreator#homeFactory(java.lang.String, java.lang.String, java.lang.String)
     */
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
    
}
