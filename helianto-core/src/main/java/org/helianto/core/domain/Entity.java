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
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.helianto.core.domain.enums.ActivityState;
import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.utils.StringListUtils;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextName", "alias"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("0")
public class Entity
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private int version;

	@Column(length=20)
	private String contextName = "DEFAULT";

	@Column(length=64)
    private String alias = "";

    @Column(length=20)
    private String pun = "";

    @Column(length=36)
    private String entityCode = "";
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date installDate = new Date();
    
    private char entityType = 'C';
    
	@Enumerated(EnumType.STRING) @Column(length=20)
    private EntityNature nature = EntityNature.ORGANIZATION;
    
	@Column(length=128)
    private String customColors = "";
    
	@Column(length=128)
    private String customStyle = "";
    
	@Column(length=128)
    private String customProperties = "";
    
	@Column(length=1024)
    private String summary = "";
    
	@Transient
	private Identity manager;
    
	@Column(length=128)
    private String externalLogoUrl = "";
    
    private char activityState = 'A';
    
    @Column(length=128)
    private String entityName = "";
    
    @Column(length=128)
    private String entityDomain = "";

    @Column(length=12)
    private String stateCode = "";

    private Integer cityId;
    
    @Transient
    private List<UserGroup> userList;

    /** 
     * Default constructor.
     */
    public Entity() {
    	super();
    }

    /**
     * City constructor.
     *
     * @param contextName the context name
     * @param alias teh entity alias
     * @param entityName the entity name
     * @param cityId id of the city
     * @param stateCode the state code
     * @param entityType entity type
     * @param pun public unique number, optional
     */
    public Entity(String contextName, String alias, String entityName, Integer cityId, String stateCode, char entityType, String pun) {
        this();
        setContextName(Objects.requireNonNull(contextName));
        setAlias(Objects.requireNonNull(alias));
        setEntityName(entityName);
        setCityId(Objects.requireNonNull(cityId));
        setStateCode(stateCode);
        setEntityType(entityType);
        setPun(pun);
    }

    /**
     * Key constructor.
     * 
     * @param contextName
     * @param alias
     * @deprecated
     */
    public Entity(String contextName, String alias) {
    	this();
    	setContextName(contextName);
    	setAlias(alias);
   }

//    /**
//     * User constructor.
//     *
//     * @param user
//     * @deprecated
//     */
//    public Entity(User user) {
//    	this(user.getOperator());
//    	setManager(user.getIdentity());
//    }
//
//    /**
//     * City constructor.
//     *
//     * @param city
//     * @param alias
//     */
//    public Entity(City city, String alias) {
//    	this();
//    	setCity(Objects.requireNonNull(city, "A city is required"));
//    	setOperator(city.getContext());
//    	setAlias(alias);
//    }
    
    /**
     * Prototype constructor.
     * 
     * @param contextName
     * @param prototype
     * @deprecated
     */
    public Entity(String contextName, Entity prototype) {
		this();
		this.contextName = prototype.getContextName();
		this.alias = prototype.getAlias();
		this.entityType = prototype.getEntityType();
		this.nature = prototype.getNature();
		this.customColors = prototype.getCustomColors();
		this.customStyle = prototype.getCustomStyle();
		this.customProperties = prototype.getCustomProperties();
		this.summary = prototype.getSummary();
		this.externalLogoUrl = prototype.getExternalLogoUrl();
		this.entityDomain = prototype.getEntityDomain();
		this.cityId = prototype.getCityId();
	}

	public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version.
     */
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public Locale getLocale() {
    	// TODO create locale field.
    	return Locale.getDefault();
    }

    /**
     * Alias.
     */
    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPun() {
        return pun;
    }
    public void setPun(String pun) {
        this.pun = pun;
    }

    /**
     * Entity code.
     */
    public String getEntityCode() {
		return entityCode;
	}
    public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
    
    /**
     * Date of installation.
     */
    public Date getInstallDate() {
		return installDate;
	}
    public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
    
	/**
	 * <<Transient>> True if install date is not null.
	 */
//	@Transient
	public boolean isInstalled() {
		return getInstallDate()!=null;
	}
	
	public char getEntityType() {
		return entityType;
	}
	public void setEntityType(char entityType) {
		this.entityType = entityType;
	}
	
	/**
	 * Entity nature
	 */
	public EntityNature getNature() {
		return nature;
	}
	public void setNature(EntityNature nature) {
		this.nature = nature;
	}
	
	/**
	 * Custom colors.
	 * 
	 * <p>
	 * Up to 6 colors in the hex format #rrggbb,#rrggbb, etc.
	 * </p>
	 */
	public String getCustomColors() {
		return customColors;
	}
	public void setCustomColors(String customColors) {
		this.customColors = customColors;
	}

	/**
	 * <<Transient>> Colors as array.
	 */
	public String[] getCustomColorsAsArray() {
		return StringListUtils.stringToArray(getCustomColors());
	}
	public void setCustomColorsAsArray(String[] customColorsArray) {
		setCustomColors(StringListUtils.arrayToString(customColorsArray));
	}
	
	/**
	 * Custom style.
	 */
	public String getCustomStyle() {
		return customStyle;
	}
	public void setCustomStyle(String customStyle) {
		this.customStyle = customStyle;
	}

	/**
	 * Custom properties.
	 */
	public String getCustomProperties() {
		return customProperties;
	}
	public void setCustomProperties(String customProperties) {
		this.customProperties = customProperties;
	}
	
	/**
	 * Summary.
	 */
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
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
    public Identity getManager() {
		return manager;
	}
    public void setManager(Identity manager) {
		this.manager = manager;
	}
    
    /**
     * Link to an external logo (like http://mysite/img/log).
     */
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
     * Domain associated with the entity. e.g. helianto.org.
     */
    public String getEntityDomain() {
		return entityDomain;
	}
    public void setEntityDomain(String entityDomain) {
		this.entityDomain = entityDomain;
	}
    
    /**
     * Entity name.
     */
    public String getEntityName() {
		return entityName;
	}
    public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
    
    /**
     * City id.
     */
    public Integer getCityId() {
		return cityId;
	}
    public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * <<Transient>> User list.
     */
    public List<UserGroup> getUserList() {
		return userList;
	}
	public void setUserList(List<UserGroup> userList) {
		this.userList = userList;
	}

    public Entity verify(String contextName) {
        if (this.contextName!=null && this.contextName.equals(contextName)) {
            return this;
        }
        throw new IllegalArgumentException("Invalid context!");
    }

	/**
	 * Merger.
	 * 
	 * @param command // TODO add entityDesc and Locale
	 */
	public Entity merge(Entity command) {
        setPun(command.getPun());
        setEntityCode(command.getEntityCode());
		setInstallDate(command.getInstallDate());
		setEntityType(command.getEntityType());
		setNature(command.getNature());
		setCustomColors(command.getCustomColors());
		setCustomStyle(command.getCustomStyle());
		setCustomProperties(command.getCustomProperties());
		setSummary(command.getSummary());
		setManager(command.getManager());
		setExternalLogoUrl(command.getExternalLogoUrl());
		setActivityState(command.getActivityState());
		setEntityName(command.getEntityName());
        setEntityDomain(command.getEntityDomain());
        setCityId(command.getCityId());
		return this;
	}

}
