package org.helianto.core;
// Generated 09/12/2006 14:43:19 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Private Key.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 			
 * 		
 */
public class PrivateKey  implements java.io.Serializable {

    // Fields    

     private int id;
     private Credential credential;
     private String privateKey;

     // Constructors

    /** default constructor */
    public PrivateKey() {
    }

	/** minimal constructor */
    public PrivateKey(Credential credential) {
        this.credential = credential;
    }
    /** full constructor */
    public PrivateKey(Credential credential, String privateKey) {
       this.credential = credential;
       this.privateKey = privateKey;
    }
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Credential getCredential() {
        return this.credential;
    }
    
    public void setCredential(Credential credential) {
        this.credential = credential;
    }
    public String getPrivateKey() {
        return this.privateKey;
    }
    
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("credential").append("='").append(getCredential()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrivateKey) ) return false;
		 PrivateKey castOther = ( PrivateKey ) other; 
         
		 return ( (this.getCredential()==castOther.getCredential()) || ( this.getCredential()!=null && castOther.getCredential()!=null && this.getCredential().equals(castOther.getCredential()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getCredential() == null ? 0 : this.getCredential().hashCode() );
         
         return result;
   }   


}


