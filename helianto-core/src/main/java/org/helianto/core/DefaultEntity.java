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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.standalone.NamespaceDefaults;

/**
 * Domain object to indicate which <code>Entity</code>
 * may be taken by default when required by an association.
 *
 * <p>
 * This is usefull for environments where all domain objects may be associated
 * to a single <code>Entity</code>.
 * </p>
 * <p>
 * Each <code>DefaultEntity</code> should have a corresponding partner.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @deprecated see {@link NamespaceDefaults}
 */
@javax.persistence.Entity
@Table(name="core_defaultentity",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "priority"})}
)
public class DefaultEntity  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private int priority;

    /** default constructor */
    public DefaultEntity() {
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
     * Entity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DefaultEntity) ) return false;
		 DefaultEntity castOther = ( DefaultEntity ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         return result;
   }   


}


