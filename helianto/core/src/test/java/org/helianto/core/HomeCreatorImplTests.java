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

import junit.framework.TestCase;

public class HomeCreatorImplTests extends TestCase {
    
    HomeCreatorImpl factory;
    
    public void setUp() {
        factory = new HomeCreatorImpl();
    }

    public void testMailTransportDataFactory() {
        MailTransportData mailTransportData = 
            factory.mailTransportDataFactory("host", "user", "password");
        assertEquals("host", mailTransportData.getSmtpHost());
        assertEquals("user", mailTransportData.getSmtpUser());
        assertEquals("password", mailTransportData.getSmtpPassword());
    }

    public void testMailAccessDataFactory() {
        MailAccessData mailAccessData = 
            factory.mailAccessDataFactory("host", "user", "password");
        assertEquals("host", mailAccessData.getHost());
        assertEquals("user", mailAccessData.getUser());
        assertEquals("password", mailAccessData.getPassword());
    }

    public void testHomeFactory() {
        Home home = 
            factory.homeFactory();
        assertEquals("", home.getHomeName());
    }

    public void testHomeFactoryString() {
        Home home = 
            factory.homeFactory("UNIQUE");
        assertEquals("UNIQUE", home.getHomeName());
    }

    public void testHomeFactoryStringStringString() {
        Home home = 
            factory.homeFactory("UNIQUE", "LANGUAGE", "COUNTRY");
        assertEquals("LANGUAGE", home.getLanguage());
        assertEquals("COUNTRY", home.getCountry());
        assertEquals("UNIQUE", home.getHomeName());
        assertTrue(home.getAdded().compareTo(new Date()) < 1000);
        assertEquals("", home.getHttpAddress());
        assertEquals("", home.getHomeDesc());
        assertNull(home.getParent());
        assertNull(home.getMailAccessData());
        assertNull(home.getMailTransportData());
    }

}
