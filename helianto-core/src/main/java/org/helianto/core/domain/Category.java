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
import java.util.List;

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
import org.helianto.core.TrunkEntity;
import org.helianto.core.base.AbstractHumanReadable;
import org.helianto.core.def.CategoryGroup;

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
	, Programmable

{

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private char categoryGroup;
    private String categoryCode;
    private String categoryLabel;
    private String categoryName;
    private char priority;
    private String scriptItems;

    /** 
     * Default constructor
     */
    public Category() {
    	super();
    	setCategoryGroupAsEnum(CategoryGroup.NOT_DEFINED);
    	setCategoryCode("");
    	setCategoryLabel("");
    	setCategoryName("");
    	setPriority('0');
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
		if (getScriptItems()!=null && getScriptItems().trim().length()>0) {
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
	private List<String> scriptList = new ArrayList<String>();
    
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
