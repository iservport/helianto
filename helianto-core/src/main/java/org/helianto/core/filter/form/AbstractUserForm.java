package org.helianto.core.filter.form;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;

/**
 * Abstract user form.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractUserForm 

	extends AbstractTrunkForm 
	
	implements 
	  IdentityForm
	, UserGroupForm 

{

    private static final long serialVersionUID = 1L;
    private Class<? extends UserGroup> clazz;
    private String userKey;
    private UserGroup parent;
	private List<UserGroup> parentList;
    private String parentUserKey;
    private char userState = ' ';
    private char userGroupType = ' ';
    private char userType = ' ';
    private Identity identity;
    private String principal;
    private String firstName;
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

	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}
	public int getParentId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return 0;
	}

	public String getParentName() {
		return "userGroup";
	}
	
	public List<UserGroup> getParentList() {
		return parentList;
	}
	public void setParentList(List<UserGroup> parentList) {
		this.parentList = parentList;
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
    
    public Collection<Identity> getExclusions() {
        return exclusions;
    }
    public void setExclusions(Collection<Identity> exclusions) {
        this.exclusions = exclusions;
    }

    /**
     * Helper method to clone the form and set an identity.
     * 
     * @param identity
     */
    public CompositeUserForm clone(Identity identity) {
    	try {
    		CompositeUserForm form = (CompositeUserForm) super.clone();
    		form.setIdentity(identity);
    		return form;
		} catch (CloneNotSupportedException e) {
			throw new UnsupportedOperationException("Unable to clone CompositeUserForm.");
		}
    }
    
}
