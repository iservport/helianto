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
    
    @Lob
    private byte[] content;
    
	@Transient
    private transient MultipartFile file;
    
	/** 
	 * Default constructor.
	 */
    public AbstractContent() {
    	super();
    	setMultipartFileContentType("text/html");
    }

    public byte[] getContent() {
        return this.content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    @JsonIgnore
    public void setContent(String content) {
    	this.content = content.getBytes();
    }
    
    /**
     * Helper method to get text content as String.
     */
//    @Transient
    public String getContentAsString() {
    	if (getContent()!=null) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString);
	}
    
//    @Transient
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
//	@Transient
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

}
