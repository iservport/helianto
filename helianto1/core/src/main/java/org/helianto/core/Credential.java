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



import org.helianto.core.Identity;
/**
 * <p>
 * Provides <code>Identity</code> with authentication information. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_credential",
    uniqueConstraints = {@UniqueConstraint(columnNames={"identityId"})}
)
public class Credential implements java.io.Serializable {

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
    private Date expired;
    private byte encription;
    //transient fields
    private String verifyPassword;
    private boolean passwordDirty;


    /** default constructor */
    public Credential() {
    }

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Identity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="identityId", nullable=true)
    public Identity getIdentity() {
        return this.identity;
    }
    /**
     * Identity setter.
     */
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    /**
     * Version getter.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    /**
     * Version setter.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Password getter.
     */
    @Column(length=20)
    public String getPassword() {
        return this.password;
    }
    /**
     * Password setter.
     */
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
     * CredentialState getter.
     */
    public char getCredentialState() {
        return this.credentialState;
    }
    /**
     * CredentialState setter.
     */
    public void setCredentialState(char credentialState) {
        this.credentialState = credentialState;
    }

    /**
     * LastModified getter.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModified() {
        return this.lastModified;
    }
    /**
     * LastModified setter.
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Expired getter.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExpired() {
        return this.expired;
    }
    /**
     * Expired setter.
     */
    public void setExpired(Date expired) {
        this.expired = expired;
    }

    /**
     * Encription getter.
     */
    public byte getEncription() {
        return this.encription;
    }
    /**
     * Encription setter.
     */
    public void setEncription(byte encription) {
        this.encription = encription;
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
     * Empty <code>Identity</code> and password <code>Credential</code> factory.
     * 
     * @param identity
     */
    public static Credential credentialFactory() {
        Identity identity = Identity.identityFactory("");
        return credentialFactory(identity, passwordFactory());
    }

    /**
     * Empty <code>Identity</code> <code>Credential</code> factory.
     * 
     * @param identity
     */
    public static Credential credentialFactory(String password) {
        Identity identity = Identity.identityFactory("");
        return credentialFactory(identity, password);
    }

    /**
     * <code>Credential</code> factory.
     * 
     * @param identity
     */
    public static Credential credentialFactory(Identity identity, String password) {
        Credential credential = new Credential();
        credential.setIdentity(identity);
        credential.setPassword(password);
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        credential.setLastModified(new Date());
        credential.setExpired(null);
        credential.setCredentialState(ActivityState.INITIAL.getValue());
        credential.setEncription(Encription.PLAIN_PASSWORD.getValue());
        return credential;
    }

    /**
     * VerifyPassword getter.
     */
    @Transient
    public String getVerifyPassword() {
        return verifyPassword;
    }
    /**
     * VerifyPassword setter. Force <code>passwordDirty</code> to true.
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
     * Password verifier.
     */
    @Transient
    public static boolean verifyPassword(Credential credential) {
        if (credential.getPassword().compareTo(credential.getVerifyPassword())!=0) {
            credential.setPassword("");
            credential.setVerifyPassword("");
            credential.setPasswordDirty(true);
            credential.setCredentialState(ActivityState.SUSPENDED.getValue());
            return false;
        }
        credential.setVerifyPassword("");
        credential.setPasswordDirty(false);
        credential.setCredentialState(ActivityState.ACTIVE.getValue());
        return true;
    }
    
    /**
     * <code>Credential</code> natural id query.
     */
    @Transient
    public static String getCredentialNaturalIdQueryString() {
        return "select credential from Credential credential where credential.identity = ? ";
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
