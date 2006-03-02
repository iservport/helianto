package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist a credential, unique by principal.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Credential implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String principal;

    /** nullable persistent field */
    private String password;

    /** persistent field */
    private char credentialType;

    /** persistent field */
    private char notification;

    /** nullable persistent field */
    private Date created;

    /** nullable persistent field */
    private Date lastModified;

    /** nullable persistent field */
    private Date expired;

    /** persistent field */
    private char credentialState;

    /** persistent field */
    private Set users;

    /** persistent field */
    private org.helianto.core.PersonalData personalData;

    /** full constructor */
    public Credential(String principal, String password, char credentialType, char notification, Date created, Date lastModified, Date expired, char credentialState, Set users, org.helianto.core.PersonalData personalData) {
        this.principal = principal;
        this.password = password;
        this.credentialType = credentialType;
        this.notification = notification;
        this.created = created;
        this.lastModified = lastModified;
        this.expired = expired;
        this.credentialState = credentialState;
        this.users = users;
        this.personalData = personalData;
    }

    /** default constructor */
    public Credential() {
    }

    /** minimal constructor */
    public Credential(String principal, char credentialType, char notification, char credentialState, Set users, org.helianto.core.PersonalData personalData) {
        this.principal = principal;
        this.credentialType = credentialType;
        this.notification = notification;
        this.credentialState = credentialState;
        this.users = users;
        this.personalData = personalData;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * 				@see CredentialType
     * 			
     */
    public char getCredentialType() {
        return this.credentialType;
    }

    public void setCredentialType(char credentialType) {
        this.credentialType = credentialType;
    }

    /** 
     * 				@see NotificationType
     * 			
     */
    public char getNotification() {
        return this.notification;
    }

    public void setNotification(char notification) {
        this.notification = notification;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getExpired() {
        return this.expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    /** 
     * 				@see CredentialState
     * 			
     */
    public char getCredentialState() {
        return this.credentialState;
    }

    public void setCredentialState(char credentialState) {
        this.credentialState = credentialState;
    }

    public Set getUsers() {
        return this.users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    public org.helianto.core.PersonalData getPersonalData() {
        return this.personalData;
    }

    public void setPersonalData(org.helianto.core.PersonalData personalData) {
        this.personalData = personalData;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
