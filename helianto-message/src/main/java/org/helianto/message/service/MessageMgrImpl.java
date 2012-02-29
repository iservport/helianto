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

import org.helianto.message.MessageAdapter;
import org.helianto.message.MessageSender;
import org.springframework.stereotype.Service;

/**
 * Default <code>MessageMgr</code> implementation.
 *  
 * @author Mauricio Fernandes de Castro
 */
@Service("messageMgr")
public class MessageMgrImpl implements MessageMgr {

//    public void send(MessageAdapter message) {
//		JavaMailMessage javaMailMessage = new JavaMailMessage(javaMailSender.createMimeMessage());
//		javaMailSender.send(javaMailMessage.getMimeMessage());
//		logger.info("Sent {}.", javaMailMessage.getSubject());
//    }
    
    public void send(MessageAdapter<?> message) {
    	messageSender.prepareMessage(message);
		messageSender.sendMessage(message);
    }
    
//    @Scheduled(fixedRate=3600000) // one hour
    public void findHourly() {
//    	send("mauricio@iservport.com", "mauricio@iservport.com", "Execution control test", "<html><body><p>Running.</p></body></html>");
    }
    
    // collabs

    private MessageSender messageSender;
    
    @Resource
    public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
    
}
