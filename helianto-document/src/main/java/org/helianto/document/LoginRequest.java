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

package org.helianto.document;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Controllable;
import org.helianto.core.Entity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * A login request.
 * 
 * @author Mauricio Fernandes de Castro              
 */
@javax.persistence.Entity
@Table(name="doc_request",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
public class LoginRequest implements Controllable {

    private static final long serialVersionUID = 1L;
    private long id;
    private Entity entity;
    private long internalNumber;
    private String principal;
    private String principalConfirmation;
    private Date issueDate;
    private char resolution;
    private int complete;
    private Date nextCheckDate;

    /** 
     * Default constructor.
     */
    public LoginRequest() {
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param internalNumber
     */
    public LoginRequest(Entity entity, long internalNumber) {
    	this();
    	setEntity(entity);
    	setInternalNumber(internalNumber);
    }

    /** 
     * Principal constructor.
     * 
     * @param entity
     * @param principal
     */
    public LoginRequest(Entity entity, String principal) {
    	this(entity, 0);
    	setPrincipal(principal);
    }

    @Transient
    public void reset() {
    	setResolution(' ');
    	setComplete(-1);
    }
    
    @Transient
    public String getInternalNumberKey() {
    	return "LOGINREQ";
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
    
    @ManyToOne()
    @JoinColumn(name="entityId")
    public Entity getEntity() {
		return entity;
	}
    public void setEntity(Entity entity) {
		this.entity = entity;
	}
    
    public long getInternalNumber() {
		return internalNumber;
	}
    public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

    /**
     * Principal getter.
     */
    @Column(length=40)
    public String getPrincipal() {
        return this.principal;
    }
    /**
     * Setting the principal also forces to lower case.
     * 
     * @param principal
     */
    public void setPrincipal(String principal) {
        if (principal!=null) {
            this.principal = principal.toLowerCase();
        }
        else {
            this.principal = null;
        }
    }

    /**
     * <<Transient>> Principal name, i.e., substring of principal before '@', if any,
     * or the principal itself.
     */
    @Transient
    public String getPrincipalName() {
    	int position = getPrincipal().indexOf("@");
    	if (position>0) {
    		return getPrincipal().substring(0, position);
    	}
        return getPrincipal();
    }
    
    /**
     * <<Transient>> User principal domain, i.e., substring of principal after '@', if any,
     * or empty string.
     */
    @Transient
    public String getPrincipalDomain() {
    	int position = getPrincipal().indexOf("@");
    	if (position>0) {
    		return getPrincipal().substring(position);
    	}
        return "";
    }
    
    /**
     * Principal confirmation.
     */
    @Transient
    public String getPrincipalConfirmation() {
		return principalConfirmation;
	}
    public void setPrincipalConfirmation(String principalConfirmation) {
		this.principalConfirmation = principalConfirmation;
	}
    
    /**
     * True if principal and principal confirmation are equal.
     */
    @Transient
    public boolean validatePrincipal() {
    	if (getPrincipal()!=null && getPrincipalConfirmation()!=null) {
    		return getPrincipal().equals(getPrincipalConfirmation());
    	}
    	return false;
    }
    
    /**
     * Issue date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style="S-")
    public Date getIssueDate() {
		return issueDate;
	}
    public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
    
    /**
     * Resolution.
     */
    public char getResolution() {
		return resolution;
	}
    public void setResolution(char resolution) {
		this.resolution = resolution;
	}
    
    /**
     * Progress.
     */
    public int getComplete() {
		return complete;
	}
    public void setComplete(int complete) {
		this.complete = complete;
	}
    
    /**
     * Next check date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style="S-")
    public Date getNextCheckDate() {
		return nextCheckDate;
	}
    public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}
    
    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("principal").append("='").append(getPrincipal()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof LoginRequest) ) return false;
         LoginRequest castOther = (LoginRequest) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
         	&& (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   @Override
   public int hashCode() {
        int result = 17;
        result = 37 * result + (int) this.getInternalNumber();
        return result;
  }

}
