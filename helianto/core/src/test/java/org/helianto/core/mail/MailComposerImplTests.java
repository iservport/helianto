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

import org.helianto.core.Credential;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.core.service.CoreMgrImpl;

public class MailComposerImplTests extends AbstractIntegrationTest {

    protected MailComposer mailComposer;
    
    public void setMailComposer(MailComposer mailComposer) {
        this.mailComposer = mailComposer;
    }

    protected CoreMgrImpl coreMgr;

    public void setCoreMgr(CoreMgrImpl coreMgr) {
        this.coreMgr = coreMgr;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/dataSource.xml", 
                "deploy/sessionFactory-test.xml",
                "deploy/support.xml",
                "deploy/coreMgr.xml"};
    }
    
    public void test() {
        Credential cred = coreMgr.credentialFactory();
        String subject = mailComposer
            .composeRegistrationNotificationSubject("PREFIX");
        logger.info("SUBJECT: "+subject);
        assertEquals("1.0", "PREFIX", subject.substring(0, "PREFIX".length()));
        String body = mailComposer
            .composeRegistrationNotification(cred, "ADDRESS");
        logger.info("BODY: "+body);
        assertTrue("1.1", body.indexOf("ADDRESS")>0);
    }

}
