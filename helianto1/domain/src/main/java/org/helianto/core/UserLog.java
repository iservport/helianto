package org.helianto.core;
// Generated 07/12/2006 07:44:31 by Hibernate Tools 3.2.0.beta8


import java.util.Date;

/**
 * 				
 * <p>
 * A class to hold user successful logins.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class UserLog  implements java.io.Serializable {

    // Fields    

     private long id;
     private User user;
     private Date lastEvent;
     private byte eventType;

     // Constructors

    /** default constructor */
    public UserLog() {
    }

    /** full constructor */
    public UserLog(User user, Date lastEvent, byte eventType) {
       this.user = user;
       this.lastEvent = lastEvent;
       this.eventType = eventType;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getLastEvent() {
        return this.lastEvent;
    }
    
    public void setLastEvent(Date lastEvent) {
        this.lastEvent = lastEvent;
    }
    public byte getEventType() {
        return this.eventType;
    }
    
    public void setEventType(byte eventType) {
        this.eventType = eventType;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserLog) ) return false;
		 UserLog castOther = ( UserLog ) other; 
         
		 return ( (this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ) )
 && ( (this.getLastEvent()==castOther.getLastEvent()) || ( this.getLastEvent()!=null && castOther.getLastEvent()!=null && this.getLastEvent().equals(castOther.getLastEvent()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + ( getLastEvent() == null ? 0 : this.getLastEvent().hashCode() );
         
         return result;
   }   


}


