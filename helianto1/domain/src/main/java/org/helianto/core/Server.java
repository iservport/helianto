package org.helianto.core;
// Generated 23/10/2006 19:51:20 by Hibernate Tools 3.1.0.beta5



/**
 * 				
 * <p>
 * A domain object to represent available servers to an <code>Operator</code>.
 * </p>
 * <p>
 * Servers are the source for comunication between the <code>Operator</code> controlled entities and
 * recipients (e-mail, etc). They hold the information necessary to connect to 
 * external hosts. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Server  implements java.io.Serializable {

    // Fields    

     private int id;
     private Operator operator;
     private String serverName;
     private String serverHostAddress;
     private String serverDesc;
     private char serverType;
     private byte priority;
     private char serverState;
     private byte requiredEncription;
     private Credential credential;

     // Constructors

    /** default constructor */
    public Server() {
    }

	/** minimal constructor */
    public Server(Operator operator, String serverName, char serverType, byte priority, char serverState, byte requiredEncription) {
        this.operator = operator;
        this.serverName = serverName;
        this.serverType = serverType;
        this.priority = priority;
        this.serverState = serverState;
        this.requiredEncription = requiredEncription;
    }
    /** full constructor */
    public Server(Operator operator, String serverName, String serverHostAddress, String serverDesc, char serverType, byte priority, char serverState, byte requiredEncription, Credential credential) {
       this.operator = operator;
       this.serverName = serverName;
       this.serverHostAddress = serverHostAddress;
       this.serverDesc = serverDesc;
       this.serverType = serverType;
       this.priority = priority;
       this.serverState = serverState;
       this.requiredEncription = requiredEncription;
       this.credential = credential;
    }
    
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Operator getOperator() {
        return this.operator;
    }
    
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    public String getServerName() {
        return this.serverName;
    }
    
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public String getServerHostAddress() {
        return this.serverHostAddress;
    }
    
    public void setServerHostAddress(String serverHostAddress) {
        this.serverHostAddress = serverHostAddress;
    }
    public String getServerDesc() {
        return this.serverDesc;
    }
    
    public void setServerDesc(String serverDesc) {
        this.serverDesc = serverDesc;
    }
    public char getServerType() {
        return this.serverType;
    }
    
    public void setServerType(char serverType) {
        this.serverType = serverType;
    }
    public byte getPriority() {
        return this.priority;
    }
    
    public void setPriority(byte priority) {
        this.priority = priority;
    }
    public char getServerState() {
        return this.serverState;
    }
    
    public void setServerState(char serverState) {
        this.serverState = serverState;
    }
    public byte getRequiredEncription() {
        return this.requiredEncription;
    }
    
    public void setRequiredEncription(byte requiredEncription) {
        this.requiredEncription = requiredEncription;
    }
    public Credential getCredential() {
        return this.credential;
    }
    
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("operator").append("='").append(getOperator()).append("' ");			
      buffer.append("serverName").append("='").append(getServerName()).append("' ");			
      buffer.append("credential").append("='").append(getCredential()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Server) ) return false;
		 Server castOther = ( Server ) other; 
         
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
 && ( (this.getServerName()==castOther.getServerName()) || ( this.getServerName()!=null && castOther.getServerName()!=null && this.getServerName().equals(castOther.getServerName()) ) )
 && ( (this.getCredential()==castOther.getCredential()) || ( this.getCredential()!=null && castOther.getCredential()!=null && this.getCredential().equals(castOther.getCredential()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getServerName() == null ? 0 : this.getServerName().hashCode() );
         
         
         
         
         
         
         result = 37 * result + ( getCredential() == null ? 0 : this.getCredential().hashCode() );
         return result;
   }   


}


