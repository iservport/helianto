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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.Appellation;
import org.helianto.core.def.Gender;
import org.helianto.core.def.IdentityType;
import org.helianto.core.def.Notification;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * An uniquely identified actor.
 * 
 * @author Mauricio Fernandes de Castro              
 */
@javax.persistence.Entity
@Table(name="core_identity",
    uniqueConstraints = {@UniqueConstraint(columnNames={"principal"})}
)
@SecondaryTables({@SecondaryTable(name="core_identitydata", 
	pkJoinColumns={@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")})
})
public class Identity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(length=64)
	private String displayName = "";
	
    @Column(length=20)
	private String optionalSourceAlias = "";
	
    @Column(length=40)
    private String principal = "";
    
    @Embedded
    private PersonalData personalData;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
    
    private char identityType = IdentityType.PERSONAL_EMAIL.getValue();
    
    private char notification = Notification.AUTOMATIC.getValue();
    
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(table="core_identitydata")
	private byte[] photo = new byte[0];
	
	@Column(length=32)
    private String multipartFileContentType;
    
    @ElementCollection
    @CollectionTable(name = "core_identityPhone", joinColumns = @JoinColumn(name = "identityId"))
    @OrderColumn(name="sequence")
    private List<Phone> phones = new ArrayList<Phone>();
    
    @ElementCollection
    @CollectionTable(name = "core_identityContact", joinColumns = @JoinColumn(name = "identityId"))
    @OrderColumn(name="sequence")
    private List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
    
    @JsonManagedReference 
    @OneToMany(mappedBy="identity")
    private Set<IdentitySecurity> connections = new HashSet<IdentitySecurity>();

	@Transient
    private transient MultipartFile file;
	
    /** 
     * Default constructor.
     */
    public Identity() {
        this("");
    }

    /** 
     * Principal constructor.
     * 
     * @param principal
     */
    public Identity(String principal) {
    	this(principal, "");
    }

    /** 
     * Principal and optional alias constructor.
     * 
     * @param principal
     * @param displayName
     */
    public Identity(String principal, String displayName) {
    	this(principal, displayName, new PersonalData());
    }

    /** 
     * Principal and optional alias constructor.
     * 
     * @param principal
     * @param displayName
     * @param personalData
     */
    public Identity(String principal, String displayName, PersonalData personalData) {
    	setPrincipal(principal);
    	setDisplayName(displayName);
        setPersonalData(personalData);
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
     * Principal getter.
     */
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
    public String getPrincipalDomain() {
    	if (getPrincipal()!=null) {
        	int position = getPrincipal().indexOf("@");
        	if (position>0) {
        		return getPrincipal().substring(position);
        	}
    	}
        return "";
    }
    
    /**
     * Optional source alias.
     * 
     * <p>
     * May be used to create an user in the future.
     * </p>
     */
    public String getOptionalSourceAlias() {
		return optionalSourceAlias;
	}
    public void setOptionalSourceAlias(String optionalSourceAlias) {
		this.optionalSourceAlias = optionalSourceAlias;
	}
    
    /**
     * Optional alias.
     * @deprecated
     * @see #getDisplayName()
     */
    public String getOptionalAlias() {
        return getDisplayName();
    }
    public void setOptionalAlias(String displayName) {
        setDisplayName(displayName);
    }

    /**
     * Display name.
     */
    public String getDisplayName() {
		return displayName;
	}
    public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

    /**
     * PersonalData getter.
     */
    public PersonalData getPersonalData() {
        return this.personalData;
    }
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
    
    /**
     * <<Transient>> Safe identity first name getter.
     */
    public String getIdentityFirstName() {
    	if (getPersonalData()!=null) {
    		return getPersonalData().getFirstName();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Safe identity last name getter.
     */
    public String getIdentityLastName() {
    	if (getPersonalData()!=null) {
    		return getPersonalData().getLastName();
    	}
    	return "";
    }
    
    /**
     * <<Transient>> Safe identity name getter.
     */
    public String getIdentityName() {
    	if (getPersonalData()==null) {
    		return getAlias();
    	}
    	return new StringBuilder(getPersonalData().getFirstName())
    	    .append(" ")
    	    .append(getPersonalData().getLastName()).toString();
    }
    
    /**
     * <<Transient>> Safe gender getter.
     */
    public char getGender() {
    	if (getPersonalData()==null) {
    		return Gender.NOT_SUPPLIED.getValue();
    	}
		return getPersonalData().getGender();
	}
    
    /**
     * <<Transient>> Safe appellation getter.
     */
    public char getAppellation() {
    	if (getPersonalData()==null) {
    		return Appellation.NOT_SUPPLIED.getValue();
    	}
		return getPersonalData().getAppellation();
	}
    
    /**
     * <<Transient>> Safe birth date getter.
     */
    public Date getBirthDate() {
    	if (getPersonalData()==null) {
    		return new Date(1000l);
    	}
		return getPersonalData().getBirthDate();
	}
    
    /**
     * <<Transient>> Safe age getter.
     */
    public int getAge() {
		return getAge(new Date());
	}
    
    /**
     * <<Transient>> Safe age getter.
     * 
     * @param date
     */
    protected int getAge(Date date) {
    	if (getPersonalData()!=null && getPersonalData().getBirthDate()!=null) {
    		DateMidnight birthdate = new DateMidnight(getPersonalData().getBirthDate());
    		return Years.yearsBetween(birthdate, new DateTime(date)).getYears();
    	}
		return -1;
	}
    
    /**
     * <<Transient>> True if image url is available.
     */
    public boolean isImageAvailable() {
    	if (getPersonalData()!=null && getPersonalData().getImageUrl()!=null 
    			&& getPersonalData().getImageUrl().length()>0) {
    		return true;
    	}
    	return false;
	}
    
    /**
     * <<Transient>> Safe image url getter.
     */
    public String getImageUrl() {
    	if (isImageAvailable()) {
    		return getPersonalData().getImageUrl();
    	}
		return "";
	}
    
    /**
     * <<Transient>> Safe identity alias.
     */
    public String getAlias() {
		if (getDisplayName()!=null && getDisplayName().length()>0) {
			return getDisplayName();
		}
		return getPrincipal();
    }

    /**
     * Date created.
     */
    public Date getCreated() {
        return this.created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * IdentityType getter.
     */
    public char getIdentityType() {
        return this.identityType;
    }
    public void setIdentityType(char identityType) {
        this.identityType = identityType;
    }
    public void setIdentityTypeAsEnum(IdentityType identityType) {
        this.identityType = identityType.getValue();
    }
    
    /**
     * True if can receive email.
     */
    public boolean isAddressable() {
		return IdentityType.isAddressable(getIdentityType());
	}

    /**
     * Notification getter.
     */
    public char getNotification() {
        return this.notification;
    }
    public void setNotification(char notification) {
        this.notification = notification;
    }
    public void setNotificationAsEnum(Notification notification) {
        this.notification = notification.getValue();
    }
    
    /**
     * Identity photo.
     */
    public byte[] getPhoto() {
		return photo;
	}
    public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
    /**
     * Content type, like img/jpg, etc.
     */
    public String getMultipartFileContentType() {
		return multipartFileContentType;
	}
    public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
	}
    
    /**
     * Ture if the phot has the image type.
     */
    public boolean isPhotoLoaded() {
    	return getMultipartFileContentType()!=null && getMultipartFileContentType().startsWith("image");
    }

	/**
	 * <<Transient>> Required to allow for file upload.
	 */
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
	/**
	 * <<Transient>> Convenience method to read uploaded data.
	 */
	public void processFile() throws IOException {
		setPhoto(getFile().getBytes());
		setMultipartFileContentType(file.getContentType());
	}
	
    /**
     * A set of connection data.
     */
    public Set<IdentitySecurity> getConnections() {
		return connections;
	}
    public void setConnections(Set<IdentitySecurity> connections) {
		this.connections = connections;
	}
    
    /**
     * List of phones.
     */
    public List<Phone> getPhones() {
		return phones;
	}
    public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
    
    /**
     * List of contact infos.
     */
    public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}
    public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
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
         if ( !(other instanceof Identity) ) return false;
         Identity castOther = (Identity) other; 
         
         return ((this.getPrincipal()==castOther.getPrincipal()) || ( this.getPrincipal()!=null && castOther.getPrincipal()!=null && this.getPrincipal().equals(castOther.getPrincipal()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPrincipal() == null ? 0 : this.getPrincipal().hashCode() );
         return result;
   }   

}
