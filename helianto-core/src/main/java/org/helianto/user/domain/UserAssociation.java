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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.AssociationState;
import org.helianto.core.domain.Credential;
import org.helianto.core.internal.AbstractAssociation;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * User group associations.
 * 
 * @author Mauricio Fernandes de Castro	
 */
@javax.persistence.Entity
@Table(name="core_userassoc", 
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class UserAssociation 
	extends AbstractAssociation<UserGroup, UserGroup> 
	implements java.io.Serializable 
{

    private static final long serialVersionUID = 1L;
    
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date associationDate = new Date();
    
    private char resolution = AssociationState.ACTIVE.getValue();
    
    @Column(length=512)
    private String parsedContent;

    /** 
     * Default constructor.
     */
    public UserAssociation() {
    	super();
    }

    /** 
     * Parent constructor.
     * 
     * @param parent
     */
    public UserAssociation(UserGroup parent) {
    	this();
    	setParent(parent);
    }

    /** 
     * Child constructor.
     * 
     * @param parent
     * @param child
     */
    public UserAssociation(UserGroup parent, UserGroup child) {
    	this(parent);
    	setChild(child);
    }

    /** 
     * Key constructor.
     * 
     * @param parent
     * @param child
     */
    public UserAssociation(UserGroup parent, User child) {
    	this(parent);
    	setChild(child);
    }

    /** 
     * Credential constructor.
     * 
     * @param parent
     * @param childCredential
     */
    public UserAssociation(UserGroup parent, Credential childCredential) {
    	this(parent, new User(parent.getEntity(), childCredential));
    }
    
	@Override
    protected int compareChild(AbstractAssociation<UserGroup, UserGroup> other) {
    	if (this.getChild()!=null && other.getChild()!=null) {
    		return this.getChild().compareTo((UserGroup) other.getChild());
    	}
    	return 0;
    }
       
    /**
     * Natural key info.
     */
//    @Transient
    public boolean isKeyEmpty() {
    	if (this.getChild()!=null) {
    		return this.getChild().isKeyEmpty();
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }
    
    /**
     * Association resolution.
     */
    public char getResolution() {
		return resolution;
	}
    public void setResolution(char resolution) {
		this.resolution = resolution;
	}
    public void setResolutionAsEnum(AssociationState associationState) {
		this.resolution = associationState.getValue();
	}
    
    /**
     * Association date.
     */
    public Date getAssociationDate() {
		return associationDate;
	}
    public void setAssociationDate(Date associationDate) {
		this.associationDate = associationDate;
	}

    /**
     * Parsed content.
     */
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
	}
    
//	@Transient
	public String[] getParsedContentAsArray() {
		if (getParsedContent()!=null) {
			return getParsedContent().split(",");
		}
		return new String[] {};
	}
	public void setParsedContentAsArray(String[] parsedContentArray) {
		setParsedContent(parsedContentArray.toString().replace("[", "").replace("]", ""));
	}

   /**
    * equals
    */
    @Override
   public boolean equals(Object other) {
         if ( !(other instanceof UserAssociation) ) return false;
         return super.equals(other);
   }

}
