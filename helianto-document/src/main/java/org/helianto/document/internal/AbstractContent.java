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

import java.io.IOException;
import java.util.Date;

import javax.persistence.Lob;
import javax.persistence.Transient;

import org.helianto.core.def.Uploadable;
import org.helianto.document.domain.Document;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Implements <code>Uploadable</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public class AbstractContent 
	extends Document 
	implements Cloneable
	, Uploadable {

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @Lob
    private byte[] content = "".getBytes();
    
    @JsonIgnore
	@Transient
    private transient MultipartFile file;
    
	/** 
	 * Default constructor.
	 */
    public AbstractContent() {
    	super();
    	setMultipartFileContentType("text/html");
    }

    /**
     * Read constructor.
     * 
     * @param id
     * @param ownerId
     * @param issueDate
     * @param resolution
     * @param docCode
     * @param docName
     * @param docFile
     * @param docAbstract
     * @param priority
     * @param folderId
     * @param internalNumber
     * @param categoryId
     */
    public AbstractContent(Integer id, Integer ownerId, Date issueDate, Character resolution
    	    , String docCode, String docName, String docFile, String docAbstract, Character priority
    	    , Integer folderId, Long internalNumber, Integer categoryId) {
    	super(id, ownerId, issueDate, resolution, docCode, docName, docFile, docAbstract, priority, 
    			folderId, internalNumber, categoryId);
    }
    
    /**
     * Read composite constructor.
     * 
     * @param id
     * @param ownerId
     * @param ownerDisplayName
     * @param ownerFirstName
     * @param ownerLastName
     * @param ownerGender
     * @param ownerImageUrl
     * @param issueDate
     * @param resolution
     * @param docCode
     * @param docName
     * @param docFile
     * @param docAbstract
     * @param priority
     * @param folderId
     * @param folderCode
     * @param folderName
     * @param contentType
     * @param internalNumber
     * @param categoryId
     */
	public AbstractContent(Integer id, Integer ownerId, String ownerDisplayName,
			String ownerFirstName, String ownerLastName, Character ownerGender,
			String ownerImageUrl, Date issueDate, Character resolution,
			String docCode, String docName, String docFile, String docAbstract,
			Character priority, Integer folderId, String folderCode, String folderName,
			char contentType, Long internalNumber, Integer categoryId) {
		super(id, ownerId, ownerDisplayName, ownerFirstName, ownerLastName,
				ownerGender, ownerImageUrl, issueDate, resolution, docCode, docName,
				docFile, docAbstract, priority, folderId, folderCode, folderName, 
				contentType, internalNumber, categoryId);
	}

    public byte[] getContent() {
        return this.content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    
    /**
     * Helper method to get text content as String.
     */
    public String getContentAsString() {
    	if (getContent()!=null) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
    	if (getContent()!=null) {
    		setContent(contentAsString.getBytes());
    	}
	}
    
    public int getContentSize() {
    	if (getContent()!=null) {
    		return getContent().length;
    	}
    	return 0;
    }
    
	/**
	 * <<Transient>> Convenience property to hold uploaded data.
	 */
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * <<Transient>> Convenience method to read uploaded data.
	 */
	public void processFile() throws IOException {
		setContent(file.getBytes());
		setMultipartFileContentType(file.getContentType());
		setDocFile(file.getOriginalFilename());
	}
	
    @Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractContent) ) return false;
		 return super.equals(other);
    }
    
    /**
     * Make clone() public.
     */
    @Override
    public AbstractContent clone() throws CloneNotSupportedException {
    	return (AbstractContent) super.clone();
    }
    
    /**
     * Merger.
     * 
     * @param command
     */
    public AbstractContent merge(AbstractContent command) {
    	super.merge(command);
		setContent(command.getContentAsString().getBytes());
    	return this;
    }

}
