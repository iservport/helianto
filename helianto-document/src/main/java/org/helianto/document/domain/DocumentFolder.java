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

package org.helianto.document.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.document.base.AbstractSerializer;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Wraps a number pattern to be used to generate a sequence of documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_doccodebuilder",
	    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "builderCode"})}
	)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("B")
public class DocumentFolder 
	extends AbstractSerializer<Document> {

	private static final long serialVersionUID = 1L;
    private Category category;

    /** 
     * Default constructor.
     */
    public DocumentFolder() {
    	this(null, "");
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public DocumentFolder(Entity entity, String folderCode) {
    	super(entity, folderCode);
    	setNumberPattern("0000");
    	setFolderName("");
    	setContentType(' ');
    }

    /**
     * Category.
     * @see {@link Category}
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    public Category getCategory() {
		return getInternalCategory(category);
	}
    public void setCategory(Category category) {
		this.category = category;
	}
    
	/**
	 * <<Transient>> Optionally delegate to subclasses a method to replace the private field.
	 * 
	 * <p>
	 * Default implementation does not replace the private field.
	 * </p>
	 */
	@Transient
	protected Category getInternalCategory(Category category) {
		return category;
	}
	
	/**
	 * <<Transient>> True if category is not null.
	 */
	@Transient
	public boolean isCategoryEnabled() {
		return getCategory()!=null;
	}
	
   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( !(other instanceof DocumentFolder) ) return false;
         return super.equals(other);
   }
   
}
