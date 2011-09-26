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

import org.springframework.web.multipart.MultipartFile;

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
    private long id;
    private String principal;
    private String optionalAlias;
    private PersonalData personalData;
    private Date created;
    private char identityType;
    private char notification;
	private byte[] photo;
    private String multipartFileContentType;
    private List<Phone> phones = new ArrayList<Phone>();
    private List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
    private Set<Credential> credentials = new HashSet<Credential>();

    /** 
     * Default constructor.
     */
    public Identity() {
        setPersonalData(new PersonalData());
        setCreated(new Date());
        setIdentityTypeAsEnum(IdentityType.EMAIL);
        setNotificationAsEnum(Notification.AUTOMATIC);
        setPhoto(new byte[0]);
    }

    /** 
     * Principal constructor.
     * 
     * @param principal
     */
    public Identity(String principal) {
    	this();
    	setPrincipal(principal);
    	setOptionalAlias(getPrincipalName());
    }

    /** 
     * Principal and optional alias constructor.
     * 
     * @param principal
     * @param optionalAlias
     */
    public Identity(String principal, String optionalAlias) {
    	this();
    	setPrincipal(principal);
    	setOptionalAlias(optionalAlias);
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
     * Optional alias.
     */
    @Column(length=20)
    public String getOptionalAlias() {
        return this.optionalAlias;
    }
    public void setOptionalAlias(String optionalAlias) {
        this.optionalAlias = optionalAlias;
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
    @Transient
    public char getGender() {
    	if (getPersonalData()==null) {
    		return Gender.NOT_SUPPLIED.getValue();
    	}
		return getPersonalData().getGender();
	}
    
    /**
     * <<Transient>> Safe appellation getter.
     */
    @Transient
    public char getAppellation() {
    	if (getPersonalData()==null) {
    		return Appellation.NOT_SUPPLIED.getValue();
    	}
		return getPersonalData().getAppellation();
	}
    
    /**
     * <<Transient>> Safe birth date getter.
     */
    @Transient
    public Date getBirthDate() {
    	if (getPersonalData()==null) {
    		return new Date(1000l);
    	}
		return getPersonalData().getBirthDate();
	}
    
    /**
     * <<Transient>> Safe identity alias.
     */
    @Transient
    public String getAlias() {
		if (getOptionalAlias()!=null && getOptionalAlias().length()>0) {
			return getOptionalAlias();
		}
		return getPrincipal();
    }

    /**
     * Date created.
     */
    @Temporal(TemporalType.TIMESTAMP)
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
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(table="core_identitydata")
    public byte[] getPhoto() {
		return photo;
	}
    public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
    /**
     * Tipo de conteúdo, tal como img/jpg, etc.
     */
	@Column(length=32)
    public String getMultipartFileContentType() {
		return multipartFileContentType;
	}
    public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
	}
    
    /**
     * Ture if the phot has the image type.
     */
    @Transient
    public boolean isPhotoLoaded() {
    	return getMultipartFileContentType()!=null && getMultipartFileContentType().startsWith("image");
    }

	@Transient
    private transient MultipartFile file;
	
	/**
	 * <<Transient>> Required to allow for file upload.
	 */
	@Transient
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
	/**
	 * <<Transient>> Convenience method to read uploaded data.
	 */
	@Transient
	public void processFile() throws IOException {
		setPhoto(getFile().getBytes());
		setMultipartFileContentType(file.getContentType());
	}
	
    /**
     * A set of credentials.
     */
    @OneToMany(mappedBy="identity")
    public Set<Credential> getCredentials() {
		return credentials;
	}
    public void setCredentials(Set<Credential> credentials) {
		this.credentials = credentials;
	}
    
    /**
     * List of phones.
     */
    @ElementCollection
    @CollectionTable(name = "core_identityPhone", joinColumns = @JoinColumn(name = "identityId"))
    @OrderColumn(name="sequence")
    public List<Phone> getPhones() {
		return phones;
	}
    public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
    
    /**
     * List of contact infos.
     */
    @ElementCollection
    @CollectionTable(name = "core_identityContact", joinColumns = @JoinColumn(name = "identityId"))
    @OrderColumn(name="sequence")
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
