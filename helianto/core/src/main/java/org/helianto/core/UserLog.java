package org.helianto.core;
// Generated 18/04/2006 20:38:55 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * A class to hold user successful logins.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core7.hbm.xml,v 1.3 2006/03/20 16:11:40 iserv Exp $
 * 				
 * 		
 */

public class UserLog  implements java.io.Serializable {


    // Fields    

     private int id;
     private User user;
     private Date lastLogin;


    // Constructors

    /** default constructor */
    public UserLog() {
    }

    
    /** full constructor */
    public UserLog(User user, Date lastLogin) {
        this.user = user;
        this.lastLogin = lastLogin;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserLog) ) return false;
		 UserLog castOther = ( UserLog ) other; 
         
		 return ( (this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ) )
 && ( (this.getLastLogin()==castOther.getLastLogin()) || ( this.getLastLogin()!=null && castOther.getLastLogin()!=null && this.getLastLogin().equals(castOther.getLastLogin()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + ( getLastLogin() == null ? 0 : this.getLastLogin().hashCode() );
         return result;
   }   





}
