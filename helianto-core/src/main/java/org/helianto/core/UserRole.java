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

package org.helianto.core;

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

/**
 * Roles represent the authorization given to access a service.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_userrole",
    uniqueConstraints = {@UniqueConstraint(columnNames={"userId", "serviceId", "serviceExtension"})}
)
public class UserRole  implements java.io.Serializable {

    /**
     * Factory method.
     * 
     * @param user
     */
    public static UserRole userRoleFactory(UserGroup user) {
        UserRole userRole = new UserRole();
        userRole.setUserGroup(user);
        return userRole;
    }
    
    /**
     * Factory method.
     * 
     * @param user
     * @param service
     * @param serviceExtension
     */
    public static UserRole userRoleFactory(UserGroup user, Service service, String serviceExtension) {
        UserRole userRole = UserRole.userRoleFactory(user);
        userRole.setService(service);
        userRole.setServiceExtension(serviceExtension);
        user.getRoles().add(userRole);
        return userRole;
    }
    
    private static final long serialVersionUID = 1L;
    private long id;
    private UserGroup userGroup;
    private Service service;
    private String serviceExtension;

    /** 
     * Empty constructor.
     */
    public UserRole() {
    	setServiceExtension("READ");
    }
   
    /** 
     * Service constructor.
     * 
     * @param userGroup
     * @param service
     */
    public UserRole(UserGroup userGroup, Service service) {
    	this();
    	setUserGroup(userGroup);
    	setService(service);
    }
   
    /** 
     * Role constructor.
     * 
     * @param userGroup
     * @param service
     * @param serviceExtension
     */
    public UserRole(UserGroup userGroup, Service service, String serviceExtension) {
    	this(userGroup, service);
    	setServiceExtension(serviceExtension);
    }
   
    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * User group.
     */
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="userId", nullable=true)
    public UserGroup getUserGroup() {
        return this.userGroup;
    }
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * Service.
     */
    @ManyToOne
    @JoinColumn(name="serviceId", nullable=true)
    public Service getService() {
        return this.service;
    }
    public void setService(Service service) {
        this.service = service;
    }
    
    /**
     * <<Transient>> Service name.
     */
    @Transient
    public String getServiceName() {
    	if (getService()==null) {
    		return "";
    	}
        return getService().getServiceName();
    }
    
    /**
     * Service extension.
     */
    @Column(length=8)
    public String getServiceExtension() {
        return this.serviceExtension;
    }
    public void setServiceExtension(String serviceExtension) {
        this.serviceExtension = serviceExtension;
    }
    
    /**
     * UserRole name.
     */
    @Transient
    public String getUserRoleName() {
        return "ROLE_"+service.getServiceName().toUpperCase()+"_"+serviceExtension;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userGroup").append("='").append(getUserGroup()).append("' ");			
      buffer.append("service").append("='").append(getService()).append("' ");			
      buffer.append("serviceExtension").append("='").append(getServiceExtension()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserRole) ) return false;
		 UserRole castOther = ( UserRole ) other; 
         
		 return ( (this.getUserGroup()==castOther.getUserGroup()) || ( this.getUserGroup()!=null && castOther.getUserGroup()!=null && this.getUserGroup().equals(castOther.getUserGroup()) ) )
 && ( (this.getService()==castOther.getService()) || ( this.getService()!=null && castOther.getService()!=null && this.getService().equals(castOther.getService()) ) )
 && ( (this.getServiceExtension()==castOther.getServiceExtension()) || ( this.getServiceExtension()!=null && castOther.getServiceExtension()!=null && this.getServiceExtension().equals(castOther.getServiceExtension()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getUserGroup() == null ? 0 : this.getUserGroup().hashCode() );
         result = 37 * result + ( getService() == null ? 0 : this.getService().hashCode() );
         result = 37 * result + ( getServiceExtension() == null ? 0 : this.getServiceExtension().hashCode() );
         return result;
   }

}


