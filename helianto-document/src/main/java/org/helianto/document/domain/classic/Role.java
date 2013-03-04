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

package org.helianto.document.domain.classic;

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

import org.helianto.core.domain.Entity;
import org.helianto.document.base.AbstractDocument;

/**
 * Base class to roles.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see UserGroup
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
public class Role extends AbstractDocument {

	private static final long serialVersionUID = 1L;
    private Set<FunctionAssociation> parentAssociations = new HashSet<FunctionAssociation>();
    private Set<FunctionAssociation> childAssociations = new HashSet<FunctionAssociation>();
    
    /**
     * Default constructor.
     */
    public Role() {
    	super();
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param roleCode
     */
    public Role(Entity entity, String roleCode) {
    	this();
    	setEntity(entity);
    	setDocCode(roleCode);
    }

	/**
     * Parent associations.
     */
    @OneToMany(mappedBy="child")
    public Set<FunctionAssociation> getParentAssociations() {
        return this.parentAssociations;
    }
    public void setParentAssociations(Set<FunctionAssociation> parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    /**
     * Child associations.
     */
    @OneToMany(mappedBy="parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<FunctionAssociation> getChildAssociations() {
        return this.childAssociations;
    }
    public void setChildAssociations(Set<FunctionAssociation> childAssociations) {
        this.childAssociations = childAssociations;
    }

}
