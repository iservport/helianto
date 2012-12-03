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

package org.helianto.document.base;

import org.helianto.core.Controllable;
import org.helianto.core.domain.Entity;


/**
 * Base class to entity records that control a date.
 *  
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractPrivateControl extends AbstractRepeatable implements Controllable {

    private static final long serialVersionUID = 1L;

    /** 
     * Default constructor.
     */
    public AbstractPrivateControl() {
    	super();
    }
    
    /** 
     * Entity constructor.
     * 
     * @param entity
     * @param internalNumber
     */
    public AbstractPrivateControl(Entity entity, long internalNumber) {
    	this();
    	setEntity(entity);
    	setInternalNumber(internalNumber);
    }
    
    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractPrivateControl) ) return false;
		 AbstractPrivateControl castOther = ( AbstractPrivateControl ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
}


