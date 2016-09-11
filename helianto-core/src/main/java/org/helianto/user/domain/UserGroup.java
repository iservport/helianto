/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Programmable;
import org.helianto.core.def.ActivityState;
import org.helianto.core.def.CreateIdentity;
import org.helianto.core.def.UserState;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.ContextGroup;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.type.FolderEntity;
import org.helianto.core.internal.AbstractCounter;
import org.helianto.core.internal.KeyNameAdapter;
import org.helianto.core.utils.StringListUtils;
import org.helianto.user.def.UserType;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 			
 * An user account (or group) represents a set of roles within an <code>Entity</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_user",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "userKey"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("G")
@AttributeOverrides({
    @AttributeOverride(name="id", column=@Column(name="userId"))
})
public class UserGroup 
	extends AbstractCounter
	implements 
	  FolderEntity
	, Comparable<UserGroup>
	, Programmable
	, KeyNameAdapter
{
	
	/**
	 * <<Transient>> Exposes the discriminator.
	 */

	public char getDiscriminator() {
		return 'G';
	}

    private static final long serialVersionUID = 1L;
    
    @Column(length=40)
    private String userKey = "";
    
    @Column(length=64)
    private String userName = "";
    
    private Locale locale;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEvent = new Date();
    
    private Character userState = UserState.ACTIVE.getValue();
    
    private Character userType = ' ';
    
    private boolean accountNonExpired = true;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    private Category category;
    
    @Transient
    private Integer categoryId = 0;
    
    @Transient
    private char createIdentity = CreateIdentity.REJECT.getValue();
    
    @Transient
    private Character priority = '9';
    
	@Lob
    private String userDesc = "";
    
    @Column(length=128)
    private String nature = "";
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="jobId", nullable=true)
    private UserGroup job;
    
    @Transient
    private int jobId;
    
    @Transient
    private String jobTitle;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="areaId", nullable=true)
    private UserGroup area;
    
    @Transient
    private int areaId;
    
    @Transient
    private String areaName;
    
    private int minimalEducationRequirement;
    
    private int minimalExperienceRequirement;
    
    @Column(length=255)
    private String scriptItems = "";

    @JsonIgnore
    @OneToMany(mappedBy="child")
    private Set<UserAssociation> parentAssociations = new HashSet<UserAssociation>();
    
    @JsonIgnore
    @OneToMany(mappedBy="parent")
    private Set<UserAssociation> childAssociations = new HashSet<UserAssociation>();
    
    @Transient
    private List<UserAssociation> childAssociationList = new ArrayList<UserAssociation>();
    
    @JsonIgnore
    @OneToMany(mappedBy="userGroup")
	private Set<UserRole> roles = new HashSet<UserRole>();
	
    @Transient
    private List<UserRole> roleList = new ArrayList<UserRole>();
    
    private Integer notificationDay = 0;
    
	/** 
	 * Empty constructor.
	 */
    public UserGroup() {
    	super();
    }

	/** 
	 * Entity constructor.
	 * 
	 * @param entity
	 */
    public UserGroup(Entity entity) {
    	this();
    	setEntity(entity);
    }

	/** 
	 * User group constructor.
	 * 
	 * @param entity
	 * @param userKey
	 */
    public UserGroup(Entity entity, String userKey) {
    	this(entity);
    	setUserKey(userKey);
    }

	/** 
	 * User group constructor.
	 * 
	 * @param entity
	 * @param contextGroup
	 */
    public UserGroup(Entity entity, ContextGroup contextGroup) {
    	this(entity);
    	setUserKey(contextGroup.getContextGroupCode());
    	setUserName(contextGroup.getContextGroupName());
    	setUserType(contextGroup.getUserType());
    }

	/**
	 * Read constructor.
	 * 
	 * @param id
	 * @param userKey
	 * @param userName
	 * @param locale
	 * @param lastEvent
	 * @param userState
	 * @param userType
	 * @param accountNonExpired
	 * @param userDesc
	 * @param nature
	 * @param minimalEducationRequirement
	 * @param minimalExperienceRequirement
	 * @param scriptItems
	 */
	public UserGroup(int id
			, String userKey
			, String userName
			, Locale locale
			, Date lastEvent
			, char userState
			, char userType
			, Boolean accountNonExpired
			, String userDesc
			, String nature
			, int minimalEducationRequirement
			, int minimalExperienceRequirement
			, String scriptItems
			) {
		this();
		setId(id);
    	setUserKey(userKey);
    	setUserName(userName);
    	setLocale(locale);
    	setLastEvent(lastEvent);
    	setUserState(userState);
    	setUserType(userType);
    	setAccountNonExpired(accountNonExpired);
    	setUserDesc(userDesc);
    	setNature(nature);
    	setMinimalEducationRequirement(minimalEducationRequirement);
    	setMinimalExperienceRequirement(minimalExperienceRequirement);
    	setScriptItems(scriptItems);
	}


    /**
     * <<Transient>> Convenience to return Operator.
     */
    @JsonIgnore
    public Operator getOperator() {
		return getEntity().getOperator();
	}

    /**
     * User key.
     */
    public String getUserKey() {
        return getInternalUserKey();
    }
    
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    

    public boolean isUserKeyEmpty() {
    	return getUserKey()==null || (getUserKey()!=null && getUserKey().length()==0);
    }
    
    /**
     * Satisfies <code>FoderEntity</code> interface to show groups as folders.
     */

    public String getFolderCode() {
    	if (!isUserKeyEmpty()) {
    		return getUserKey();
    	}
    	return "";
    }

    /**
     * User name.
     */
	public String getUserName() {
		return getInternalUserName();
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    /**
     * Satisfies <code>FoderEntity</code> interface to show groups as folders.
     */

    public String getFolderName() {
    	if (getUserName()!=null && getUserName().length()>0) {
    		return getUserName();
    	}
    	return "";
    }
    
    /**
     * Satisfies <code>FoderEntity</code> interface to show groups as folders.
     */

    public String getFolderDecorationUrl() {
    	return "";
    }
    
	/**
	 * Defaults to userName field.
	 */

	protected String getInternalUserName() {
		if (userName!=null && userName.length()>0) {
			return userName;
		}
		return getUserKey();
	}
	
    /**
     * <<Transient>> Subclasses may override to customize userKey creation.
     */

    protected String getInternalUserKey() {
    	return this.userKey;
    }

    /**
     * Locale.
     */
    public Locale getLocale() {
		return locale;
	}
    public void setLocale(Locale locale) {
		this.locale = locale;
	}

    /**
     * Last event
     */
    public Date getLastEvent() {
		return lastEvent;
	}
	public void setLastEvent(Date lastEvent) {
		this.lastEvent = lastEvent;
	}
	
    /**
     * Users or groups may be deactivated using this field.
     */
    public Character getUserState() {
        return this.userState;
    }
    public void setUserState(Character userState) {
        this.userState = userState;
    }
    public void setUserStateAsEnum(UserState userState) {
        this.userState = userState.getValue();
    }
    
    /**
     * True if user state is active.
     */
    public boolean isAccountNonLocked() {
        if (getUserState()==ActivityState.ACTIVE.getValue()) {
            return true;
        }
        return false;
    }
    
    /**
     * Primary user and group distinction.
     */
     public Character getUserType() {
		return userType;
	}
     public void setUserType(Character userType) {
 		this.userType = userType;
 	}
	public void setUserTypeAsEnum(UserType userType) {
		if (userType != null) {
			this.userType = userType.getValue();
		}
		this.userType = UserType.INTERNAL.getValue();
	}
	
	/**
	 * True if user group is system.
	 */
	public boolean isSystemGroup() {
		return this.userType==UserType.SYSTEM.getValue();
	}

    /**
     * AccountNonExpired getter.
     */
    public boolean isAccountNonExpired() {
//    	if (getUserState()!=null 
//    			&& (getUserState().equals(UserState.INACTIVE.getValue()) 
//    					|| getUserState().equals(UserState.PENDING.getValue()))) {
//    		System.out.println(getUserState().equals(UserState.INACTIVE.getValue())+" - "+getUserState().equals(UserState.PENDING.getValue()));
//    		return false;
//    	}
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

	/**
     * <<Transient>> May be used by the presentation layer to
     * signal automatic identity creation"
     */

    public char getCreateIdentity() {
		return createIdentity;
	}
	public void setCreateIdentity(char createIdentity) {
		this.createIdentity = createIdentity;
	}
	public void setCreateIdentityAsEnum(CreateIdentity createIdentity) {
		this.createIdentity = createIdentity.getValue();
	}
	
    /**
     * Group nature, as a keyword csv.
     */
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	/**
	 * <<Transient>> Nature as array.
	 */

	public String[] getNatureAsArray() {
		return StringListUtils.stringToArray(getNature());
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(StringListUtils.arrayToString(natureArray));
	}
	
	/**
	 * <<Transient>> Convenience to read custom colors as array from entity.
	 */
	@JsonIgnore
	public String[] getCustomColorsAsArray() {
		return getEntity().getCustomColorsAsArray();
	}
	
	public Character getPriority() {
		if (priority==null) {
			return '9';
		}
		return priority;
	}
	public void setPriority(Character priority) {
		this.priority = priority;
	}
	
	/**
	 * User or group description.
	 */
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	
	/**
	 * User group representing a job
	 */
	public UserGroup getJob() {
		return job;
	}
	public void setJob(UserGroup job) {
		this.job = job;
	}
	
	/**
	 * <<Transient>> job id.
	 */
	public Integer getJobId() {
		if (getJob()!=null) {
			return getJob().getId();
		}
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	/**
	 * Job title helper method.
	 */
	public String getJobTitle() {
		if (getJob()!=null) {
			return getJob().getUserName();
		}
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * User group representing an area
	 */
	public UserGroup getArea() {
		return area;
	}
	public void setArea(UserGroup area) {
		this.area = area;
	}
	
	/**
	 * <<Transient>> User group area id
	 */
	public int getAreaId() {
		if (getArea()!=null) {
			return getArea().getId();
		}
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaName() {
		if (getArea()!=null) {
			return getArea().getAreaName();
		}
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	/**
	 * Education minimal requirement.
	 * 
	 * <p>
	 * Years spent on basic degree required as a minimum to perform the function.
	 * </p>
	 */
	public int getMinimalEducationRequirement() {
		return minimalEducationRequirement;
	}
	public void setMinimalEducationRequirement(int minimalEducationRequirement) {
		this.minimalEducationRequirement = minimalEducationRequirement;
	}
	
	/**
	 * Experience minimal requirement.
	 * 
	 * <p>
	 * Years of experience required as a minimum to perform the function.
	 * </p>
	 */
	public int getMinimalExperienceRequirement() {
		return minimalExperienceRequirement;
	}
	public void setMinimalExperienceRequirement(int minimalExperienceRequirement) {
		this.minimalExperienceRequirement = minimalExperienceRequirement;
	}
	
	/**
	 * Category attached to user or group.
	 */
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * <<Transient>> category id.
	 */
    public Integer getCategoryId() {
		return categoryId;
	}
    public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
    
    /**
     * Key-value pair list of scripts, separated by comma.
     */
    public String getScriptItems() {
		return scriptItems;
	}
    public void setScriptItems(String scriptItems) {
		this.scriptItems = scriptItems;
	}
    
    /**
     * <<Transient>> Key-value pair list of scripts converted to array.
     */

    public String[] getScriptItemsAsArray() {
		if (getScriptItems()!=null) {
			return getScriptItems().replace(" ", "").split(",");
		}
		return new String[] {};
	}
	public void setScriptItemsAsArray(String[] scriptItemsArray) {
		setScriptItems(scriptItemsArray.toString().replace("[", "").replace("]", ""));
	}
	
	/*
	 * Transient field to hold actual script list.
	 */
	@Transient
	private List<String> scriptList = new ArrayList<String>();
    
    /**
     * <<Transient>> Script list, likely to be loaded at runtime.
     */

    public List<String> getScriptList() {
    	return scriptList;
    }
    public void setScriptList(List<String> scriptList) {
		this.scriptList = scriptList;
	}
    
    /**
     * Add the content of a script to the list.
     * 
     * @param scriptContent
     */
    public void addScriptContent(String scriptContent) {
    	getScriptList().add(scriptContent);
	}
    
    @Override
    public Serializable getKey() {
    	return getId();
    }

    @Override
    public String getCode() {
    	if (getUserKey()!=null) {
    		return getUserKey();
    	}
    	return "";
    }

    @Override
    public String getName() {
    	if (getUserName()!=null) {
    		return getUserName();
    	}
    	return "";
    }

    /**
     * Parent associations.
     */
    public Set<UserAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    public void setParentAssociations(Set<UserAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * Child associations.
     */
    public Set<UserAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    public void setChildAssociations(Set<UserAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }

    /**
     * <<Transient>> Child association list.
     */
    public List<UserAssociation> getChildAssociationList() {
		return childAssociationList;
	}
	public void setChildAssociationList(List<UserAssociation> childAssociationList) {
		this.childAssociationList = childAssociationList;
	}

    /**
     * Roles for this group.
     */
    public Set<UserRole> getRoles() {
        return this.roles;
    }
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    /**
     * <<Transient>> User role list.
     */
	public List<UserRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}
	
	public Integer getNotificationDay() {
		return notificationDay;
	}
	public void setNotificationDay(Integer notificationDay) {
		this.notificationDay = notificationDay;
	}
	
	/**
	 * Merger.
	 * 
	 * @param command
	 */
	public UserGroup merge(UserGroup command) {
		setUserName(command.getUserName());
		setLocale(command.getLocale());
		setLastEvent(command.getLastEvent());
		setUserState(command.getUserState());
		setUserType(command.getUserType());
		setAccountNonExpired(command.isAccountNonExpired());
		setPriority(command.getPriority());
		setUserDesc(command.getUserDesc());
		setNature(command.getNature());
		setMinimalEducationRequirement(command.getMinimalEducationRequirement());
		setMinimalExperienceRequirement(command.getMinimalExperienceRequirement());
		setScriptItems(command.getScriptItems());
		setNotificationDay(command.getNotificationDay());
		return this;
	}

	/**
	 * Comparator.
	 * 
	 * @param other
	 */
	public int compareTo(UserGroup other) {
		if (getUserKey()!=null && other!=null && other.getUserKey()!=null) {
			return getUserKey().compareTo(other.getUserKey());
		}
		return 1;
	}   

    /**
     * toString
     * 
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("userKey").append("='").append(getUserKey()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof UserGroup) ) return false;
         UserGroup castOther = (UserGroup) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getUserKey()==castOther.getUserKey()) || ( this.getUserKey()!=null && castOther.getUserKey()!=null && this.getUserKey().equals(castOther.getUserKey()) ));
   }
   
   /**
    * hashCode
    */
    @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getUserKey() == null ? 0 : this.getUserKey().hashCode() );
         return result;
   }

}
