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

package org.helianto.process;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;

/**				
 * <p>
 * A class to represent document releases.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_release",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Release implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
    private Entity entity;
    private long internalNumber;
    private Date releaseDate;
    private char releaseState;

    /** default constructor */
    public Release() {
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Owning entity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
    
    /**
     * Release date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getReleaseDate() {
        return this.releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    /**
     * Release state.
     */
    public char getReleaseState() {
        return this.releaseState;
    }
    public void setReleaseState(char releaseState) {
        this.releaseState = releaseState;
    }
    public void setReleaseState(ReleaseState releaseState) {
        this.releaseState = releaseState.getValue();
    }

    /** 
	 * Factory method.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
    public Release release(Entity entity, long internalNumber) {
    	Release release = new Release();
    	release.setEntity(entity);
    	release.setInternalNumber(internalNumber);
    	release.setReleaseDate(new Date());
    	release.setReleaseState(ReleaseState.PENDING.getValue());
        return release;
    }
   
	/**
     * toString
     * @return String
     */
	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        builder.append("internalNumber").append("='").append(getInternalNumber()).append("' ");
        builder.append("]");
        return builder.toString();
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Release) ) return false;
		 Release castOther = ( Release ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }   


}


