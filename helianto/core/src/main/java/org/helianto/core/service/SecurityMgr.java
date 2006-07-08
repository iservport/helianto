package org.helianto.core.service;

import java.util.Date;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;

public interface SecurityMgr {
	
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

}
