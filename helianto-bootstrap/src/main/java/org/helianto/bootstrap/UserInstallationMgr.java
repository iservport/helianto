package org.helianto.bootstrap;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Service;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserRole;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserInstallationMgr {

    /**
     * <p>Create <code>UserAssociation</code> with a new credential.</p>
     * 
     * @param parent
     * @param credential
     * @param accountNonExpired
     */
    public UserAssociation installUser(UserGroup parent, Credential credential, boolean accountNonExpired);
    
	/**
	 * Install an UserGroup, if does not exist.
	 * 
	 * @param defaultEntity
	 * @param userGroupName
	 * @param reinstall
	 */
	public UserGroup installUserGroup(Entity defaultEntity, String userGroupName, boolean reinstall);

	/**
	 * Install an UserRole, if does not exist.
	 * 
	 * @param userGroup
	 * @param service
	 * @param extension
	 */
	public UserRole installUserRole(UserGroup userGroup, Service service, String extension);

}
