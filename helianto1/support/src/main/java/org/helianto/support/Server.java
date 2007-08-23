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

package org.helianto.support;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Encription;
import org.helianto.core.Identity;
import org.helianto.core.Operator;

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
 */
@javax.persistence.Entity
@Table(name="supp_server",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "serverName"})}
)
public class Server implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private String serverName;
    private String serverHostAddress;
    private int serverPort;
    private String serverDesc;
    private char serverType;
    private int priority;
    private char serverState;
    private char requiredEncription;
    private Credential credential;

    /** default constructor */
    public Server() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Operator getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    /**
     * Operator setter.
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * ServerName getter.
     */
    @Column(length=20)
    public String getServerName() {
        return this.serverName;
    }
    /**
     * ServerName setter.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * ServerHostAddress getter.
     */
    @Column(length=64)
    public String getServerHostAddress() {
        return this.serverHostAddress;
    }
    /**
     * ServerHostAddress setter.
     */
    public void setServerHostAddress(String serverHostAddress) {
        this.serverHostAddress = serverHostAddress;
    }

    /**
     * ServerPort getter.
     */
    public int getServerPort() {
        return this.serverPort;
    }
    /**
     * ServerPort setter.
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * ServerDesc getter.
     */
    @Column(length=64)
    public String getServerDesc() {
        return this.serverDesc;
    }
    /**
     * ServerDesc setter.
     */
    public void setServerDesc(String serverDesc) {
        this.serverDesc = serverDesc;
    }

    /**
     * ServerType getter.
     */
    public char getServerType() {
        return this.serverType;
    }
    /**
     * ServerType setter.
     */
    public void setServerType(char serverType) {
        this.serverType = serverType;
    }

    /**
     * Priority getter.
     */
    public int getPriority() {
        return this.priority;
    }
    /**
     * Priority setter.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * ServerState getter.
     */
    public char getServerState() {
        return this.serverState;
    }
    /**
     * ServerState setter.
     */
    public void setServerState(char serverState) {
        this.serverState = serverState;
    }

    /**
     * RequiredEncription getter.
     */
    public char getRequiredEncription() {
        return this.requiredEncription;
    }
    /**
     * RequiredEncription setter.
     */
    public void setRequiredEncription(char requiredEncription) {
        this.requiredEncription = requiredEncription;
    }

    /**
     * Credential getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="credentialId", nullable=true)
    public Credential getCredential() {
        return this.credential;
    }
    /**
     * Credential setter.
     */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    /**
     * <code>Server</code> factory.
     * 
     * Set ServerType to ServerType.SMTP_SERVER by default.
     * 
     * @param operator
     * @param serverName
     */
    public static Server serverFactory(Operator operator, String serverName) {
        return serverFactory(operator, serverName, ServerType.SMTP_SERVER);
    }


    /**
     * <code>Server</code> factory.
     * 
     * Set ServerState to ActivityState.ACTIVE and
     * RequiredEncription to Encription.PLAIN_PASSWORD by default.
     * 
     * @param operator
     * @param serverName
     * @param serverType
     *  
     * @see ServerType
     */
    public static Server serverFactory(Operator operator, String serverName, ServerType serverType) {
        Identity identity = Identity.identityFactory("", "");
        Credential credential = Credential.credentialFactory(identity, "");
        credential.getIdentity().setPrincipal(serverName);
        return serverFactory(operator, serverName, serverType, credential);
    }

    /**
     * <code>Server</code> factory.
     * 
     * Set ServerState to ActivityState.ACTIVE and
     * RequiredEncription to Encription.PLAIN_PASSWORD by default.
     * 
     * @param operator
     * @param serverName
     * @param serverType 
     * @param credential 
     * 
     * @see ServerType
     */
    public static Server serverFactory(Operator operator, String serverName, ServerType serverType, Credential credential) {
        Server server = new Server();

        server.setOperator(operator);
        server.setServerName(serverName);
        server.setServerHostAddress("");
        server.setServerDesc("");
        server.setServerType(serverType.getValue());
        server.setCredential(credential);
        setServerDefaults(server);
        return server;
    }
    
    /**
     * Set port to -1, priority to 1, server state to ActivityState.ACTIVE and
     * required encription to Encription.PLAIN_PASSWORD.
     * 
     * @param server
     * 
     * @see ActivityState
     * @see Encription
     */
    public static void setServerDefaults(Server server) {
        server.setServerPort(-1);
        server.setPriority(1);
        server.setServerState(ActivityState.ACTIVE.getValue());
        server.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
    }

    /**
     * <code>Server</code> natural id query.
     */
    @Transient
    public static String getServerNaturalIdQueryString() {
        return "select server from Server server where server.operator = ? and server.serverName = ? ";
    }

    /**
     * <code>Server</code> operator and type query.
     */
    @Transient
    public static String getServerOperatorAndTypeQueryString() {
        return "select server from Server server where server.operator = ? and server.serverType = ? order by server.priority ";
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

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Server) ) return false;
         Server castOther = (Server) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getServerName()==castOther.getServerName()) || ( this.getServerName()!=null && castOther.getServerName()!=null && this.getServerName().equals(castOther.getServerName()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getServerName() == null ? 0 : this.getServerName().hashCode() );
         return result;
   }   

}
