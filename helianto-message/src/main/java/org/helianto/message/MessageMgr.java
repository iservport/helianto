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


package org.helianto.message;

import java.util.List;

import org.helianto.message.domain.NotificationEvent;
import org.helianto.message.form.NotificationEventForm;



/**
 * Message service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface MessageMgr {

    /**
     * Send a message.
     * 
     * @param messageAdapter
     */
    void send(MessageAdapter<?> messageAdapter);
    
    /**
     * Notify hourly.
     */
    void findHourly();
    
    /**
     * Find notification events.
     * 
     * @param form
     */
    List<NotificationEvent> findNotificationEvents(NotificationEventForm form);
    
    /**
     * Store a notification event.
     * 
     * @param target
     */
    NotificationEvent storeNotificationEvent(NotificationEvent target);

}