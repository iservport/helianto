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

package org.helianto.document;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;

/**
 * Base class to functions.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_function",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public abstract class AbstractFunction extends AbstractDocument {

	private static final long serialVersionUID = 1L;
    private Set<AbstractFunctionAssociation> parentAssociations = new HashSet<AbstractFunctionAssociation>();
    private Set<AbstractFunctionAssociation> childAssociations = new HashSet<AbstractFunctionAssociation>();
    
    /**
     * Default constructor.
     */
    public AbstractFunction() {
    	super();
    }

    /**
     * Entity constructor.
     * 
     * @param entity
     */
    public AbstractFunction(Entity entity) {
    	this();
    	setEntity(entity);
    }

	/**
     * Parent associations.
     */
    @OneToMany(mappedBy="child")
    public Set<AbstractFunctionAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    public void setParentAssociations(Set<AbstractFunctionAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * Child associations.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<AbstractFunctionAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    public void setChildAssociations(Set<AbstractFunctionAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }

}
