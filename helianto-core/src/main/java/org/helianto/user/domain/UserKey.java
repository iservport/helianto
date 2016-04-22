package org.helianto.user.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.KeyType;
import org.helianto.core.internal.AbstractKeyStringValue;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The content of a key associated to an user.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_userKey",
    uniqueConstraints = {@UniqueConstraint(columnNames={"userGroupId", "keyTypeId"})}
)
public class UserKey extends AbstractKeyStringValue {

	/**
	 * Delegate to the actual key owner.
	 */
	@Override
	protected Object getKeyOwner() {
		return getUserGroup();
	}   

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userGroupId", nullable=true)
    private UserGroup userGroup;
    
    @Transient
    private Integer userGroupId;
    
    /**
     * Constructor.
     */
    public UserKey() {
		super();
	}
    
    /**
     * Key constructor.
     * 
     * @param userGroup
     * @param keyType
     */
    public UserKey(UserGroup userGroup, KeyType keyType) {
		this();
		setUserGroup(userGroup);
		setKeyType(keyType);
	}

    /**
     * Value constructor.
     * 
     * @param userGroup
     * @param keyType
     * @param keyValue
     */
    public UserKey(UserGroup userGroup, KeyType keyType, String keyValue) {
		this(userGroup, keyType);
		setKeyValue(keyValue);
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Integer getUserGroupId() {
		if (getUserGroup()!=null) {
			return getUserGroup().getId();
		}
		return userGroupId;
	}
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public UserKey merge(UserKey command) {
		super.merge(command);
		return this;
	}
    
}
