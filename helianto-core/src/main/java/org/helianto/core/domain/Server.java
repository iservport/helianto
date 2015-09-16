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

package org.helianto.core.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.ActivityState;
import org.helianto.core.def.Encription;
import org.helianto.core.def.ServerType;
import org.helianto.core.domain.type.RootEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * A domain object to represent available servers to an <code>Operator</code>.
 * 
 * <p>
 * Servers are the source for comunication between the <code>Operator</code> controlled entities and
 * recipients (e-mail, etc). They hold the information necessary to connect to 
 * external hosts. 
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
@javax.persistence.Entity
@Table(name="core_serv",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "serverName"})}
)
public class Server  
	implements RootEntity 
{

	private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonBackReference("operator")
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    private Operator operator;
    
    @Column(length=20)
    private String serverName;
    
    @Column(length=64)
    private String serverHostAddress;
    
    private int serverPort;
    
    @Column(length=64)
    private String serverDesc;
    
    private char serverType;
    
    private byte priority;
    
    private char serverState;
    
    private char requiredEncription;
    
    @JsonBackReference("credential")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="credentialId", nullable=true)
    private Credential credential;

    /**
     * Default constructor.
     */
    public Server() {
    	setServerHostAddress("");
    	setServerPort(-1);
    	setServerDesc("");
    	setServerType(ServerType.SMTP_SERVER.getValue());
        setPriority((byte) 1);
        setServerStateAsEnum(ActivityState.ACTIVE);
        setRequiredEncriptionAsEnum(Encription.PLAIN_PASSWORD);
    }

    /**
     * Key constructor.
     * 
     * @param operator
     * @param serverName
     */
    public Server(Operator operator, String serverName) {
    	this();
    	setOperator(operator);
    	setServerName(serverName);
    }
    
    /**
     * Credential constructor.
     * 
     * @param operator
     * @param serverName
     * @param serverType
     * @param credential
     */
    public Server(Operator operator, String serverName, ServerType serverType, Credential credential) {
    	this(operator, serverName);
        setServerType(serverType.getValue());
        setCredential(credential);
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
     * Operator.
     */
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
//    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
    
    /**
     * Server name.
     */
    public String getServerName() {
        return this.serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    /**
     * Server host address.
     */
    public String getServerHostAddress() {
        return this.serverHostAddress;
    }
    public void setServerHostAddress(String serverHostAddress) {
        this.serverHostAddress = serverHostAddress;
    }
    
    /**
     * Server port.
     */
    public int getServerPort() {
        return this.serverPort;
    }
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    /**
     * Server description.
     */
    public String getServerDesc() {
        return this.serverDesc;
    }
    public void setServerDesc(String serverDesc) {
        this.serverDesc = serverDesc;
    }
    
    /**
     * Server type.
     */
    public char getServerType() {
        return this.serverType;
    }
    public void setServerType(char serverType) {
        this.serverType = serverType;
    }
    
    /**
     * Priority.
     */
    public byte getPriority() {
        return this.priority;
    }
    public void setPriority(byte priority) {
        this.priority = priority;
    }
    
    /**
     * Server state.
     */
    public char getServerState() {
        return this.serverState;
    }
    public void setServerState(char serverState) {
        this.serverState = serverState;
    }
    public void setServerStateAsEnum(ActivityState serverState) {
        this.serverState = serverState.getValue();
    }
    
    /**
     * Required encription.
     */
    public char getRequiredEncription() {
        return this.requiredEncription;
    }
    public void setRequiredEncription(char requiredEncription) {
        this.requiredEncription = requiredEncription;
    }
    public void setRequiredEncriptionAsEnum(Encription requiredEncription) {
        this.requiredEncription = requiredEncription.getValue();
    }
    
    /**
     * Credential.
     */
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


