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

package org.helianto.partner.domain;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.classic.enums.Uploadable;
import org.helianto.core.domain.Category;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * The category associated to the partner.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_category",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerId", "categoryId"})}
)
public class PartnerCategory 
	implements Uploadable 
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private int version;
    
    @JsonBackReference("partner")
    @ManyToOne
    @JoinColumn(name="partnerId", nullable=true)
    private Partner partner;
    
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    private Category category;
    
    @Lob
    private byte[] content;
    
	@Column(length=32)
    private String encoding = "";
    
	@Column(length=32)
    private String multipartFileContentType;

    @Transient
    private transient MultipartFile file;
    
    /** 
     * Default constructor.
     */
    public PartnerCategory() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param partner
     * @param category
     */
    public PartnerCategory(Partner partner, Category category) {
    	this(partner);
        setCategory(category);
    }

    /** 
     * Form constructor.
     * 
     * @param partner
     */
    public PartnerCategory(Partner partner) {
    	this();
        setPartner(partner);
    }

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Partner.
     */
    public Partner getPartner() {
        return this.partner;
    }
    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    
    /**
     * Category.
     */
    public Category getCategory() {
		return category;
	}
    public void setCategory(Category category) {
		this.category = category;
	}
    
    /**
     * Partner category content.
     */
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
    	if (getContent()!=null && isText()) {
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
    
	public String getEncoding() {
		return this.encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getMultipartFileContentType() {
		return multipartFileContentType;
	}
	public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
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
	}

    public boolean isText() {
    	if (getContent()!=null && getMultipartFileContentType().startsWith("text")) {
    		return true;
    	}
    	return false;
    }

    public boolean isHtml() {
    	if (getContent()!=null && getMultipartFileContentType().startsWith("text/html")) {
    		return true;
    	}
    	return false;
    }

    public boolean isImage() {
    	if (getContent()!=null && getMultipartFileContentType().startsWith("image")) {
    		return true;
    	}
    	return false;
    }


    /**
     * equals
     */
    public boolean equals(Object other) {
          if ( (this == other ) ) return true;
          if ( (other == null ) ) return false;
          if ( !(other instanceof PartnerCategory) ) return false;
          PartnerCategory castOther = (PartnerCategory) other; 
          
          return ((this.getPartner()==castOther.getPartner()) || ( this.getPartner()!=null && castOther.getPartner()!=null && this.getPartner().equals(castOther.getPartner()) ))
              && ((this.getCategory()==castOther.getCategory()) || ( this.getCategory()!=null && castOther.getCategory()!=null && this.getCategory().equals(castOther.getCategory()) ));
    }
    
    /**
     * hashCode
     */
    public int hashCode() {
          int result = 17;
          result = 37 * result + ( getPartner() == null ? 0 : this.getPartner().hashCode() );
          result = 37 * result + ( getCategory() == null ? 0 : this.getCategory().hashCode() );
          return result;
    }   

}
