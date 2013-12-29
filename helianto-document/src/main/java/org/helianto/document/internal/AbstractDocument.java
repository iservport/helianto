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

package org.helianto.document.internal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractEvent;
import org.helianto.core.utils.StringListUtils;
/**
 * Base class to represent a <code>Document</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractDocument 
	extends AbstractEvent
{

    private static final long serialVersionUID = 1L;
    
    @Column(length=24)
    private String docCode = "";
    
    @Column(length=128)
    private String docName = "";
    
    @Column(length=128)
    private String docFile = "";
    
    @Column(length=2048)
    private String docAbstract = "";
    
    private char priority = '0';
    
	@Column(length=32)
    private String encoding = "ISO8859_1";
    
	@Column(length=32)
    private String multipartFileContentType = "text/plain";
    
    @Column(length=1024)
    private String referenceList = "";

    /** 
     * Default constructor
     */
    public AbstractDocument() {
    	init("");
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param docCode
     */
    public AbstractDocument(Entity entity, String docCode) {
    	init(docCode);
    	setEntity(entity);
    }
    
    /**
     * Document initialization.
     * 
     * @param docCode
     */
    protected void init(String docCode) {
    	setDocCode(docCode);
    }

    /**
     * Document code.
     */
    public String getDocCode() {
        return this.docCode;
    }
    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    /**
     * Document name.
     */
    public String getDocName() {
        return this.docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }

    /**
     * Document file.
     */
    public String getDocFile() {
        return this.docFile;
    }
    public void setDocFile(String docFile) {
        this.docFile = docFile;
    }

    /**
     * Document abstract.
     */
    public String getDocAbstract() {
		return docAbstract;
	}
    public void setDocAbstract(String docAbstract) {
		this.docAbstract = docAbstract;
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
    
	public String getEncoding() {
		return this.encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getMultipartFileContentType() {
		return internalMultipartFileContentType(multipartFileContentType);
	}
	public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
	}

    /**
     * Allow subclasses to override how multipartFileContentType is determined.
     */
//    @Transient
    protected String internalMultipartFileContentType(String multipartFileContentType) {
    	return multipartFileContentType;
    }

    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "text".
     */
//    @Transient
    public boolean isText() {
    	if (getMultipartFileContentType().startsWith("text")) {
    		return true;
    	}
    	return false;
    }

    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "text/html".
     */
//    @Transient
    public boolean isHtml() {
    	if (getMultipartFileContentType().startsWith("text/html")) {
    		return true;
    	}
    	return false;
    }

    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "image".
     */
//    @Transient
    public boolean isImage() {
    	if (getMultipartFileContentType().startsWith("image")) {
    		return true;
    	}
    	return false;
    }

    /**
     * By default, a document can be changed.
     */
//    @Transient
    public boolean isLocked() {
    	return false;
    }

    /**
     * True if docCode is empty.
     */
//    @Transient
    public boolean isKeyEmpty() {
    	if (this.getDocCode()!=null) {
    		return getDocCode().length()==0;
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
    }

    /**
     * Reference list of comma separated values.
     */
    public String getReferenceList() {
		return referenceList;
	}
    public void setReferenceList(String referenceList) {
		this.referenceList = referenceList;
	}
    
    /**
     * References as array.
     */
//    @Transient
    public String[] getReferencesAsArray() {
    	return StringListUtils.stringToArray(getReferenceList());
    }
    public void setReferencesAsArray(String[] referenceListAsArray) {
    	setReferenceList(StringListUtils.arrayToString(referenceListAsArray));
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
