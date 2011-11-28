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


package org.helianto.message.service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.helianto.message.mail.compose.MailMessageComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Default <code>MessageMgr</code> interface implementation.
 *  
 * @author Mauricio Fernandes de Castro
 */
@Service("messageMgr")
public class MessageMgrImpl implements MessageMgr {

    public void send(String recipient, String sender, String subject, String htmlMessageBody) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(recipient);
			helper.setFrom(sender);
			helper.setSubject(subject);
			helper.setText(htmlMessageBody, true);
			mailSender.send(message);
			logger.info("Sent passwordConfirmation to {}", recipient);
		} catch (MessagingException e) {
			throw new RuntimeException("Failed to send message.", e);
		}
    }
    
    @Scheduled(fixedRate=3600000) // one hour
    public void heartBeat() {
    	send("mauricio@iservport.com", "mauricio@iservport.com", "Execution control test", "<html><body><p>Running.</p></body></html>");
    }
    
    // collabs

    private MailMessageComposer mailMessageComposer;
    private JavaMailSender mailSender;
    
    @Resource(name="basicMailMessageComposer")
    public void setMailMessageComposer(MailMessageComposer mailMessageComposer) {
        this.mailMessageComposer = mailMessageComposer;
    }

    @Resource(name="mailSender")
    public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
    private final Logger logger = LoggerFactory.getLogger(MessageMgrImpl.class);

}
