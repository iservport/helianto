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

import org.helianto.core.def.Gender;
import org.helianto.core.def.IdentityType;
import org.helianto.core.def.Notification;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
	@JsonIgnore
    @ElementCollection
    @CollectionTable(name = "core_identityPhone", joinColumns = @JoinColumn(name = "identityId"))
    @OrderColumn(name="sequence")
    private List<Phone> phones = new ArrayList<Phone>();
    
	@JsonIgnore
    @ElementCollection
    @CollectionTable(name = "core_identityContact", joinColumns = @JoinColumn(name = "identityId"))
    @OrderColumn(name="sequence")
    private List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
    
	@JsonIgnore
    @OneToMany(mappedBy="identity")
    private Set<IdentitySecurity> connections = new HashSet<IdentitySecurity>();

	@Transient
    private transient MultipartFile file;
	
	@Transient
    private String passwordToChange;
	
	@Transient
    private String passwordConfirmation;
	
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
	 * Read constructor.
	 * 
	 * @param id
	 * @param userGroupId
	 * @param identityType
	 * @param principal
	 * @param displayName
	 * @param appellation
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param notification
	 * @param birthDate
	 * @param imageUrl
	 */
	public Identity(Integer id, char identityType, String principal, String displayName,
			char appellation, String firstName, String lastName,
			char gender, char notification, Date birthDate, String imageUrl) {
		this(principal, displayName, new PersonalData(firstName, lastName, gender, appellation, birthDate, imageUrl));
		this.id = id;
		this.identityType = identityType;
		this.notification = notification;
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
     * Safe personal data getter.
     */
    protected final PersonalData safePersonalData() {
    	if (getPersonalData()==null) {
    		return new PersonalData();
    	}
    	return getPersonalData();
    }
    
    /**
     * <<Transient>> identity first name.
     */
    public String getIdentityFirstName() {
		return safePersonalData().getFirstName();
    }
    public void setIdentityFirstName(String firstName) {
    	safePersonalData().setFirstName(firstName);
	}
    
    /**
     * <<Transient>> identity last name.
     */
    public String getIdentityLastName() {
    	return safePersonalData().getLastName();
    }
    public void setIdentityLastName(String lastName) {
    	safePersonalData().setLastName(lastName);
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
     * <<Transient>> gender.
     */
    public char getGender() {
    	return safePersonalData().getGender();
    }
    public void setGender(char gender) {
    	safePersonalData().setGender(gender);
	}
    
    /**
     * Gender as enum.
     */
    public Gender getGenderAsEnum() {
    	for (Gender g: Gender.values()) {
    		if (g.getValue()==safePersonalData().getGender()) {
    			return g;
    		}
    	}
    	return null;
    }
    public void setGenderAsEnum(Gender gender) {
    	safePersonalData().setGender(gender.getValue());
    }
    
    /**
     * <<Transient>> appellation.
     */
    public char getAppellation() {
    	return safePersonalData().getAppellation();
    }
    public void setAppellation(char appellation) {
    	safePersonalData().setAppellation(appellation);
	}
    
    /**
     * <<Transient>> birth date.
     */
    public Date getBirthDate() {
    	return safePersonalData().getBirthDate();
    }
    public void setBirthDate(Date birthDate) {
    	safePersonalData().setBirthDate(birthDate);
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
     * <<Transient>> image URL.
     */
    public String getImageUrl() {
    	if (isImageAvailable()) {
        	return safePersonalData().getImageUrl();
    	}
		return "";
    }
    public void setImageUrl(String imageUrl) {
    	safePersonalData().setImageUrl(imageUrl);
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
    
    /**
     * Identity type as enum.
     */
    public IdentityType getIdentityTypeAsEnum() {
    	for (IdentityType t: IdentityType.values()) {
    		if (t.getValue()==this.identityType) {
    			return t;
    		}
    	}
    	return null;
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
     * <<Transient>> Password to change.
     */
    public String getPasswordToChange() {
		return passwordToChange;
	}
    public void setPasswordToChange(String passwordToChange) {
		this.passwordToChange = passwordToChange;
	}
    
    /**
     * <<Transient>> Password confirmation.
     */
    public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
    public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
    
    /**
     * True only if password to change is not empty and matches password confirmation.
     */
    public boolean isPasswordChanging() {
    	if (getPasswordToChange()!=null 
    			&& !getPasswordToChange().isEmpty() 
    			&& getPasswordToChange().equals(getPasswordConfirmation())) {
    		return true;
    	}
    	return false;
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
