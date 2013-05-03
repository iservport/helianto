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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.Identity;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.message.MessageAdapter;
import org.helianto.message.MessageMgr;
import org.helianto.message.MessageSender;
import org.helianto.message.domain.NotificationEvent;
import org.helianto.message.filter.NotificationEventFormFilterAdapter;
import org.helianto.message.form.NotificationEventForm;
import org.helianto.message.sender.SendGridMessageAdapter;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
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
    
    @Scheduled(cron="0 0 * * * *") // (fixedRate=3600000) // one hour
    public void findHourly() {
    	logger.info("Hourly scheduler triggered at {}.", new Date());
    	MessageAdapter<String> hourlyMessage = new SendGridMessageAdapter();
    	String query = "select distinct child from User child " +
    			"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) like 'admin' ";
    	Collection<UserGroup> adminList = userGroupDao.find(query, new Object[0]);
    	Map<Identity, List<UserGroup>> adminMap = new HashMap<Identity, List<UserGroup>>();
    	for (UserGroup admin: adminList) {
    		Identity identity = ((User) admin).getIdentity();
    		if (adminMap.containsKey(identity)) {
    			adminMap.get(identity).add(admin);
    		}
    		else {
    			if (identity.isAddressable()) {
        			List<UserGroup> userList = new ArrayList<UserGroup>();
        			userList.add(admin);
        			adminMap.put(identity, userList);
    			}
    		}
    	}
    	hourlyMessage.setTo(adminMap.keySet());
       	hourlyMessage.setFrom(adminMap.keySet().iterator().next());
      	hourlyMessage.setSubject("System status");
      	hourlyMessage.setText("System is running");
      	hourlyMessage.setHtml("<html><body><p>Running.</p></body></html>");
      	send(hourlyMessage);
    }
    
    public List<NotificationEvent> findNotificationEvents(NotificationEventForm form) {
    	Filter filter = new NotificationEventFormFilterAdapter(form);
    	List<NotificationEvent> notificationEventList = (List<NotificationEvent>) notificationEventDao.find(filter);
		return notificationEventList;
    	
    }
    
    public NotificationEvent storeNotificationEvent(NotificationEvent target) {
    	sequenceMgr.validateInternalNumber(target);
    	notificationEventDao.saveOrUpdate(target);
    	return target;
    }
    
    // collabs

    private MessageSender messageSender;
    private FilterDao<UserGroup> userGroupDao;
    private FilterDao<NotificationEvent> notificationEventDao;
    private SequenceMgr sequenceMgr;
    
    @Resource
    public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
    
    @Resource(name="userGroupDao")
    public void setUserGroupDao(FilterDao<UserGroup> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
    
    @Resource(name="notificationEventDao")
    public void setNotificationEventDao(FilterDao<NotificationEvent> notificationEventDao) {
		this.notificationEventDao = notificationEventDao;
	}
    
    @Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
    
    private static final Logger logger = LoggerFactory.getLogger(MessageMgrImpl.class);
    
}
