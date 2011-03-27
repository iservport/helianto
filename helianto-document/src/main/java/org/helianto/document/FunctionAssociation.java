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

import org.helianto.core.base.AbstractAssociation;

/**
 * Function parent-child associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_funcassoc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")
public class FunctionAssociation extends AbstractAssociation<Function, Function> {

    private static final long serialVersionUID = 1L;
    
    /**
     * Associated parent function.
     */
    @ManyToOne
    @JoinColumn(name="parentId", nullable=true)
	public Function getParent() {
		return parent;
	}
    
    /**
     * Associated child function.
     */
    @ManyToOne
    @JoinColumn(name="childId", nullable=true)
	public Function getChild() {
		return child;
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

}
