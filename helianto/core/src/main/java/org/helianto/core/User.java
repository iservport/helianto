package org.helianto.core;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 			
 * <p>
 * Persist the user account.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class User implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private boolean accountNonExpired;

    /** persistent field */
    private boolean accountNonLocked;

    /** persistent field */
    private char userType;

    /** nullable persistent field */
    private org.helianto.core.Entity entity;

    /** nullable persistent field */
    private org.helianto.core.Credential credential;

    /** nullable persistent field */
    private org.helianto.core.User parent;

    /** persistent field */
    private Set roles;

    /** full constructor */
    public User(boolean accountNonExpired, boolean accountNonLocked, char userType, org.helianto.core.Entity entity, org.helianto.core.Credential credential, org.helianto.core.User parent, Set roles) {
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.userType = userType;
        this.entity = entity;
        this.credential = credential;
        this.parent = parent;
        this.roles = roles;
    }

    /** default constructor */
    public User() {
    }

    /** minimal constructor */
    public User(boolean accountNonExpired, boolean accountNonLocked, char userType, Set roles) {
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.userType = userType;
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public char getUserType() {
        return this.userType;
    }

    public void setUserType(char userType) {
        this.userType = userType;
    }

    public org.helianto.core.Entity getEntity() {
        return this.entity;
    }

    public void setEntity(org.helianto.core.Entity entity) {
        this.entity = entity;
    }

    public org.helianto.core.Credential getCredential() {
        return this.credential;
    }

    public void setCredential(org.helianto.core.Credential credential) {
        this.credential = credential;
    }

    public org.helianto.core.User getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.core.User parent) {
        this.parent = parent;
    }

    public Set getRoles() {
        return this.roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
