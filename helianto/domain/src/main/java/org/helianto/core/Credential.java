package org.helianto.core;
// Generated 03/07/2006 15:46:51 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 			
 * <p>
 * Provides <code>Identity</code> with authentication information. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 * 			
 * 		
 */

public class Credential extends AbstractCredential implements java.io.Serializable {


    // Fields    

     private long id;
     private Identity identity;
     private String password;
     private char credentialState;
     private Date lastModified;
     private Date expired;


    // Constructors

    /** default constructor */
    public Credential() {
    }

	/** minimal constructor */
    public Credential(Identity identity, char credentialState) {
        this.identity = identity;
        this.credentialState = credentialState;
    }
    
    /** full constructor */
    public Credential(Identity identity, String password, char credentialState, Date lastModified, Date expired) {
        this.identity = identity;
        this.password = password;
        this.credentialState = credentialState;
        this.lastModified = lastModified;
        this.expired = expired;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Identity getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public char getCredentialState() {
        return this.credentialState;
    }
    
    public void setCredentialState(char credentialState) {
        this.credentialState = credentialState;
    }

    public Date getLastModified() {
        return this.lastModified;
    }
    
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getExpired() {
        return this.expired;
    }
    
    public void setExpired(Date expired) {
        this.expired = expired;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("identity").append("='").append(getIdentity()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Credential) ) return false;
		 Credential castOther = ( Credential ) other; 
         
		 return ( (this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         
         
         
         
         return result;
   }   





}
