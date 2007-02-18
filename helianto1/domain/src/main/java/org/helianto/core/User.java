package org.helianto.core;
// Generated 18/02/2007 09:37:41 by Hibernate Tools 3.2.0.beta8


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
 * 				
 * 			
 */
public class User extends org.helianto.core.UserGroup implements java.io.Serializable {

    // Fields    

     private char userType;
     private boolean accountNonExpired;

     // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Entity entity, Identity identity, char userState, char userType) {
        super(entity, identity, userState);        
        this.userType = userType;
    }
    /** full constructor */
    public User(Entity entity, Identity identity, char userState, Set<UserAssociation> parents, Set<UserRole> roles, char userType, boolean accountNonExpired) {
        super(entity, identity, userState, parents, roles);        
       this.userType = userType;
       this.accountNonExpired = accountNonExpired;
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




}


