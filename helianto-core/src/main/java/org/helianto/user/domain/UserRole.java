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

package org.helianto.user.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Roles represent the authorization given to access a service.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_userrole",
    uniqueConstraints = {@UniqueConstraint(columnNames={"userId", "serviceId", "serviceExtension"})}
)
public class UserRole 
	implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private UserGroup userGroup;
    private Service service;
    private String serviceExtension;
    private String partnershipExtension;
    private char activityState;

    /** 
     * Empty constructor.
     */
    public UserRole() {
    	setServiceExtension("");
    	setActivityStateAsEnum(ActivityState.ACTIVE);
    }
   
    /** 
     * Key constructor.
     * 
     * @param userGroup
     * @param service
     * @param serviceExtension
     */
    public UserRole(UserGroup userGroup, Service service, String serviceExtension) {
    	this();
    	setUserGroup(userGroup);
    	setService(service);
    	setServiceExtension(serviceExtension);
    }
   
    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * User group.
     */
    @JsonBackReference 
    @ManyToOne
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
    @JsonBackReference 
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
     * Service extension list of comma separated values.
     */
    // TODO change name to getServiceExtensions() (append "s" to current method name).
    @Column(length=64)
    public String getServiceExtension() {
        return this.serviceExtension;
    }
    public void setServiceExtension(String serviceExtension) {
        this.serviceExtension = serviceExtension;
    }
    
    /**
     * Service extensions as array.
     */
    @Transient
    // TODO change name to getServiceExtensionAsArray() (insert "s" into current method name).
    public String[] getServiceExtensionAsArray() {
    	if (getServiceExtension()!=null && getServiceExtension().replace(" ", "").length()>0) {
        	return getServiceExtension().replace(" ", "").split(",");
    	}
    	return new  String[] { };
    }
    
    /**
     * UserRole name.
     */
    @Transient
    public String getUserRoleName() {
        return formatRole(service.getServiceName(), serviceExtension);
    }
    
    /**
     * Converts user roles to authorities, including "ROLE_SELF_ID_x", where
     * x is the authorized user identity primary key.
     * 
     * @param userRole
     * @param identityId
     */
    public static Set<String> getRoleNames(Collection<UserRole> roles, long identityId) {
        Set<String> roleNames = new HashSet<String>();
        for (UserRole r : roles) {
        	if (r.getActivityState()==ActivityState.ACTIVE.getValue()) {
               roleNames.addAll(UserRole.getUserRolesAsString(r, identityId));
        	}
        }
        return roleNames;
    }
    
    /**
     * Converts user roles to authorities, including "ROLE_SELF_ID_x", where
     * x is the authorized user identity primary key.
     * 
     * @param userRole
     * @param identityId
     */
    private static Set<String> getUserRolesAsString(UserRole userRole, long identityId) {
        Set<String> roleNames = new HashSet<String>();
        if (identityId>0) {
            roleNames.add(formatRole("SELF", new StringBuilder("ID_").append(identityId).toString()));
        }
        roleNames.add(formatRole(userRole.getService().getServiceName(), null));

        String[] extensions = userRole.getServiceExtension().split(",");
        for (String extension: extensions) {
        	roleNames.add(formatRole(userRole.getService().getServiceName(), extension));
        }
        return roleNames;
    }
    
    /**
     * Internal role formatter.
     * 
     * @param serviceName
     * @param extension
     */
    public static String formatRole(String serviceName, String extension) {
        StringBuilder sb = new StringBuilder("ROLE_").append(serviceName.toUpperCase());
        if (extension!=null && extension.length()>0) {
        	sb.append("_").append(extension.trim());
        }
        return sb.toString();
    }
    
    /**
     * Partnership extension list of comma separated values.
     * 
     * Indicates what types of partnership are allowed by this role. E.g.:
     * C,S means customers and suppliers, while C,L,M means customers, laboratories and
     * manufacturers.
     */
    @Column(length=64)
    public String getPartnershipExtension() {
		return partnershipExtension;
	}
    public void setPartnershipExtension(String partnershipExtension) {
		this.partnershipExtension = partnershipExtension;
	}
    
    /**
     * Partnership extensions as array.
     */
    @Transient
    public String[] getPartnershipExtensionAsArray() {
    	if (getPartnershipExtension()!=null && getPartnershipExtension().replace(" ", "").length()>0) {
        	return getPartnershipExtension().replace(" ", "").split(",");
    	}
    	return new  String[] { };
    }
    
    /**
     * Activity state.
     */
    public char getActivityState() {
		return activityState;
	}
    public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
    public void setActivityStateAsEnum(ActivityState activityState) {
		this.activityState = activityState.getValue();
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


