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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Programmable;
import org.helianto.core.PropertyMappable;
import org.helianto.core.domain.enums.CategoryGroup;
import org.helianto.core.domain.enums.ReferenceEnabled;
import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.internal.AbstractHumanReadable;
import org.helianto.core.internal.KeyNameAdapter;
import org.helianto.core.utils.StringListUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Categories.  
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
@javax.persistence.Entity
@Table(name="core_category",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "categoryGroup", "categoryCode"})}
)
public class Category2
	extends AbstractHumanReadable
	implements 
	  TrunkEntity
	, ReferenceEnabled
	, PropertyMappable
	, Programmable
	, KeyNameAdapter
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="entityId")
    private Entity entity;
    
    @Transient
    private Integer entityId;
    
    private char categoryGroup;
    
    /**
     * Service will substitute categoryGroup and categoryGroupType
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="serviceId")
    private Service service;
    
    @Transient
    private Integer serviceId;
    
    @Enumerated(EnumType.STRING)
    private CategoryGroup categoryGroupType;
    
    @Column(length=36)
    private String categoryCode = "";
    
    @Column(length=36)
    private String categoryLabel = "";
    
    @Column(length=64)
    private String categoryName = "";
    
    @Column(length=64)
    private String categoryIcon = "";
    
    private char priority = '0';

    @Column(length=1024)
    private String referenceList = "";

    @Column(length=255)
    private String customStyle = "";

    @Column(length=255)
    private String customWorkflowRoles = "";

	@Column(length=2048)
    private String customProperties = "";
	
	@Column(length=12)
    private String customNumberPattern = "";
	
	@Column(length=64)
	private String patternPrefix = "";

	@Column(length=64)
	private String patternSuffix = "";
	
	private int numberOfDigits = 2;
	
    @Column(length=20)
    private String partnerFilterPattern = "";
    
    @Column(length=255)
    private String scriptItems = "";
    
    private Character activityCode = 'A';

    @Transient
	private List<String> scriptList = new ArrayList<String>();
    
    @Transient
	private int countItems;

    @Transient
	private int countAlerts;
    
	/** 
     * Default constructor
     */
    public Category2() {
    	super();
    	setCategoryGroupAsEnum(CategoryGroup.NOT_DEFINED);
    }
    
    /** 
     * Key constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
     * @deprecated
     */
    public Category2(Entity entity, CategoryGroup categoryGroup, String categoryCode) {
    	this();
    	setEntity(entity);
        setCategoryGroupAsEnum(categoryGroup);
        setCategoryCode(categoryCode);
    }

    /** 
     * Key constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
     */
    public Category2(Entity entity, char categoryGroup, String categoryCode) {
    	this();
    	setEntity(entity);
        setCategoryGroup(categoryGroup);
        setCategoryCode(categoryCode);
    }

    /** 
     * Type constructor
     * 
     * @param categoryGroupType
     */
    public Category2(CategoryGroup categoryGroupType) {
    	this();
        setCategoryGroupType(categoryGroupType);
    }

    /** 
     * Name constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
     * @param categoryName
     * @deprecated
     */
    public Category2(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName) {
    	this(entity, categoryGroup, categoryCode);
    	setCategoryName(categoryName);
    }

    /** 
     * Name constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
     * @param categoryName
     */
    public Category2(Entity entity, char categoryGroup, String categoryCode, String categoryName) {
    	this(entity, categoryGroup, categoryCode);
    	setCategoryName(categoryName);
    }

	/**
	 * Form constructor.
	 * 
	 * @param id
	 * @param categoryGroupType
	 * @param categoryCode
	 * @param categoryLabel
	 * @param categoryName
	 * @param categoryIcon
	 * @param priority
	 * @param referenceList
	 * @param customStyle
	 * @param customWorkflowRoles
	 * @param customProperties
	 * @param customNumberPattern
	 * @param patternPrefix
	 * @param patternSuffix
	 * @param numberOfDigits
	 * @param partnerFilterPattern
	 * @param scriptItems
	 * @param activityCode
	 */
    public Category2(int id
    		, byte[] content
    		, String encoding
    		, String multipartFileContentType
    		, CategoryGroup categoryGroupType
    		, String categoryCode
    		, String categoryLabel
    		, String categoryName
			, String categoryIcon
			, char priority
			, String referenceList
			, String customStyle
			, String customWorkflowRoles
			, String customProperties
			, String customNumberPattern
			, String patternPrefix
			, String patternSuffix
			, int numberOfDigits
			, String partnerFilterPattern
			, String scriptItems
			, Character activityCode
			) {
		super(content, encoding, multipartFileContentType);
		this.id = id;
		this.categoryGroupType = categoryGroupType;
		this.categoryCode = categoryCode;
		this.categoryLabel = categoryLabel;
		this.categoryName = categoryName;
		this.categoryIcon = categoryIcon;
		this.priority = priority;
		this.referenceList = referenceList;
		this.customStyle = customStyle;
		this.customWorkflowRoles = customWorkflowRoles;
		this.customProperties = customProperties;
		this.customNumberPattern = customNumberPattern;
		this.patternPrefix = patternPrefix;
		this.patternSuffix = patternSuffix;
		this.numberOfDigits = numberOfDigits;
		this.partnerFilterPattern = partnerFilterPattern;
		this.scriptItems = scriptItems;
		this.activityCode = activityCode;
	}

	public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Transient
    public Serializable getKey() {
    	return id;
    }

    /**
     * Category entity.
     */
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * <<Transient>> entity id.
     */
    public Integer getEntityId() {
    	if (getEntity()!=null) {
    		return getEntity().getId();
    	}
		return entityId;
	}
    public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

    /**
     * Group of categories.
     * @deprecated
     */
	public char getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(char categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	public void setCategoryGroupAsEnum(CategoryGroup categoryGroup) {
		this.categoryGroup = categoryGroup.getValue();
	}
	
	
    /**
     * Group of categories.
     */
	public CategoryGroup getCategoryGroupType() {
		// workaraund to avoid nulls in existing instances
		if (categoryGroupType==null && categoryGroup!=0) {
			for (CategoryGroup c: CategoryGroup.values()) {
				if (c.getValue()==categoryGroup) {
					return c;
				}
			}
		}
		return categoryGroupType;
	}
	public void setCategoryGroupType(CategoryGroup categoryGroupType) {
		this.categoryGroupType = categoryGroupType;
		setCategoryGroup(categoryGroupType.getValue());
	}
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	
	public Integer getServiceId() {
		if (getService()!=null) {
			return getService().getId();
		}
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

    /**
     * Category code.
     */
    public String getCategoryCode() {
        return this.categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    
    @Transient
    public String getCode() {
    	if (getCategoryCode()!=null) {
    		return getCategoryCode();
    	}
    	return "";
    }

    /**
     * Category label.
     */
    public String getCategoryLabel() {
		return categoryLabel;
	}
    public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

    /**
     * Category name.
     */
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Transient
    public String getName() {
    	if (getCategoryName()!=null) {
    		return getCategoryName();
    	}
    	return "";
    }
    
    public String getCategoryIcon() {
		return categoryIcon;
	}
    public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

    /**
     * Priority.
     */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}
	
    /**
     * Reference list of comma separated values.
     */
    public String getReferenceList() {
		return referenceList;
	}
    public void setReferenceList(String referenceList) {
		this.referenceList = referenceList;
	}
    
    /**
     * References as array.
     */
    public String[] getReferencesAsArray() {
    	return StringListUtils.stringToArray(getReferenceList());
    }
    
    /**
     * Style to be applied.
     * 
     * <p>
     * Classes reading this property are free to parse the content as 
     * a file name, a set of css rules or any other method of choice to
     * apply style. Limited to 255 characters.
     * </p>
     */
	public String getCustomStyle() {
		return customStyle;
	}
	public void setCustomStyle(String customStyle) {
		this.customStyle = customStyle;
	}
	
    /**
     * List of workflow roles.
     * 
     * <p>
     * A csv list of significant role names to be sequentially read in 
     * a workflow. Limited to 255 characters.
     * </p>
     */
	public String getCustomWorkflowRoles() {
		return customWorkflowRoles;
	}
	public void setCustomWorkflowRoles(String customWorkflowRoles) {
		this.customWorkflowRoles = customWorkflowRoles;
	}

    /**
     * <<Transient>> List of workflow roles converted to array.
     */
    public String[] getCustomWorkflowRolesAsArray() {
    	return StringListUtils.stringToArray(getCustomWorkflowRoles());
	}
    
    /**
     * <<Transient>> True if there is at least one workflow role defined.
     */
    public boolean isWorkflowEnabled() {
    	return getCustomWorkflowRolesAsArray().length >0;
	}
    
    /**
     * <<Transient>> Last workflow index, i.e., last index from workflow roles array.
     */
    public int getLastWorkflowIndex() {
    	return getCustomWorkflowRolesAsArray().length - 1;
	}
    
    /**
     * <<Transient>> List of work flow roles converted to map.
     * 
     * <p>
     * Map indexes are created as 1-based. This allows for a 0 stage work flow phase 
     * to be prepended.
     * </p>
     */
    public Map<String, String> getCustomWorkflowRolesAsMap() {
		Map<String, String> workflowRolesMap = new HashMap<String, String>();
    	if (isWorkflowEnabled()) {
    		for (int i=0; i<getCustomWorkflowRolesAsArray().length; i++) {
    			workflowRolesMap.put(String.valueOf(i+1), getCustomWorkflowRolesAsArray()[i]);
    		}
    	}
    	return workflowRolesMap;
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
	
	public Map<String, Object> getCustomPropertiesAsMap() {
		return StringListUtils.propertiesToMap(getCustomProperties());
	}
    
	/**
	 * Custom pattern to be applied at code generation.
	 */
    public String getCustomNumberPattern() {
		return customNumberPattern;
	}
    public void setCustomNumberPattern(String customNumberPattern) {
		this.customNumberPattern = customNumberPattern;
	}
	
    public String getPatternPrefix() {
		return patternPrefix;
	}
    public void setPatternPrefix(String patternPrefix) {
		this.patternPrefix = patternPrefix;
	}
    
    public String getPatternSuffix() {
		return patternSuffix;
	}
    public void setPatternSuffix(String patternSuffix) {
		this.patternSuffix = patternSuffix;
	}
    
    public int getNumberOfDigits() {
		return numberOfDigits;
	}
    public void setNumberOfDigits(int numberOfDigits) {
		this.numberOfDigits = numberOfDigits;
	}
    
    /**
     * Partner (if any) filter pattern.
     * 
     * <p>
     * Partners are not included in helianto-core module, but for simplicity,
     * a method to help filter partners is included here. It is intended to be
     * used if a partner selection is required. This field may provide a csv list 
     * of partner discriminators to narrow the choices.
     * </p>
     */
    public String getPartnerFilterPattern() {
		return partnerFilterPattern;
	}
    public void setPartnerFilterPattern(String partnerFilterPattern) {
		this.partnerFilterPattern = partnerFilterPattern;
	}
    
    /**
     * Can deactivate a category.
     */
    public Character getActivityCode() {
    	if (activityCode==null) {
    		return 'A';
    	}
		return activityCode;
	}
    public void setActivityCode(Character activityCode) {
		this.activityCode = activityCode;
	}
    
    /**
     * <<Transient>> Partner (if any) filter pattern converted to array.
     */
    public String[] getPartnerFilterPatternAsArray() {
		return StringListUtils.stringToArray(getPartnerFilterPattern());
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
    	return StringListUtils.stringToArray(getScriptItems());
	}
	@JsonIgnore
	public void setScriptItemsAsArray(String[] scriptItemsArray) {
		setScriptItems(StringListUtils.arrayToString(scriptItemsArray));
	}
	
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
     * <<Transient>> Add  script content to a list.
     * 
     * @param scriptContent
     */
    public void addScriptContent(String scriptContent) {
    	getScriptList().add(scriptContent);
	}

    /**
     * <<Transient>> Count items.
     */
    public int getCountItems() {
		return countItems;
	}
    public void setCountItems(int countItems) {
		this.countItems = countItems;
	}
    
    /**
     * <<Transient>> Count alerts.
     */
    public int getCountAlerts() {
		return countAlerts;
	}
    public void setCountAlerts(int countAlerts) {
		this.countAlerts = countAlerts;
	}
    
    /**
     * Merger.
     * 
     * @param command
     */
	public Category2 merge(Category2 command) {
		super.merge(command);
		setCategoryGroupType(command.getCategoryGroupType());
		setCategoryCode(command.getCategoryCode());
		setCategoryLabel(command.getCategoryLabel());
		setCategoryName(command.getCategoryName());
		setCategoryIcon(command.getCategoryIcon());
		setPriority(command.getPriority());
		setReferenceList(command.getReferenceList());
		setCustomStyle(command.getCustomStyle());
		setCustomWorkflowRoles(command.getCustomWorkflowRoles());
		setCustomProperties(command.getCustomProperties());
		setCustomNumberPattern(command.getCustomNumberPattern());
		setPatternPrefix(command.getPatternPrefix());
		setPatternSuffix(command.getPatternSuffix());
		setNumberOfDigits(command.getNumberOfDigits());
		setPartnerFilterPattern(command.getPartnerFilterPattern());
		setScriptItems(command.getScriptItems());
		setActivityCode(command.getActivityCode());
		setScriptList(command.getScriptList());
		return this;
	}

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("categoryGroup").append("='").append(getCategoryGroup()).append("' ");
        buffer.append("categoryCode").append("='").append(getCategoryCode()).append("' ");
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
        if ( !(other instanceof Category2) ) return false;
         Category2 castOther = (Category2) other;
         
        return ((this.getEntity()==castOther.getEntity()) 
        		|| ( this.getEntity()!=null && castOther.getEntity()!=null 
        				&& this.getEntity().equals(castOther.getEntity()) ))
            && (this.getCategoryGroup()==castOther.getCategoryGroup())
            && ((this.getCategoryCode()==castOther.getCategoryCode()) 
            		|| ( this.getCategoryCode()!=null && castOther.getCategoryCode()!=null 
            				&& this.getCategoryCode().equals(castOther.getCategoryCode()) ));
    }
   
    /**
     * hashCode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (int) this.getCategoryGroup();
        result = 37 * result + ( getCategoryCode() == null ? 0 : this.getCategoryCode().hashCode() );
        return result;
    }

}
