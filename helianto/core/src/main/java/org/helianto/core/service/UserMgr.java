package org.helianto.core.service;

import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;

public interface UserMgr {
	
    /**
     * <p>An empty <code>Identity</code> to be 
     * submitted to the presentation layer.</p>
     */
    public Identity createIdentity();
    
    /**
     * <p>Persist <code>Identity</code>.</p>
     */
    public void persistIdentity(Identity identity);
    
    /**
     * <p>Persist <code>Credential</code>.</p>
     */
    public void persistCredential(Credential credential);
    
    /**
     * <p>A <code>Credential</code>.</p>
     */
    public Credential createCredential(Identity identity);
    
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
     * <p>List <code>User</code> by <code>Entity</code>.</p>
     */
    public List<User> findUserByEntity(Entity entity);
    


}
