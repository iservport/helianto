package org.helianto.core;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8



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

	private static final long serialVersionUID = 1L;
	private int id;
    private Operator operator;
    private String serverName;
    private String serverHostAddress;
    private int serverPort;
    private String serverDesc;
    private char serverType;
    private byte priority;
    private char serverState;
    private char requiredEncription;
    private Credential credential;

     // Constructors

    /** default constructor */
    public Server() {
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
    public int getServerPort() {
        return this.serverPort;
    }
    
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
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
    public char getRequiredEncription() {
        return this.requiredEncription;
    }
    
    public void setRequiredEncription(char requiredEncription) {
        this.requiredEncription = requiredEncription;
    }
    public Credential getCredential() {
        return this.credential;
    }
    
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    /**
     * Default <code>Server</code> creator.
     * 
     * Set ServerState to ActivityState.ACTIVE and
     * RequiredEncription to Encription.PLAIN_PASSWORD by default.
     * 
     * @param requiredOperator
     * @param serverName
     * @param serverType if null, default is ServerType.SMTP_SERVER
     * @param credential if null, create one with serverName and empty password
     * 
     * @see ServerType
     * @see ActivityState
     * @see Encription
     */
    public static Server serverFactory(Operator requiredOperator, String serverName, ServerType serverType, Credential credential) {
        Server server = new Server();

        server.setOperator(requiredOperator);
        server.setServerName(serverName);
        server.setServerHostAddress("");
        server.setServerPort(-1);
        server.setServerDesc("");
        if (serverType==null) {
            server.setServerType(ServerType.SMTP_SERVER.getValue());
        } else {
            server.setServerType(serverType.getValue());
        }
        server.setPriority((byte) 1);
        server.setServerState(ActivityState.ACTIVE.getValue());
        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        if (credential==null) {
            Identity identity = Identity.identityFactory("", "");
            credential = Credential.credentialFactory(identity, "");
            credential.getIdentity().setPrincipal(serverName);
        } 
        server.setCredential(credential);
        return server;
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
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Server) ) return false;
		 Server castOther = ( Server ) other; 
         
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
             && ( (this.getServerName()==castOther.getServerName()) || ( this.getServerName()!=null && castOther.getServerName()!=null && this.getServerName().equals(castOther.getServerName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getServerName() == null ? 0 : this.getServerName().hashCode() );
         
         
         
         
         
         
         
         result = 37 * result + ( getCredential() == null ? 0 : this.getCredential().hashCode() );
         return result;
   }   


}


