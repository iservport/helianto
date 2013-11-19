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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
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
import org.helianto.core.def.CategoryGroup;
import org.helianto.core.def.ReferenceEnabled;
import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.internal.AbstractHumanReadable;
import org.helianto.core.number.Sequencer;
import org.helianto.core.utils.StringListUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Categories.  
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_category",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "categoryGroup", "categoryCode"})}
)
public class Category 
	extends AbstractHumanReadable
	implements 
	  TrunkEntity
	, Sequencer
	, ReferenceEnabled
	, PropertyMappable
	, Programmable
{

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private char categoryGroup;
    private String categoryCode = "";
    private String categoryLabel = "";
    private String categoryName = "";
    private char priority = '0';
    private String referenceList = "";
    private String customStyle = "";
    private String customWorkflowRoles = "";
    private String customProperties = "";
    private String customNumberPattern = "";
	private String patternPrefix = "";
	private String patternSuffix = "";
	private int numberOfDigits = 2;
    private String partnerFilterPattern = "";
    private String scriptItems = "";

	// Transients.
	private List<String> scriptList = new ArrayList<String>();
	private int countItems;
	private int countAlerts;
    
    /** 
     * Default constructor
     */
    public Category() {
    	super();
    	setCategoryGroupAsEnum(CategoryGroup.NOT_DEFINED);
    }
    
    /** 
     * Key constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
     */
    public  Category(Entity entity, CategoryGroup categoryGroup, String categoryCode) {
    	this();
    	setEntity(entity);
        setCategoryGroupAsEnum(categoryGroup);
        setCategoryCode(categoryCode);
    }

    /** 
     * Name constructor
     * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
     * @param categoryName
     */
    public  Category(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName) {
    	this(entity, categoryGroup, categoryCode);
    	setCategoryName(categoryName);
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Category entity.
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="entityId")
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Group of categories.
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
     * Category code.
     */
    @Column(length=20)
    public String getCategoryCode() {
        return this.categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    
    /**
     * Category label.
     */
    @Column(length=32)
    public String getCategoryLabel() {
		return categoryLabel;
	}
    public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

    /**
     * Category name.
     */
    @Column(length=64)
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    @Column(length=1024)
    public String getReferenceList() {
		return referenceList;
	}
    public void setReferenceList(String referenceList) {
		this.referenceList = referenceList;
	}
    
    /**
     * References as array.
     */
    @Transient
    public String[] getReferencesAsArray() {
    	return StringListUtils.stringToArray(getReferenceList());
    }
    public void setReferencesAsArray(String[] referenceListAsArray) {
    	setReferenceList(StringListUtils.arrayToString(referenceListAsArray));
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
    @Column(length=255)
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
    @Column(length=255)
	public String getCustomWorkflowRoles() {
		return customWorkflowRoles;
	}
	public void setCustomWorkflowRoles(String customWorkflowRoles) {
		this.customWorkflowRoles = customWorkflowRoles;
	}

    /**
     * <<Transient>> List of workflow roles converted to array.
     */
    @Transient
    public String[] getCustomWorkflowRolesAsArray() {
    	return StringListUtils.stringToArray(getCustomWorkflowRoles());
	}
    public void setWorkflowRolesAsArray(String[] workflowRolesArray) {
    	setCustomWorkflowRoles(StringListUtils.arrayToString(workflowRolesArray));
	}
    
    /**
     * <<Transient>> True if there is at least one workflow role defined.
     */
    @Transient
    public boolean isWorkflowEnabled() {
    	return getCustomWorkflowRolesAsArray().length >0;
	}
    
    /**
     * <<Transient>> Last workflow index, i.e., last index from workflow roles array.
     */
    @Transient
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
    @Transient
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
	@Column(length=512)
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
    
	/**
	 * Custom pattern to be applied at code generation.
	 */
	@Column(length=12)
    public String getCustomNumberPattern() {
		return customNumberPattern;
	}
    public void setCustomNumberPattern(String customNumberPattern) {
		this.customNumberPattern = customNumberPattern;
	}
	
    @Override
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
    @Column(length=20)
    public String getPartnerFilterPattern() {
		return partnerFilterPattern;
	}
    public void setPartnerFilterPattern(String partnerFilterPattern) {
		this.partnerFilterPattern = partnerFilterPattern;
	}
    
    /**
     * <<Transient>> Partner (if any) filter pattern converted to array.
     */
    @Transient
    public String[] getPartnerFilterPatternAsArray() {
		return StringListUtils.stringToArray(getPartnerFilterPattern());
	}
    
    /**
     * Key-value pair list of scripts, separated by comma.
     */
    @Column(length=255)
    public String getScriptItems() {
		return scriptItems;
	}
    public void setScriptItems(String scriptItems) {
		this.scriptItems = scriptItems;
	}
    
    /**
     * <<Transient>> Key-value pair list of scripts converted to array.
     */
    @Transient
    public String[] getScriptItemsAsArray() {
    	return StringListUtils.stringToArray(getScriptItems());
	}
	public void setScriptItemsAsArray(String[] scriptItemsArray) {
		setScriptItems(StringListUtils.arrayToString(scriptItemsArray));
	}
	
    /**
     * <<Transient>> Script list, likely to be loaded at runtime.
     */
    @Transient
    public List<String> getScriptList() {
    	return scriptList;
    }
    public void setScriptList(List<String> scriptList) {
		this.scriptList = scriptList;
	}
    
    /**
     * Adiciona conteúdo de um script à lista.
     * 
     * @param scriptContent
     */
    public void addScriptContent(String scriptContent) {
    	getScriptList().add(scriptContent);
	}

    /**
     * Count items.
     */
    @Transient
    public int getCountItems() {
		return countItems;
	}
    public void setCountItems(int countItems) {
		this.countItems = countItems;
	}
    
    /**
     * Count alerts.
     */
    @Transient
    public int getCountAlerts() {
		return countAlerts;
	}
    public void setCountAlerts(int countAlerts) {
		this.countAlerts = countAlerts;
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
        if ( !(other instanceof Category) ) return false;
         Category castOther = (Category) other; 
         
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
