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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.internal.AbstractEnumerator;
/**
 * A class to hold last value for internal number lists.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_internalenum",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "typeName"})}
)
public class PrivateSequence 
	extends AbstractEnumerator 
	implements TrunkEntity {

    private static final long serialVersionUID = 1L;
    private Entity entity;

    /**
     * Empty constructor.
     */
    public PrivateSequence() {
    	super();
    }

    /**
     * Entity constructor.
     * 
     * @param entity
     */
    public PrivateSequence(Entity entity) {
    	this();
    	setEntity(entity);
    }

    /**
     * Key constructor.
     * 
     * @param entity
     * @param typeName
     */
    public PrivateSequence(Entity entity, String typeName) {
    	this(entity);
    	setTypeName(typeName);
    	parseTypeName(this);
    }
    
    /**
     * Utility method to allow type name to carry sequence generation information.
     * 
     * <p>
     * Default implementation accepts the pattern ${internalNumberKey}:${startNumber}.
     * </p>
     * 
     * @param privateSequence
     */
    public static void parseTypeName(PrivateSequence privateSequence) {
    	if (privateSequence!=null && privateSequence.getTypeName()!=null) {
        	String[] segments = privateSequence.getTypeName().replace(" ",  "").split(":");
    		if (segments.length>1) {
    			privateSequence.setTypeName(segments[0]);
    			try {
    				int startNumber = Integer.parseInt(segments[1]);
    				privateSequence.setStartNumber(startNumber);
    			}
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
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
         if ( !(other instanceof PrivateSequence) ) return false;
         PrivateSequence castOther = (PrivateSequence) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ));
   }
   
}
