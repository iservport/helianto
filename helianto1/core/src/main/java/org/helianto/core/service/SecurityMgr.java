package org.helianto.core.service;

import java.util.Date;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserLog;
import org.helianto.core.type.ActivityState;

public interface SecurityMgr extends UserMgr {
	
	public Identity findIdentityByPrincipal(String principal);
	
	public Credential findCredentialByIdentity(Identity identity);
	
	public UserLog findLastUserLog(Identity identity);
	
    /**
     * Persist a new <code>UserLog<code> and update the <code>Identity</code>
     * last log date.
     * 
     * @param user
     * @param date
     */
	public void persistUserLog(User user, Date date);
	
    /**
     * Auto-create mode enables a new <code>User</code> creation for the 
     * default <code>Entity</code> if necessary.
     */
	public boolean isAutoCreateEnabled();
	
    /**
     * A hook to allow for automatic <code>User</code> creation.
     * 
     * @param identity
     */
	public User autoCreateUser(Identity identity);
    
    /**
     * If password matches verifyPassword returns true, else
     * cleans password and verifyPassword, set passwordDirty
     * to true and returns false.
     */
    public boolean verifyPassword(Credential credential);
    
    /**
     * <code>UserGroup</code> will be unlocked by this action.
     */
    public void activateUser(UserGroup user);
    
    /**
     * <code>UserGroup</code> will be locked by this action.
     */
    public void cancelUser(UserGroup user);
    
    /**
     * <code>UserGroup</code> will be locked by this action. 
     * Unlike <code>cancelUser()</code>, this is supposed to be 
     * a temporary action.
     */
    public void suspendUser(UserGroup user);

}
