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

package org.helianto.user.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Log of main actions, including user actions, as
 * login, logout, and so forth.
 *
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_log")
public class EventLog implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(length=36)
    private String logId;
    
    @JsonIgnore 
    @ManyToOne
    @JoinColumn(name="userId", nullable=true)
    private User user;
    
    @Transient
    private Integer userId = 0;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEvent;
    
    @Column(length=20)
    private String eventType;
    
    @Lob
    private String contentAsString = "";

    /** 
     * Default constructor.
     */
    public EventLog() {
    	super();
    	setLogId(UUID.randomUUID().toString());
    	setLastEvent(new Date());
    }

    /**
     * Key constructor.
     * 
     * @param user
     * @param lastEvent
     */
    public EventLog(User user, Date lastEvent) {
    	this(user, lastEvent, "LOGIN");
    }

    /**
     * Event type constructor.
     * 
     * @param eventType
     */
    public EventLog(String eventType) {
    	this();
    	setEventType(eventType);
    }

    /**
     * User constructor.
     * 
     * @param user
     * @param eventType
     */
    public EventLog(User user, String eventType) {
    	this(eventType);
    	setUser(user);
    }

    /**
     * Full constructor.
     * 
     * @param user
     * @param lastEvent
     * @param eventType
     */
    public EventLog(User user, Date lastEvent, String eventType) {
    	user.setLastEvent(lastEvent);
        setUser(user);
        setLastEvent(lastEvent);
        setEventType(eventType);
    }

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Log UUID.
     */
    public String getLogId() {
		return logId;
	}
    public void setLogId(String logId) {
		this.logId = logId;
	}
    
    /**
     * User.
     */
    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * <<Transient>> user id.
     */
    public Integer getUserId() {
		return userId;
	}
    public void setUserId(Integer userId) {
		this.userId = userId;
	}

    /**
     * Last event.
     */
    public Date getLastEvent() {
        return this.lastEvent;
    }
    public void setLastEvent(Date lastEvent) {
        this.lastEvent = lastEvent;
    }

    /**
     * Event type.
     */
    public String getEventType() {
        return this.eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    /**
     * Content as string.
     */
    public String getContentAsString() {
		return contentAsString;
	}
    public void setContentAsString(String contentAsString) {
		this.contentAsString = contentAsString;
	}
    
    /**
     * Merger.
     * 
     * @param command
     */
    public EventLog merge(EventLog command) {
    	setEventType(command.getEventType());
    	setContentAsString(command.getContentAsString());
    	return this;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("user").append("='").append(getUser()).append("' ");
        buffer.append("lastEvent").append("='").append(getLastEvent()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof EventLog) ) return false;
         EventLog castOther = (EventLog) other; 
         
         return ((this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ))
             && ((this.getLastEvent()==castOther.getLastEvent()) || ( this.getLastEvent()!=null && castOther.getLastEvent()!=null && this.getLastEvent().equals(castOther.getLastEvent()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + ( getLastEvent() == null ? 0 : this.getLastEvent().hashCode() );
         return result;
   }   

}
