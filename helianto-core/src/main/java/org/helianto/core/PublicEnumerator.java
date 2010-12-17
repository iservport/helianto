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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * A class to hold last value for public number lists.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_publicenum",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "typeName"})}
)
public class PublicEnumerator extends AbstractEnumerator implements RootEntity {

    private static final long serialVersionUID = 1L;
    private Operator operator;

    /**
     * Empty constructor.
     */
    public PublicEnumerator() {
    	super();
    }

    /**
     * Operator constructor.
     * 
     * @param operator
     */
    public PublicEnumerator(Operator operator) {
    	this();
    	setOperator(operator);
    }

    /**
     * Key constructor.
     * 
     * @param operator
     * @param typeName
     */
    public PublicEnumerator(Operator operator, String typeName) {
    	this(operator);
    	setTypeName(typeName);
    }

    /**
     * Entity.
     */
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
		return operator;
	}
    public void setOperator(Operator operator) {
		this.operator = operator;
	}

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PublicEnumerator) ) return false;
         PublicEnumerator castOther = (PublicEnumerator) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ));
   }
   
}
