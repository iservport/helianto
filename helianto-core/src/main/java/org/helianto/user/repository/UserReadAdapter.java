package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.core.domain.enums.ActivityState;
import org.helianto.user.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	private Integer jobId;

	private String jobTitle;

	private Boolean accountNonExpired = true;
	
	private User adaptee;
	
	public UserReadAdapter() {
		super();
	}
	
    /**
     * Constructor.
     * 
     * @param userId
     * @param entityId
     * @param entityAlias
     * @param identityId
     * @param userKey
     * @param userName
     * @param userState
     */
	public UserReadAdapter(int userId
			, int entityId
			, String entityAlias
			, int identityId
			, String userKey
			, String userName
			, char userState
			) {
		this();
		this.userId = userId;
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
     * @param jobId
     * @param jobTitle
     * @param accountNonExpired
     */
	public UserReadAdapter(int userId
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
			, Integer jobId
			, String jobTitle
			, Boolean accountNonExpired
			) {
		this();
		this.userId = userId;
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
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.accountNonExpired = accountNonExpired;
	}
	
    /**
     * Constructor.
     * 
     * @param userId
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
     * @param jobId
     * @param jobTitle
     * @param accountNonExpired
     */
	public UserReadAdapter(int userId
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
			, Integer jobId
			, String jobTitle
			, Integer accountNonExpired
			) {
		this(userId
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
		, jobId
		, jobTitle
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
	
	
	/**
	 * Adaptee contructor.
	 * 
	 * @param adaptee
	 * 
	 */
	public UserReadAdapter(User adaptee) {
		super();
		this.adaptee = adaptee;
	}

	/**
	 * Build an adapter from adaptee.
	 */
	public UserReadAdapter build(){
		if (adaptee==null) {
			throw new RuntimeException("Adaptee canoot be null.");
		}
		this.userId = adaptee.getId();
		if (adaptee.getEntity()!=null) {
			this.entityId = adaptee.getEntityId();
			this.entityAlias = adaptee.getEntity().getAlias();
		}
		if (adaptee.getIdentity()!=null) {
			this.identityId = adaptee.getIdentity().getId();
			this.firstName = adaptee.getIdentity().getIdentityFirstName();
			this.lastName = adaptee.getIdentity().getIdentityLastName();
			this.displayName = adaptee.getIdentity().getDisplayName();
			this.userGender = adaptee.getIdentity().getGender();
			this.userImageUrl = adaptee.getIdentity().getImageUrl();
		}
		this.userKey = adaptee.getUserKey();
		this.userName = adaptee.getUserName();
		this.userState = adaptee.getUserState();
		this.userType = adaptee.getUserType();
//		if (adaptee.getUserJob()!=null) {
//			this.jobId = adaptee.getUserJob().getJobId();
//			this.jobTitle = adaptee.getUserJob().getJobTitle();
//		}
		this.accountNonExpired = adaptee.isAccountNonExpired();
		return this;
	}
	
	/**
	 * Merge adapter back into adaptee.
	 */
	public User merge(){
		adaptee.setId(getUserId());
		adaptee.setUserKey(getUserKey());
		adaptee.setUserName(getFirstName()+" "+getLastName());
		adaptee.setUserState(getUserState());
		adaptee.setUserType(getUserType());
//		if (adaptee.getUserJob()!=null) {
//			adaptee.getUserJob().setJobId(getJobId());
//			adaptee.getUserJob().setJobTitle(getJobTitle());
//		}
		adaptee.setAccountNonExpired(isAccountNonExpired());
		return adaptee; 
	}
	
	@JsonIgnore
	public User getAdaptee() {
		return adaptee;
	}
	public UserReadAdapter setAdaptee(User adaptee) {
		this.adaptee = adaptee;
		return this;
	}

	public int getUserId() {
		return userId;
	}
	
	public int getUserGroupId() {
		return userGroupId;
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
    
    public Integer getJobId() {
		return jobId;
	}
    
    public String getJobTitle() {
		return jobTitle;
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