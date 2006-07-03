package org.helianto.core;
// Generated 03/07/2006 17:04:48 by Hibernate Tools 3.1.0.beta4

import java.util.Set;


/**
 * 				
 * <p>
 * The user account.
 * </p>
 * <p>
 * An user account represents the association between an <code>Identity</code>
 * and an <code>Entity</code>. Such association allows for a singly identified 
 * actor, i.e. a person or any other organizational <code>Identity</code>, to keep
 * a single authentication scheme and have multiple authorization schemes, one per
 * <code>Entity</code>.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 * 				
 * 			
 */

public class User extends org.helianto.core.UserGroup implements java.io.Serializable {


    // Fields    

     private char userType;
     private boolean accountNonExpired;
     private boolean accountNonLocked;


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Entity entity, Identity identity, char userType, boolean accountNonExpired, boolean accountNonLocked) {
        super(entity, identity);        
        this.userType = userType;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
    }
    
    /** full constructor */
    public User(Entity entity, Identity identity, UserGroup parent, Set<Role> roles, char userType, boolean accountNonExpired, boolean accountNonLocked) {
        super(entity, identity, parent, roles);        
        this.userType = userType;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
    }
    

   
    // Property accessors

    public char getUserType() {
        return this.userType;
    }
    
    public void setUserType(char userType) {
        this.userType = userType;
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
   








}
