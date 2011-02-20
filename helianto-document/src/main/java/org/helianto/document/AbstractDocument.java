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

package org.helianto.document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.helianto.core.Entity;
import org.helianto.core.TopLevelCodedEntity;
/**
 * Base class to represent a <code>Document</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public class AbstractDocument implements java.io.Serializable, TopLevelCodedEntity {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String docCode;
    private int version;
    private String docName;
    private String docFile;
    private char priority;

    /** 
     * Default constructor
     */
    public AbstractDocument() {
    	setDocCode("");
    	setDocName("");
    	setDocFile("");
    	setPriority('0');
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public AbstractDocument(Entity entity, String docCode) {
    	setEntity(entity);
    	setDocCode(docCode);
    	setDocName("");
    	setDocFile("");
    	setPriority('0');
    }

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
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Document code.
     */
    @Column(length=24)
    public String getDocCode() {
        return this.docCode;
    }
    public void setDocCode(String docCode) {
        this.docCode = docCode;
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
     * Document name.
     */
    @Column(length=128)
    public String getDocName() {
        return this.docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }

    /**
     * Document file.
     */
    @Column(length=128)
    public String getDocFile() {
        return this.docFile;
    }
    public void setDocFile(String docFile) {
        this.docFile = docFile;
    }

	public TopLevelCodedEntity setKey(Entity entity, String docCode) {
		this.setEntity(entity);
		this.setDocCode(docCode);
		return this;
	}

    /**
     * Priority.
     */
    public char getPriority() {
        return this.priority;
    }
    public void setPriority(char priority) {
        this.priority = priority;
    }
    
    /**
     * By default, a document can be changed.
     */
    @Transient
    public boolean isLocked() {
    	return false;
    }

    /**
     * Natural key info.
     */
    @Transient
    public boolean isKeyEmpty() {
    	if (this.getDocCode()!=null) {
    		return getDocCode().length()==0;
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("docCode").append("='").append(getDocCode()).append("' ");
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
         if ( !(other instanceof AbstractDocument) ) return false;
         AbstractDocument castOther = (AbstractDocument) other; 
         
         return ( ( this.getEntity()==castOther.getEntity()) 
        		    || ( this.getEntity()!=null 
        			     && castOther.getEntity()!=null 
        			     && this.getEntity().equals(castOther.getEntity()
        			   ) 
        		))
             && ( ( this.getDocCode()==castOther.getDocCode()) 
            	    || ( this.getDocCode()!=null 
            	    	 && castOther.getDocCode()!=null 
            	    	 && this.getDocCode().equals(castOther.getDocCode()
            	    	)
            	));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getDocCode() == null ? 0 : this.getDocCode().hashCode() );
         return result;
   }   

}
