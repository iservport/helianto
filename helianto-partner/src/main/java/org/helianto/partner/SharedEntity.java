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

package org.helianto.partner;


import java.util.Date;

import org.helianto.core.Entity;

/**
 * 				
 * <p>
 * Represents a shareable relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 *         
 */
public class SharedEntity  implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private Entity sharedEntity;
    private PartnerRegistry partnerRegistry;
    private char shareMode;
    private Date sharedSince;

     // Constructors

    /** default constructor */
    public SharedEntity() {
    }

    // Property accessors
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public Entity getSharedEntity() {
        return this.sharedEntity;
    }
    public void setSharedEntity(Entity sharedEntity) {
        this.sharedEntity = sharedEntity;
    }
    
    public PartnerRegistry getPartnerRegistry() {
        return this.partnerRegistry;
    }
    public void setPartnerRegistry(PartnerRegistry partnerRegistry) {
        this.partnerRegistry = partnerRegistry;
    }
    
    public char getShareMode() {
        return this.shareMode;
    }
    public void setShareMode(char shareMode) {
        this.shareMode = shareMode;
    }
    
    public Date getSharedSince() {
        return this.sharedSince;
    }
    public void setSharedSince(Date sharedSince) {
        this.sharedSince = sharedSince;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("partnerRegistry").append("='").append(getPartnerRegistry()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SharedEntity) ) return false;
		 SharedEntity castOther = ( SharedEntity ) other; 
         
		 return ( (this.getSharedEntity()==castOther.getSharedEntity()) || ( this.getSharedEntity()!=null && castOther.getSharedEntity()!=null && this.getSharedEntity().equals(castOther.getSharedEntity()) ) )
 && ( (this.getPartnerRegistry()==castOther.getPartnerRegistry()) || ( this.getPartnerRegistry()!=null && castOther.getPartnerRegistry()!=null && this.getPartnerRegistry().equals(castOther.getPartnerRegistry()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerRegistry() == null ? 0 : this.getPartnerRegistry().hashCode() );
         return result;
   }   


}


