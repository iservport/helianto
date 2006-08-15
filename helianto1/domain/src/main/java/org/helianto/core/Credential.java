package org.helianto.core;
// Generated 15/08/2006 11:35:57 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 			
 * <p>
 * Provides <code>Identity</code> with authentication information. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */

public class Credential extends org.helianto.core.type.AbstractCredential implements java.io.Serializable {


    // Fields    

     private long id;
     private Identity identity;
     private String password;
     private char credentialState;
     private Date lastModified;
     private Date expired;
     private byte encription;


    // Constructors

    /** default constructor */
    public Credential() {
    }

	/** minimal constructor */
    public Credential(Identity identity, char credentialState, byte encription) {
        this.identity = identity;
        this.credentialState = credentialState;
        this.encription = encription;
    }
    
    /** full constructor */
    public Credential(Identity identity, String password, char credentialState, Date lastModified, Date expired, byte encription) {
        this.identity = identity;
        this.password = password;
        this.credentialState = credentialState;
        this.lastModified = lastModified;
        this.expired = expired;
        this.encription = encription;
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

    public byte getEncription() {
        return this.encription;
    }
    
    public void setEncription(byte encription) {
        this.encription = encription;
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
