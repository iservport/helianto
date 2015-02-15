package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.core.def.ActivityState;

/**
 * User read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserReadAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	protected int userId;
    
	protected int userGroupId;
    
	protected int contextId;
    
	protected int entityId;
    
	protected String entityAlias;
    
	protected int identityId;
    
	private String firstName;

	private String lastName;

	private String displayName;

	protected Character userGender;
	
	protected String userImageUrl;
	
	protected String userKey;
    
	protected String userName;
    
	protected Character userState;
    
	private Character userType;

	private Boolean accountNonExpired = true;
	
	public UserReadAdapter() {
		super();
	}
	
    /**
     * Constructor.
     * 
     * @param userId
     * @param contextId
     * @param entityId
     * @param entityAlias
     * @param identityId
     * @param userKey
     * @param userName
     * @param userState
     * 
     * @deprecated
     */
	public UserReadAdapter(int userId
			, int contextId
			, int entityId
			, String entityAlias
			, int identityId
			, String userKey
			, String userName
			, char userState
			) {
		this();
		this.userId = userId;
		this.contextId = contextId;
		this.entityId = entityId;
		this.entityAlias = entityAlias;
		this.identityId = identityId;
		this.userKey = userKey;
		this.userName = userName;
		this.userState = userState;
	}
	
    /**
     * Constructor.
     * 
     * @param userId
     * @param contextId
     * @param entityId
     * @param entityAlias
     * @param identityId
     * @param firstName
     * @param lastName
     * @param displayName
     * @param userGender
     * @param userImageUrl
     * @param userKey
     * @param userName
     * @param userState
     * @param userType
     * @param accountNonExpired
     */
	public UserReadAdapter(int userId
			, int contextId
			, int entityId
			, String entityAlias
			, int identityId
			, String firstName
			, String lastName
			, String displayName
			, Character userGender
			, String userImageUrl
			, String userKey
			, String userName
			, Character userState
			, Character userType
			, Boolean accountNonExpired
			) {
		this();
		this.userId = userId;
		this.contextId = contextId;
		this.entityId = entityId;
		this.entityAlias = entityAlias;
		this.identityId = identityId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.userGender = userGender;
		this.userImageUrl = userImageUrl;
		this.userKey = userKey;
		this.userName = userName;
		this.userState = userState;
		this.userType = userType;
		this.accountNonExpired = accountNonExpired;
	}
	
    /**
     * Constructor.
     * 
     * @param userId
     * @param contextId
     * @param entityId
     * @param entityAlias
     * @param identityId
     * @param firstName
     * @param lastName
     * @param displayName
     * @param userGender
     * @param userImageUrl
     * @param userKey
     * @param userName
     * @param userState
     * @param userType
     * @param accountNonExpired
     */
	public UserReadAdapter(int userId
			, int contextId
			, int entityId
			, String entityAlias
			, int identityId
			, String firstName
			, String lastName
			, String displayName
			, String userGender
			, String userImageUrl
			, String userKey
			, String userName
			, String userState
			, String userType
			, Integer accountNonExpired
			) {
		this(userId
		, contextId
		, entityId
		, entityAlias
		, identityId
		, firstName
		, lastName
		, displayName
		, userGender!=null && userGender.length()>0 ? userGender.charAt(0) : 'N'
		, userImageUrl
		, userKey
		, userName
		, userState!=null  && userState.length()>0 ? userState.charAt(0) : 'I'
		, userType!=null  && userType.length()>0 ? userType.charAt(0) : 'I'
		, accountNonExpired!=null && accountNonExpired.equals(1)
		);
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
	public UserReadAdapter(int userId
			, int userGroupId
			, String userKey
			, String userName
			, Character userState
			, Character userGender
			) {
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
	
	public String getEntityAlias() {
		return entityAlias;
	}

	public int getIdentityId() {
		return identityId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getUserKey() {
		return userKey;
	}
	
	public String getUserName() {
		return userName;
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
    
    public Character getUserType() {
		return userType;
	}
    
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserReadAdapter other = (UserReadAdapter) obj;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
	
}