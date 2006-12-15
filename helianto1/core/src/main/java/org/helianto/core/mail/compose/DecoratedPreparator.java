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

package org.helianto.core.mail.compose;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Extends Spring's <code>MimeMessagePreparator</code> abstraction
 * to accept a <code>MailForm</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DecoratedPreparator implements MimeMessagePreparator {

    protected MailForm mailForm;
    private StringBuilder body;

    /**
     * Constructor.
     * 
     * @param mailForm
     */
    public DecoratedPreparator(MailForm mailForm) {
        body = new StringBuilder();
        this.mailForm = mailForm;
    }

    protected DecoratedPreparator() {
        throw new IllegalStateException(
                "Missing required identities, use full constructor instead!");
    }
    
    /**
     * Subclasses can override this method to support 
     * custom mail content creation.
     */
    protected void compose() {
        body.append(mailForm.getContent());
    }

    /**
     * Reads the <code>MailForm</code> to set up the 
     * <code>MimeMessage</code>.
     */
    public void prepare(MimeMessage mimeMessage) {
        compose();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(mailForm.getRecipientIdentity().getPrincipal());
            helper.setReplyTo(mailForm.getOperator().getOperatorSourceMailAddress());
            helper.setFrom(mailForm.getOperator().getOperatorSourceMailAddress());
            helper.setSentDate(new Date());
            helper.setSubject(mailForm.getSubject());
            helper.setText(body.toString(), true);
        } catch (MessagingException e) {
            throw new IllegalStateException("Unable to preparate message", e);
        }
    }

    public StringBuilder getBody() {
        return body;
    }

	public void setMailForm(MailForm mailForm) {
		this.mailForm = mailForm;
	}

	public MailForm getMailForm() {
		return mailForm;
	}

}
