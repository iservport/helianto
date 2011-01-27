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

package org.helianto.resource;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractAssociation;

/**
 * Enable <tt>Resource</tt>s and <tt>ResourceGroup</tt>s to associate 
 * to each other in a many-to-many relationship.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="rsrc_resourceAssoc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class ResourceAssociation extends AbstractAssociation<ResourceGroup, ResourceGroup> {

    private static final long serialVersionUID = 1L;

	/** 
	 * Default constructor.
	 */
    public ResourceAssociation() {
    	super();
    }

	/** 
	 * Key constructor.
	 * 
	 * @param parent
	 * @param child
	 */
    public ResourceAssociation(ResourceGroup parent, ResourceGroup child) {
    	this();
    	setParent(parent);
    	if (child!=null) {
    		setChild(child);
    	}
    }

    /**
     * Parent document (lazy loaded).
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", nullable=true)
    public ResourceGroup getParent() {
        return this.parent;
    }

    /**
     * Child document, require two phase store.
     */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="childId", nullable=true)
    public ResourceGroup getChild() {
        return this.child;
    }

    /**
     * <code>DocumentAssociation</code> factory.
     * 
     * @param clazz
     * @param parent
     * @param child
     * @param sequence
     */
    protected static ResourceAssociation resourceAssociationFactory(Class<? extends ResourceAssociation> clazz, ResourceGroup parent, ResourceGroup child, int sequence) {
    	ResourceAssociation resourceAssociation;
		try {
			resourceAssociation = clazz.newInstance();
	        resourceAssociation.setParent(parent);
	        resourceAssociation.setChild(child);
	        parent.getChildAssociations().add(resourceAssociation);
	        child.getParentAssociations().add(resourceAssociation);
	        resourceAssociation.setSequence(sequence);
	        return resourceAssociation;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to create instance of "+clazz, e);
		}
    }
    
    @SuppressWarnings({ "rawtypes" })
	@Override
    protected int compareChild(AbstractAssociation other) {
    	if (getChild()!=null && other.getChild()!=null) {
    		if (getChild().equals(other.getChild()) && getParent()!=null && other.getParent()!=null) {
    			return getParent().compareTo((ResourceGroup) other.getParent());
    		}
    		return getChild().compareTo((ResourceGroup) other.getChild());
    	}
    	return 0;
    }

    /**
     * Natural key info.
     */
    @Transient
    public boolean isKeyEmpty() {
    	if (this.getChild()!=null) {
    		return this.getChild().isKeyEmpty();
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( !(other instanceof ResourceAssociation) ) return false;
         return super.equals(other);
   }

}
