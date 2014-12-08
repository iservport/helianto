package org.helianto.user.repository;

import org.helianto.core.def.ActivityState;

/**
 * User read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserReadAdapter {
	
    private int userId;
    
    private int userGroupId;
    
    private int contextId;
    
    private int entityId;
    
    private int identityId;
    
    private String userKey;
    
    private String userName;
    
    private char userState;
    
	private Character userGender;
	
    /**
     * Constructor.
     * 
     * @param userId
     * @param contextId
     * @param entityId
     * @param identityId
     * @param userKey
     * @param userName
     * @param userState
     */
	public UserReadAdapter(int userId, int contextId, int entityId, int identityId
			, String userKey, String userName, char userState) {
		super();
		this.userId = userId;
		this.contextId = contextId;
		this.entityId = entityId;
		this.identityId = identityId;
		this.userKey = userKey;
		this.userName = userName;
		this.userState = userState;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param userId
	 * @param userGroupId
	 * @param userKey
	 * @param userName
	 * @param userState
	 * @param userGender
	 */
	public UserReadAdapter(int userId, int userGroupId, String userKey, String userName,
			Character userState, Character userGender) {
		super();
		this.userId = userId;
		this.userGroupId = userGroupId;
		this.userKey = userKey;
		this.userName = userName;
		this.userState = userState;
		this.userGender = userGender;
	}

	public int getUserId() {
		return userId;
	}
	
	public int getUserGroupId() {
		return userGroupId;
	}

	public int getContextId() {
		return contextId;
	}

	public int getEntityId() {
		return entityId;
	}

	public int getIdentityId() {
		return identityId;
	}
	
	public String getUserKey() {
		return userKey;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public boolean isAccountNonExpired() {
		// TODO include expirationDate in User
		//
		return true;
	}
	
	public char getUserState() {
		return userState;
	}
	
    public boolean isAccountNonLocked() {
        if (getUserState()==ActivityState.ACTIVE.getValue()) {
            return true;
        }
        return false;
    }
    
    public Character getUserGender() {
		return userGender;
	}
    
}