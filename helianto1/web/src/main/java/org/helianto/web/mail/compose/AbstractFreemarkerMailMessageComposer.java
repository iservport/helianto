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

package org.helianto.web.mail.compose;

import org.helianto.core.mail.compose.AbstractMailMessageComposer;
import org.helianto.core.mail.compose.MailForm;
import org.springframework.mail.javamail.MimeMessagePreparator;

import freemarker.template.Configuration;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFreemarkerMailMessageComposer extends
        AbstractMailMessageComposer {

    public MimeMessagePreparator composeMessage(String key, MailForm mailForm) {
        //TODO use the factory
        Configuration configuration = new Configuration();

//        TemplateMailMessageDecorator templateMailMessageDecorator = 
//            new TemplateMailMessageDecorator(
//                new DecoratedPreparator(null, null), configuration);
        //TODO write the template
        return null;
    }

}
