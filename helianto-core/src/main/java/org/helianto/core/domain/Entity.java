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

package org.helianto.core.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.PropertyMappable;
import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.utils.StringListUtils;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.springframework.format.annotation.DateTimeFormat;
/**
 *              
 * <p>
 * Domain object to represent the logical namespace of a business
 * organization or individual and provide for proper isolation to 
 * other entities trying to access its related classes.
 * </p>
 * <p>
 * For example, if two equipment sets must be distinguished in 
 * logical spaces to avoid identity collision, they
 * must be associated to different entities. This is also applicable for many
 * other domain classes, like accounts, statements, parts, processes, etc.
 * The <code>Entity</code> is the root for many of such objects and allow
 * for the desirable isolation between two or more organizations, or even
 * smaller units within one organization. In other words, an <code>Entity</code>
 * 'controls' a whole group of domain object instances.
 * </p>
 * <p>
 * A real world entity usually has many related properties, like 
 * address or trade mark. An <code>Entity</code> here, though, is 
 * designed not to hold much information, namely only an unique name. That makes 
 * it flexible enough to be associated to virtually any real world 
 * entity, even individuals. 
 * </p>
 * <p>
 * A small footprint is also desirable for some serialization strategies
 * like Hibernate's (www.hibernate.org) non-lazy loading.
 * </p>
 * @author Mauricio Fernandes de Castro
 *              
 *      
 */
@javax.persistence.Entity
@Table(name="core_entity",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "alias"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("0")
public class Entity 
	implements RootEntity 
	, PropertyMappable {

    private static final long serialVersionUID = 1L;
    private long id;
    private int version;
    private Operator operator;
    private String alias;
    private Date installDate;
    private String nature;
    private String customColors;
    private String customStyle;
    private String customProperties;
    private Identity manager;
    private String externalLogoUrl;
    private char activityState;
    private Set<UserGroup> users = new HashSet<UserGroup>(0);
    private Set<PublicEntity> publicEntities = new HashSet<PublicEntity>(0);
    private List<UserGroup> userList;

    /** 
     * Default constructor.
     */
    public Entity() {
    	setAlias("");
    	setNature("");
    	setCustomStyle("");
    	setCustomProperties("");
    	setExternalLogoUrl("");
    }

    /** 
     * Operator constructor.
     * 
     * @param operator
     */
    public Entity(Operator operator) {
    	this();
    	setOperator(operator);
    }

    /** 
     * Key constructor.
     * 
     * @param operator
     * @param alias
     */
    public Entity(Operator operator, String alias) {
    	this(operator);
    	setAlias(alias);
    	setInstallDate(new Date());
    }

    /** 
     * User constructor.
     * 
     * @param user
     */
    public Entity(User user) {
    	this(user.getOperator());
    	setManager(user.getIdentity());
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Operator, lazy loaded.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    @Transient
    public Locale getLocale() {
    	// TODO create locale field.
    	return Locale.getDefault();
    }

    /**
     * Alias.
     */
    @Column(length=20)
    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    /**
     * Date of installation.
     */
    @DateTimeFormat(style="S-")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getInstallDate() {
		return installDate;
	}
    public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
    
	/**
	 * <<Transient>> True if install date is not null.
	 */
	@Transient
	public boolean isInstalled() {
		return getInstallDate()!=null;
	}
	
	/**
	 * Entity nature.
	 * 
	 * <p>
	 * A list of comma separeted literals matching to public entity discriminators. The service layer
	 * must control the life cycle of such public entities following the literals on this string.</p>
	 */
	@Column(length=128)
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	/**
	 * <<Transient>> Set nature if it does not exist.
	 * 
	 * @param nature
	 */
	@Transient
	public void setNatureIfDoesNotExist(char nature) {
		if (getNature()==null) {
			setNature(Character.toString(nature));
		}
		else if (getNature().indexOf(nature)==-1) {
			if (getNatureAsArray().length>0) {
				setNature(getNature().concat(","));
			}
			setNature(getNature().concat(Character.toString(nature)));
		}
	}
	
	/**
	 * <<Transient>> True if nature already exists.
	 * 
	 * @param nature
	 */
	@Transient
	public boolean hasNature(char nature) {
		return (getNature()!=null && getNature().indexOf(nature)>=0);
	}
	
	/**
	 * <<Transient>> Nature as array.
	 */
	@Transient
	public String[] getNatureAsArray() {
		return StringListUtils.stringToArray(getNature());
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(StringListUtils.arrayToString(natureArray));
	}
	
	/**
	 * Custom colors.
	 * 
	 * <p>
	 * Up to 6 colors in the hex format #rrggbb,#rrggbb, etc.
	 * </p>
	 */
	@Column(length=48)
	public String getCustomColors() {
		return customColors;
	}
	public void setCustomColors(String customColors) {
		this.customColors = customColors;
	}

	/**
	 * <<Transient>> Colors as array.
	 */
	@Transient
	public String[] getCustomColorsAsArray() {
		return StringListUtils.stringToArray(getCustomColors());
	}
	public void setCustomColorsAsArray(String[] customColorsArray) {
		setCustomColors(StringListUtils.arrayToString(customColorsArray));
	}
	
	/**
	 * Custom style.
	 */
	@Column(length=128)
	public String getCustomStyle() {
		return customStyle;
	}
	public void setCustomStyle(String customStyle) {
		this.customStyle = customStyle;
	}

	@Column(length=128)
	public String getCustomProperties() {
		return customProperties;
	}
	public void setCustomProperties(String customProperties) {
		this.customProperties = customProperties;
	}
	
    @Transient
	public Map<String, Object> getCustomPropertiesAsMap() {
		return StringListUtils.propertiesToMap(getCustomProperties());
	}
	public void setPropertiesAsMap(Map<String, Object> propertyMap) {
		setCustomProperties(StringListUtils.mapToProperties(propertyMap));
	}
	
    /**
     * <<Transient>> Convenient to hold the manager during installation
	 * 
	 * <p>
	 * Entity installation requires many steps. Please, check
	 * service layer for installation procedures.
	 * <p>
     */
    @Transient
    public Identity getManager() {
		return manager;
	}
    public void setManager(Identity manager) {
		this.manager = manager;
	}
    
    /**
     * Link to an external logo (like http://mysite/img/log).
     */
	@Column(length=128)
    public String getExternalLogoUrl() {
		return externalLogoUrl;
	}
    public void setExternalLogoUrl(String externalLogoUrl) {
		this.externalLogoUrl = externalLogoUrl;
	}
    
    /**
     * Activity state.
     */
    public char getActivityState() {
		return activityState;
	}
    public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
    public void setActivityStateAsEnum(ActivityState activityState) {
		this.activityState = activityState.getValue();
	}

    /**
     * User group set.
     */
    @OneToMany(mappedBy="entity")
    public Set<UserGroup> getUsers() {
		return users;
	}
	public void setUsers(Set<UserGroup> users) {
		this.users = users;
	}
	
    /**
     * Public entity set.
     */
    @OneToMany(mappedBy="entity")
	public Set<PublicEntity> getPublicEntities() {
		return publicEntities;
	}
	public void setPublicEntities(Set<PublicEntity> publicEntities) {
		this.publicEntities = publicEntities;
	}
	
    /**
     * <<Transient>> User list.
     */
    @Transient
    public List<UserGroup> getUserList() {
		return userList;
	}
	public void setUserList(List<UserGroup> userList) {
		this.userList = userList;
	}

	/**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("alias").append("='").append(getAlias()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Entity) ) return false;
         Entity castOther = (Entity) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         return result;
   }   

}