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
 * A class to hold last value for internal number lists.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_internalenum",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "typeName"})}
)
public class InternalEnumerator extends AbstractEnumerator implements TrunkEntity {

    private static final long serialVersionUID = 1L;
    private Entity entity;

    /**
     * Empty constructor.
     */
    public InternalEnumerator() {
    	super();
    }

    /**
     * Entity constructor.
     * 
     * @param entity
     */
    public InternalEnumerator(Entity entity) {
    	this();
    	setEntity(entity);
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param typeName
     */
    public InternalEnumerator(Entity entity, String typeName) {
    	this(entity);
    	setTypeName(typeName);
    }

    /**
     * Entity.
     */
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof InternalEnumerator) ) return false;
         InternalEnumerator castOther = (InternalEnumerator) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ));
   }
   
}
