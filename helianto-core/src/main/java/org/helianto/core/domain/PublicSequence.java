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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.internal.AbstractEnumerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * A class to hold last value for public number lists.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_publicenum",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "typeName"})}
)
public class PublicSequence 
	extends AbstractEnumerator 
	implements RootEntity 
{

    private static final long serialVersionUID = 1L;
    private Operator operator;

    /**
     * Empty constructor.
     */
    public PublicSequence() {
    	super();
    }

    /**
     * Key constructor.
     * 
     * @param operator
     * @param typeName
     */
    public PublicSequence(Operator operator, String typeName) {
    	this();
    	setOperator(operator);
    	setTypeName(typeName);
    }

    /**
     * Entity.
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
		return operator;
	}
    public void setOperator(Operator operator) {
		this.operator = operator;
	}

    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
    
   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PublicSequence) ) return false;
         PublicSequence castOther = (PublicSequence) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ));
   }
   
}
