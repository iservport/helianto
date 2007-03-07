package org.helianto.core.service;

import java.util.Collection;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.hibernate.filter.IdentityFilter;

public interface UserMgr extends CoreMgr {
	
    /**
     * <p>An empty <code>Identity</code> to be 
     * submitted to the presentation layer.</p>
     */
    public Identity createEmptyIdentity();
    
    /**
     * <p>Persist <code>Identity</code>.</p>
     */
    public void persistIdentity(Identity identity);
    
    /**
     * <p>Selects an <code>Identity</code> list.
     * 
     * @param filter
     * @param exclusions list to be removed after selection
     */
    public List<Identity> findIdentities(IdentityFilter filter, Collection<Identity> exclusions);
    
    /**
     * <p>A <code>Credential</code> with an
     * associated new <code>Identity</code>.</p>
     */
    public Credential createCredentialAndIdentity();
    
    /**
     * <p>A <code>Credential</code>.</p>
     */
    public Credential createCredential(Identity identity);
    
    /**
     * <p>Persist <code>Credential</code>.</p>
     */
    public void persistCredential(Credential credential);
    
    
    
    /**
     * <p>A simple <code>User</code> creation given an <code>Endity</code>.</p>
     */
    public User createUser(Entity entity);
    
    /**
     * <p>Full <code>User</code> creation.</p>
     */
    public User createUser(Identity identity, Entity entity);
    
    /**
     * <p>Persist the <code>User</code>.</p>
     */
    public void persistUser(User user);
    
    /**
     * <p>List <code>UserGroup</code> by <code>Entity</code>.</p>
     */
    public List<UserGroup> findUserByEntity(Entity entity);
    
    
    /**
     * <code>UserGroup</code> will be unlocked by this action
     * if the <code>Credential</code> is active.
     */
    public void activateUser(UserGroup user, Credential credential);
    
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
