package org.helianto.user.form;

import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.domain.Identity;
import org.helianto.core.form.IdentityForm;
import org.helianto.core.form.IdentityIdForm;
import org.helianto.core.form.internal.AbstractTrunkForm;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;


/**
 * Abstract user form.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractUserForm 

	extends AbstractTrunkForm 
	
	implements 
	  IdentityForm
	, IdentityIdForm
	, UserGroupForm 
	, UserGroupIdForm 

{

    private static final long serialVersionUID = 1L;
    private Class<? extends UserGroup> clazz;
    private String userKey;
    private UserGroup userGroup;
    private String parentUserKey;
    private char userState = ' ';
    private char userGroupType = ' ';
    private char userType = ' ';
    private Identity identity;
    private String principal;
    private String firstName;
    private String lastName;
    private String nameLike;
	private char gender;
	private char identityType;
	private char notification;
	private Collection<Identity> exclusions;
	
    public void reset() {
    	setUserState(' ');
    	setUserType(' ');
    	setExclusions(new HashSet<Identity>(0));
    }
    
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public int getUserGroupParentId() {
		if (getUserGroup()!=null) {
			return getUserGroup().getId();
		}
		return 0;
	}

	public int getUserGroupId() {
		if (getUserGroup()!=null) {
			return getUserGroup().getId();
		}
		return 0;
	}

	public String getParentUserKey() {
		return parentUserKey;
	}
	public void setParentUserKey(String parentUserKey) {
		this.parentUserKey = parentUserKey;
	}

    public char getUserGroupType() {
        return this.userGroupType;
    }
    public void setUserGroupType(char userGroupType) {
        this.userGroupType = userGroupType;
    }

    public char getUserType() {
        return this.userType;
    }
    public void setUserType(char userType) {
        this.userType = userType;
    }

    public char getUserState() {
        return this.userState;
    }
    public void setUserState(char userState) {
        this.userState = userState;
    }
    
	public Class<? extends UserGroup> getClazz() {
		return this.clazz;
	}
	public void setClazz(Class<? extends UserGroup> clazz) {
		this.clazz = clazz;
	}

	public char getDiscriminator() {
		if (clazz.equals(UserGroup.class)) {
			return 'G'; 
		}
		if (clazz.equals(User.class)) {
			return 'U'; 
		}
		return ' ';
	}
	public void setDiscriminator(char discriminator) {
		if (discriminator=='G') {
			clazz = UserGroup.class; 
		}
		else if (discriminator=='U') {
			clazz = User.class; 
		}
		else {
			clazz = null;
		}
	}
	
    public Identity getIdentity() {
        return this.identity;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    
    public long getIdentityId() {
    	if (getIdentity()!=null) {
    		return getIdentity().getId();
    	}
    	return 0;
    }
    
    public String getPrincipal() {
		return principal;
	}
    public void setPrincipal(String principal) {
		this.principal = principal;
	}
    public String getFirstName() {
		return firstName;
	}
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getNameLike() {
		return nameLike;
	}
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}
	
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public char getIdentityType() {
		return identityType;
	}
	public void setIdentityType(char identityType) {
		this.identityType = identityType;
	}
	
	public char getNotification() {
		return notification;
	}
	public void setNotification(char notification) {
		this.notification = notification;
	}
	
    public Collection<Identity> getExclusions() {
        return exclusions;
    }
    public void setExclusions(Collection<Identity> exclusions) {
        this.exclusions = exclusions;
    }

}