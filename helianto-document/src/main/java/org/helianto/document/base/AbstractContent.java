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

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.helianto.document.Document;
import org.helianto.document.Uploadable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implements <code>Uploadable</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public class AbstractContent extends Document implements Cloneable, Uploadable {

    private static final long serialVersionUID = 1L;
    private byte[] content;
    private String encoding;
    private String multipartFileContentType;
    // transient
    private transient MultipartFile file;

	/** 
	 * Default constructor.
	 */
    public AbstractContent() {
    	super();
    	setDocCode("CONTENT");
    	setEncoding("ISO8859_1");
    	setMultipartFileContentType("text/html");
    }

    @Transient
    public boolean isEditable() {
    	if (getMultipartFileContentType().startsWith("text")) {
    		return true;
    	}
    	return false;
    }

    @Transient
    public boolean isImage() {
    	if (getMultipartFileContentType().startsWith("image")) {
    		return true;
    	}
    	return false;
    }

    @Lob
    public byte[] getContent() {
        return this.content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public void setContent(String content) {
    	this.content = content.getBytes();
    }
    
    @Transient
    public String getContentAsString() {
    	if (getContent()!=null) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString);
	}
    
    @Transient
    public int getContentSize() {
    	return this.content.length;
    }
    
	@Column(length=32)
	public String getEncoding() {
		return this.encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Column(length=32)
	public String getMultipartFileContentType() {
		return multipartFileContentType;
	}
	public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
	}

	/**
	 * <<Transient>> Convenience property to hold uploaded data.
	 */
	@Transient
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * <<Transient>> Convenience method to read uploaded data.
	 */
	@Transient
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