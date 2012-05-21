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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.helianto.core.Entity;
import org.helianto.document.Folder;

/**
 * Base class to represent a folder.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractFolder

	implements Folder

{

	private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private int version;
	private String folderCode;
	private String folderName;
    
    /** 
     * Empty constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractFolder() {
    	super();
    	setFolderName("");
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractFolder(Entity entity, String folderCode) {
    	this();
    	setEntity(entity);
    	setFolderCode(folderCode);
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version (database record version).
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * <<NaturalKey>> Entity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    // TODO rename in table
    @Column(length=24, name="builderCode")
    public String getFolderCode() {
        return getInternalBuilderCode();
    }
    public void setFolderCode(String builderCode) {
        this.folderCode = builderCode;
    }
    
    /**
     * Sublcasses may change the way a builder code is retrieved.
     */
    @Transient
    protected String getInternalBuilderCode() {
        return this.folderCode;
    }

    // TODO rename in table
    @Column(length=128, name="builderName")
	public String getFolderName() {
		return getInternalBuilderName();
	}
	public void setFolderName(String builderName) {
		this.folderName = builderName;
	}
	
    /**
     * Sublcasses may change the way a builder code is retrieved.
     */
    @Transient
    protected String getInternalBuilderName() {
    	return folderName;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("folderCode").append("='").append(getFolderCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof AbstractFolder) ) return false;
		AbstractFolder castOther = (AbstractFolder) other; 
         
         return ( ( this.getEntity()==castOther.getEntity()) 
        		    || ( this.getEntity()!=null 
        			     && castOther.getEntity()!=null 
        			     && this.getEntity().equals(castOther.getEntity()
        			   ) 
        		))
             && ( ( this.getFolderCode()==castOther.getFolderCode()) 
            	    || ( this.getFolderCode()!=null 
            	    	 && castOther.getFolderCode()!=null 
            	    	 && this.getFolderCode().equals(castOther.getFolderCode()
            	    	)
            	));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getFolderCode() == null ? 0 : this.getFolderCode().hashCode() );
         return result;
   }   

}
