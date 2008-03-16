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

package org.helianto.core;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;

/**
 * <p>
 * Categories.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_category",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "categoryGroup", "categoryCode"})}
)
public class Category implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private int categoryGroup;
    private String categoryCode;
    private String categoryName;
    private char priority;

    /** default constructor */
    public Category() {
    }

    // Property accessors
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Group of categories.
     */
	public int getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(int categoryGroup) {
		this.categoryGroup = categoryGroup;
	}
	public void setCategoryGroup(CategoryGroup categoryGroup) {
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
     * Factory method.
     */
    public static Category categoryFactory(Entity entity, CategoryGroup categoryGroup, String categoryCode) {
        Category category = new Category();
        category.setEntity(entity);
        category.setCategoryGroup(categoryGroup);
        category.setCategoryCode(categoryCode);
        category.setPriority('1');
        return category;
    }

    /**
     * <code>Category</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getCategoryQueryStringBuilder() {
        return new StringBuilder("select category from Category category ");
    }

    /**
     * <code>Category</code> natural id query.
     */
    @Transient
    public static String getCategoryNaturalIdQueryString() {
        return getCategoryQueryStringBuilder().append("where category.entity = ? and category.categoryGroup = ?  and category.categoryCode = ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
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
         
        return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
            && (this.getCategoryGroup()==castOther.getCategoryGroup())
            && ((this.getCategoryCode()==castOther.getCategoryCode()) || ( this.getCategoryCode()!=null && castOther.getCategoryCode()!=null && this.getCategoryCode().equals(castOther.getCategoryCode()) ));
    }
   
    /**
     * hashCode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
        result = 37 * result + (int) this.getCategoryGroup();
        result = 37 * result + ( getCategoryCode() == null ? 0 : this.getCategoryCode().hashCode() );
        return result;
    }

}
