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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import org.helianto.core.def.Appellation;
import org.helianto.core.def.Gender;
import org.helianto.core.def.Resolution;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalData;
import org.helianto.core.number.Sequenceable;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * A login request.
 * 
 * @author Mauricio Fernandes de Castro              
 */
@javax.persistence.Entity
@Table(name="core_userrequest",
    uniqueConstraints = {@UniqueConstraint(columnNames={"userGroupId", "internalNumber"})}
)
public class UserRequest 
	implements  
	  Sequenceable
	, Controllable 

{

    private static final long serialVersionUID = 1L;
    private int id;
    private UserGroup userGroup;
    private long internalNumber;
    private String principal = "";
    private String principalConfirmation = "";
    private PersonalData personalData;
    private Date issueDate;
    private char resolution = Resolution.TODO.getValue();
    private int complete;
    private Date nextCheckDate;
    private String tempPassword;
    private String postalCode;
    private String promotionCode;
    private String localeString;

    /** 
     * Default constructor.
     */
    public UserRequest() {
        setPersonalData(new PersonalData());
        setIssueDate(new Date());
    }

    /** 
     * Key constructor.
     * 
     * @param userGroup
     * @param internalNumber
     */
    public UserRequest(UserGroup userGroup, long internalNumber) {
    	this();
    	setUserGroup(userGroup);
    	setInternalNumber(internalNumber);
    }

    /** 
     * Principal constructor.
     * 
     * @param userGroup
     * @param principal
     */
    public UserRequest(UserGroup userGroup, String principal) {
    	this(userGroup, 0);
    	setPrincipal(principal);
    }

    @Transient
    public String getInternalNumberKey() {
    	return "LOGINREQ";
    }
    
    @Transient
    public int getStartNumber() {
    	return 1;
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
    
    @JsonBackReference 
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userGroupId")
    public UserGroup getUserGroup() {
		return userGroup;
	}
    public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
    
    /**
     * True if the property {@link #getUserGroup()} has the User instead of UserGroup.
     */
    @Transient
    public boolean isConfirmed() {
		return (getUserGroup() instanceof User);
	}
    
    @Transient
    public Entity getEntity() {
    	if (getUserGroup()!=null) {
    		return getUserGroup().getEntity();
    	}
		return null;
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
    	if (getPrincipal()!=null) {
        	int position = getPrincipal().indexOf("@");
        	if (position>0) {
        		return getPrincipal().substring(0, position);
        	}
            return getPrincipal();
    	}
    	return "";
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
    	return validatePrincipal(getPrincipalConfirmation());
    }
    
    /**
     * True if principal and principal confirmation are equal.
     * 
     * @param principalConfirmation
     */
    @Transient
    public boolean validatePrincipal(String principalConfirmation) {
    	if (getPrincipal()!=null && principalConfirmation!=null) {
    		return getPrincipal().equals(principalConfirmation);
    	}
    	return false;
    }
    
    /**
     * PersonalData getter.
     */
    @Embedded
    public PersonalData getPersonalData() {
        return this.personalData;
    }
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
    
    /**
     * <<Transient>> Safe identity name getter.
     */
    @Transient
    public String getIdentityName() {
    	if (getPersonalData()!=null) {
        	return new StringBuilder(getPersonalData().getFirstName())
    	    .append(" ")
    	    .append(getPersonalData().getLastName()).toString();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Safe gender getter.
     */
    @Transient
    public char getGender() {
    	if (getPersonalData()!=null) {
    		return getPersonalData().getGender();
    	}
		return Gender.NOT_SUPPLIED.getValue();
	}
    
    /**
     * <<Transient>> Safe appellation getter.
     */
    @Transient
    public char getAppellation() {
    	if (getPersonalData()!=null) {
    		return getPersonalData().getAppellation();
    	}
		return Appellation.NOT_SUPPLIED.getValue();
	}
    
    /**
     * <<Transient>> Safe birth date getter.
     */
    @Transient
    public Date getBirthDate() {
    	if (getPersonalData()!=null) {
    		return getPersonalData().getBirthDate();
    	}
		return new Date(1000l);
	}
    
    /**
     * <<Transient>> Safe age getter.
     */
    @Transient
    public int getAge() {
		return getAge(new Date());
	}
    
    /**
     * <<Transient>> Safe age getter.
     * 
     * @param date
     */
    @Transient
    protected int getAge(Date date) {
    	if (getPersonalData()!=null && getPersonalData().getBirthDate()!=null) {
    		DateMidnight birthdate = new DateMidnight(getPersonalData().getBirthDate());
    		return Years.yearsBetween(birthdate, new DateTime(date)).getYears();
    	}
		return -1;
	}
    
    /**
     * <<Transient>> Call back to create an <code>Identity</code> from user request daa.
     */
    @Transient
    public Identity createIdentity() {
    	return new Identity(getPrincipal(), "", getPersonalData());
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
    public void setResolutionAsEnum(Resolution resolution) {
		this.resolution = resolution.getValue();
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
     * Temporary password.
     */
    @Column(length=48)
    public String getTempPassword() {
		return tempPassword;
	}
    public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
    
    /**
     * Postal code.
     */
    @Column(length=10)
    public String getPostalCode() {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    /**
     * Promotion code.
     */
    @Column(length=10)
    public String getPromotionCode() {
		return promotionCode;
	}
    public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
    
    /**
     * Locale string (ex. en, pt, pt_BR).
     */
    @Column(length=12)
    public String getLocaleString() {
		return localeString;
	}
    public void setLocaleString(String localeString) {
		this.localeString = localeString;
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
         if ( !(other instanceof UserRequest) ) return false;
         UserRequest castOther = (UserRequest) other; 
         
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
