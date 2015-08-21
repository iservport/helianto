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
    @JoinColumn(name="publicEntityId", nullable=true)
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

	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

    
}
