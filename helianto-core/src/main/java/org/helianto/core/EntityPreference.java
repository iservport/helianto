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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Preference associated to an entity.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_entitypref",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "keyTypeId"})}
)
public class EntityPreference extends AbstractKeyStringValue implements Comparable<EntityPreference> {

	/**
	 * <<Transient>> Delegate to the actual key owner.
	 */
	@Transient
	@Override
	protected Object getKeyOwner() {
		return getEntity();
	}   

    /**
     * Factory method.
     * 
     * @param entity
     * @param keyType
     */
    public static EntityPreference entityPreferenceFactory(Entity entity, KeyType keyType) {
    	EntityPreference entityPreference = new EntityPreference();
        entityPreference.setEntity(entity);
        entityPreference.setKeyType(keyType);
        return entityPreference;
    }

    private static final long serialVersionUID = 1L;
    private Entity entity;

    /** 
     * Default constructor.
     */
    public EntityPreference() {
    	super();
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
     * Implements <code>Comparable</code> using {@link KeyType#getKeyCode()}.
     */
    public int compareTo(EntityPreference other) {
    	return this.getKeyType().getKeyCode().compareTo(other.getKeyType().getKeyCode());
    }
       
   /**
    * equals
    */
   public boolean equals(Object other) {
         if (other instanceof EntityPreference) {
        	 return super.equals(other);
         }
         return false;
   }

}
