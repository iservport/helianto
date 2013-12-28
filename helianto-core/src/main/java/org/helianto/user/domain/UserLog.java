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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.EventType;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * Represent the memory of main user actions, as
 * login, logout, and so forth.
 *
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_userlog",
    uniqueConstraints = {@UniqueConstraint(columnNames={"userId", "lastEvent"})}
)
public class UserLog implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="userId", nullable=true)
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEvent;
    
    private int eventType;

    /** 
     * Default constructor.
     */
    public UserLog() {
    	super();
    }

    /**
     * Key constructor.
     * 
     * @param user
     * @param lastEvent
     */
    public UserLog(User user, Date lastEvent) {
    	this(user, lastEvent, EventType.LOGIN);
    }

    /**
     * Full constructor.
     * 
     * @param user
     * @param lastEvent
     * @param eventType
     */
    public UserLog(User user, Date lastEvent, EventType eventType) {
    	user.setLastEvent(lastEvent);
        setUser(user);
        setLastEvent(lastEvent);
        setEventTypeAsEnum(eventType);
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
     * User.
     */
    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
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
    public int getEventType() {
        return this.eventType;
    }
    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
    public void setEventTypeAsEnum(EventType eventType) {
        this.eventType = eventType.getValue();
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
         if ( !(other instanceof UserLog) ) return false;
         UserLog castOther = (UserLog) other; 
         
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
