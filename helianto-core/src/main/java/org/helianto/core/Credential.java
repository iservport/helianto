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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * Provides <code>Identity</code> with authentication information. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_credential",
    uniqueConstraints = {@UniqueConstraint(columnNames={"identityId"})}
)
public class Credential implements PersonalEntity {

    private static final long serialVersionUID = 1L;
    public static final String ALLOWED_CHARS_IN_PASSWORD = 
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!#$%_";
    public static final int DEFAULT_PASSWORD_SIZE = 8;
    private long id;
    private Identity identity;
    private int version;
    private String password;
    private char credentialState;
    private Date lastModified;
    private Date expirationDate;
    private char encription;
    //transient fields
    private String currentPassword;
    private String newPassword;
    private String verifyPassword;
    private boolean passwordDirty;


    /** 
     * Default constructor.
     */
    public Credential() {
        setCredentialState(ActivityState.SUSPENDED);
        setLastModified(new Date());
        setExpirationDate(getLastModified());
        setEncriptionAsEnum(Encription.PLAIN_PASSWORD);
        setPassword("inactive");
        setVerifyPassword("");
        setCurrentPassword("");
        setPasswordDirty(false);
    }

    /** 
     * Key constructor.
     * 
     * @param identity
     */
    public Credential(Identity identity) {
    	this();
    	setIdentity(identity);
    }

    /** 
     * Password constructor.
     * 
     * @param identity
     * @param password
     */
    public Credential(Identity identity, String password) {
    	this(identity);
        setPassword(password);
        setCredentialState(ActivityState.INITIAL);
    }

    /** 
     * Principal constructor.
     * 
     * @param principal
     * @param password
     */
    public Credential(String principal, String password) {
    	this(new Identity(principal), password);
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
     * Plain text password.
     */
    @Column(length=20)
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Password reset.
     */
    @Transient
    public void resetPassword() {
        this.password = "";
    }
    /**
     * Password generator.
     */
    @Transient
    public void generatePassword() {
        this.password = Credential.passwordFactory();
    }

    /**
     * Credential state.
     */
    public char getCredentialState() {
        return this.credentialState;
    }
    public void setCredentialState(char credentialState) {
        this.credentialState = credentialState;
    }
    public void setCredentialState(ActivityState credentialState) {
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
     * Encription.
     */
    public char getEncription() {
        return this.encription;
    }
    public void setEncription(char encription) {
        this.encription = encription;
    }
    public void setEncriptionAsEnum(Encription encription) {
        this.encription = encription.getValue();
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
     * <<Transient>> Current password.
     * 
     * <p>
     * Required before a new password is to be set.
     * </p>
     */
    @Transient
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
    /**
     * <<Transient>> New password.
     */
    @Transient
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
     * Verify <code>password</code>.
     * 
     * <p>
     * True if <code>password</code> and <code>verifyPassword</code> transient field match.
     * </p>
     */
    @Transient
    public boolean isPasswordVerified() {
    	if (getPassword()==null 
    			|| getPassword().length()==0 
    			|| getPassword().compareTo(getVerifyPassword())!=0) {
            setPassword("");
            setVerifyPassword("");
            setPasswordDirty(true);
            setCredentialState(ActivityState.SUSPENDED.getValue());
            return false;
        }
        setVerifyPassword("");
        setPasswordDirty(false);
        setCredentialState(ActivityState.ACTIVE.getValue());
        return true;
    }
    
    /**
     * Verify <code>password</code> field against transient <code>verifyPassword</code> transient field.
     * 
     * <p>
     * Updates the <code>password</code> field and returns true if:
     * </p>
     * <ol>
     * <li><code>currentPassword</code> transient field matches <code>password</code> field, and</li>
     * <li><code>newPassword</code> transient field matches <code>verifyPassword</code> transient field.</li>
     * </ol>
     */
    @Transient
    public boolean isNewPasswordVerified() {
    	if (getCurrentPassword()!=null 
    			&& getCurrentPassword().length()>0 
    			&& getPassword().compareTo(getCurrentPassword())==0) {
    		// current password and password match
    		if (getNewPassword()!=null 
        			&& getNewPassword().length()>0
        			&& getVerifyPassword()!=null
        			&& getVerifyPassword().length()>0
        			&& getNewPassword().compareTo(getVerifyPassword())==0) {
        		// new password and verify password match
    			setPassword(getNewPassword());
    			return true;
    		}
    	}
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
         if ( !(other instanceof Credential) ) return false;
         Credential castOther = (Credential) other; 
         
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
