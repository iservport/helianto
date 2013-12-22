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

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Version;

import org.helianto.core.def.ActivityState;
import org.helianto.core.def.ProviderType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * Provides <code>Identity</code> with authentication information. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_security",
    uniqueConstraints = {
		 @UniqueConstraint(columnNames={"identityId", "providerType"})
		,@UniqueConstraint(columnNames={"consumerKey"})
    }
)
public class IdentitySecurity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ALLOWED_CHARS_IN_PASSWORD = 
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!#$%_";
    public static final int DEFAULT_PASSWORD_SIZE = 8;
    
    private int id;
    private int version;
    private Identity identity;
    private ProviderType providerType;
    private String consumerKey = "";
    private String consumerSecret = "";
    private String password = "inactive";
    private char credentialState = ActivityState.ACTIVE.getValue();
    private Date lastModified;
    private Date expirationDate;
    
    //transient fields
    private String rawPassword = "";
    private String verifyPassword = "";
    private boolean passwordDirty = false;


    /** 
     * Default constructor.
     */
    public IdentitySecurity() {
        setLastModified(new Date());
        setExpirationDate(getLastModified());
    }

    /** 
     * Key constructor.
     * 
     * @param identity
     * @param providerType
     */
    public IdentitySecurity(Identity identity, ProviderType providerType) {
    	this();
    	setIdentity(identity);
    	setProviderType(providerType);
    	setConsumerKey(identity.getPrincipal());
    }

    /** 
     * Password constructor.
     * 
     * @param identity
     * @param rawPassword
     */
    public IdentitySecurity(Identity identity, String rawPassword) {
    	this(identity, ProviderType.email);
        setRawPassword(rawPassword);
        setCredentialStateAsEnum(ActivityState.INITIAL);
    }

    /** 
     * Activity state constructor.
     * 
     * @param identity
     * @param activityState
     */
    public IdentitySecurity(Identity identity, char activityState) {
    	this(identity, ProviderType.email);
        setCredentialState(activityState);
    }

    /** 
     * Principal constructor.
     * 
     * @param principal
     * @param password
     */
    public IdentitySecurity(String principal, String password) {
    	this(new Identity(principal), password);
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
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * <<Cascading>> Identity owning this credential.
     */
    @JsonBackReference 
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    
    /**
     * <<Transient>> Convenience to read identity principal.
     */
    @Transient
    public String getPrincipal() {
		return getIdentity().getPrincipal();
	}
    
    /**
     * Provider type.
     */
    @Enumerated(EnumType.STRING)
    @Column(length=12)
    public ProviderType getProviderType() {
		return providerType;
	}
    public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}
    
    /**
     * Consumer key.
     */
    @Column(length=40)
    public String getConsumerKey() {
		return consumerKey;
	}
    public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
    
    /**
     * Encoded consumer secret.
     */
    @Column(length=60)
    public String getConsumerSecret() {
		return consumerSecret;
	}
    public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

    /**
     * Plain text password.
     * @deprecated
     */
    @Column(length=20)
    public String getPassword() {
        return this.password;
    }
    /**
     * @deprecated
     */
    public void setPassword(String password) {
        this.password = password;
    }

//    /**
//     * Password generator.
//     */
//    @Transient
//    public void generatePassword() {
//        this.password = IdentitySecurity.passwordFactory();
//    }

    /**
     * Credential state.
     */
    public char getCredentialState() {
        return this.credentialState;
    }
    public void setCredentialState(char credentialState) {
        this.credentialState = credentialState;
    }
    public void setCredentialStateAsEnum(ActivityState credentialState) {
        this.credentialState = credentialState.getValue();
    }

    /**
     * Last modified.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModified() {
        return this.lastModified;
    }
    @Transient
    public String getLastModifiedDateAsString() {
    	if (getLastModified()==null) {
    		return "";
    	}
    	DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        return formatter.format(getLastModified());
    }
    @Transient
    public String getLastModifiedTimeAsString() {
    	if (getLastModified()==null) {
    		return "";
    	}
    	DateFormat formatter = SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
        return formatter.format(getLastModified());
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Expiration date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpirationDate() {
        return this.expirationDate;
    }
    @Transient
    public boolean isExpired() {
    	if (getExpirationDate()==null) {
    		// null means never expires
    		return false;
    	}
    	return getExpirationDate().before(new Date());
    }
    @Transient
    public String getExpirationDateAsString() {
    	if (getExpirationDate()==null) {
    		return "";
    	}
    	DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        return formatter.format(getExpirationDate());
    }
    @Transient
    public String getExpirationTimeAsString() {
    	if (getExpirationDate()==null) {
    		return "";
    	}
    	DateFormat formatter = SimpleDateFormat.getTimeInstance(DateFormat.SHORT);
        return formatter.format(getExpirationDate());
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Create a random password of size <code>DEFAULT_PASSWORD_SIZE</code>
     * containing only characters in <code>ALLOWED_CHARS_IN_PASSWORD</code>.
     */
    public static String passwordFactory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<DEFAULT_PASSWORD_SIZE;i++) {
            int index = (int) (ALLOWED_CHARS_IN_PASSWORD.length() * Math.random());
            sb.append(ALLOWED_CHARS_IN_PASSWORD.charAt(index));
        }
        return sb.toString();
    }

    /**
     * <<Transient>> Raw password.
     * 
     * <p>
     * Required before a new password is to be set.
     * </p>
     */
    @Transient
	public String getRawPassword() {
		return rawPassword;
	}
	public void setRawPassword(String currentPassword) {
		this.rawPassword = currentPassword;
	}
	
    /**
     * <<Transient>> Verify password.
     */
    @Transient
    public String getVerifyPassword() {
        return verifyPassword;
    }
    /**
     * VerifyPassword setter. Forces <code>passwordDirty</code> to true.
     */
    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        this.passwordDirty = true;
    }
    
    /**
     * PasswordDirty getter.
     */
    @Transient
    public boolean isPasswordDirty() {
        return passwordDirty;
    }
    /**
     * PasswordDirty setter.
     */
    public void setPasswordDirty(boolean passwordDirty) {
        this.passwordDirty = passwordDirty;
    }

    /**
     * True if the raw password is not empty.
     */
    @Transient
    public boolean isRawPasswordNotEmpty() {
    	return getRawPassword()!=null && getRawPassword().length()>0;
    }
    
    /**
     * True if the raw password matches the verified password field.
     */
    @Transient
    public boolean isRawPasswordVerified() {
    	if (isRawPasswordNotEmpty() && getRawPassword().compareTo(getVerifyPassword())==0) {
            setVerifyPassword("");
            setPasswordDirty(false);
            return true;
        }
        setRawPassword("");
        setVerifyPassword("");
        setPasswordDirty(true);
        return false;
    }
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("identity").append("='").append(getIdentity()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof IdentitySecurity) ) return false;
         IdentitySecurity castOther = (IdentitySecurity) other; 
         
         return ((this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ));
   }
   
   /**
    * hashCode
    */
    @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         return result;
   }   

}
