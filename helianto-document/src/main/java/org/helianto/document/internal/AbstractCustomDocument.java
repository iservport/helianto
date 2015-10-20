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

import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.domain.DocumentFolder;

/**
 * Extends <code>AbstractDocument</code> to control how docCode
 * is created.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractCustomDocument 
	extends AbstractDocument {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="serializerId")
	private DocumentFolder series;
	
	@Transient
	private Integer folderId;
	
	@Transient
	private String folderCode;
	
	@Transient
	private String folderName;
	
	@Transient
    private char contentType = ' ';
    
	private Long internalNumber;
	
	@Column(length=48)
	private String internalNumberKey;
	
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    private Category category;
	
	@Transient
	private Integer categoryId;
	
	/**
	 * Default constructor.
	 */
	public AbstractCustomDocument() {
		super();
	}
	
	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
    public AbstractCustomDocument(Entity entity, String docCode) {
    	super(entity, docCode);
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
    public AbstractCustomDocument(Integer id, Integer ownerId, Date issueDate, Character resolution
    	    , String docCode, String docName, String docFile, String docAbstract, Character priority
    	    , Integer folderId, Long internalNumber, Integer categoryId) {
    	super(id, ownerId, issueDate, resolution, docCode, docName, docFile, docAbstract, priority);
    	initCustomDocument(folderId, internalNumber, categoryId);
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
    public AbstractCustomDocument(Integer id, Integer ownerId, String ownerDisplayName
    		, String ownerFirstName, String ownerLastName, Character ownerGender
    		, String ownerImageUrl, Date issueDate, Character resolution
    	    , String docCode, String docName, String docFile, String docAbstract, Character priority
    	    , Integer folderId, String folderCode, String folderName
    	    , char contentType, Long internalNumber, Integer categoryId) {
    	super(id, ownerId, ownerDisplayName, ownerFirstName, ownerLastName
    			, ownerGender, ownerImageUrl, issueDate, resolution, docCode, docName, docFile
    			, docAbstract, priority);
    	initCustomDocument(folderId, internalNumber, categoryId);
    	setFolderCode(folderCode);
    	setFolderName(folderName);
    	setContentType(contentType);
    }
    
    /**
     * Convenience to set fields.
     * 
     * @param folderId
     * @param internalNumber
     * @param categoryId
     */
    protected final void initCustomDocument(Integer folderId, Long internalNumber, Integer categoryId) {
    	setFolderId(folderId);
    	setInternalNumber(internalNumber);
    	setCategoryId(categoryId);
	}
    
	/**
	 * The document series.
	 */
	public DocumentFolder getSeries() {
		return series;
	}
	/**
	 * Series setter, also sets superclass entity.
	 * 
	 * @param serializer
	 */
	public void setSeries(DocumentFolder series) {
		this.series = series;
		if (series!=null) {
			super.setEntity(series.getEntity());
		}
	}
	
    /**
     * <<Transient>> Convenience to rename field series to folder.
     */
    public DocumentFolder getFolder() {
		return getSeries();
	}
    public void setFolder(DocumentFolder folder) {
		setSeries(folder);
	}
    
    /**
     * <<Transient>> folder id.
     */
    public Integer getFolderId() {
		return getFolder()!=null ? getFolder().getId() : folderId;
	}
    public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
    
    /**
     * <<Transient>> folder code.
     */
    public String getFolderCode() {
		return getFolder()!=null ? getFolder().getFolderCode() : folderCode;
	}
    public void setFolderCode(String folderCode) {
		this.folderCode = folderCode;
	}
    
    /**
     * <<Transient>> folder name.
     */
    public String getFolderName() {
		return getFolder()!=null ? getFolder().getFolderName() : folderName;
	}
    public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
    
    /**
     * <<Transient>> folder content type.
     */
    public char getContentType() {
		return getFolder()!=null ? getFolder().getContentType() : contentType;
	}
    public void setContentType(char contentType) {
		this.contentType = contentType;
	}
    
	/**
	 * Subclasses may override this method to change how the prefix is created.
	 * @deprecated
	 */
	public StringBuilder getPrefix() {
		if (getSeries()!=null) {
			return new StringBuilder(getSeries().getFolderCode());
		}
		return new StringBuilder();
	}

	/**
	 * <<Transient>> Allow subclasses to override internal number key.
	 * 
	 * <p>
	 * Defaults to ${prefix}.
	 * </p>
	 * 
	 * @param internalNumberKey
	 * @deprecated
	 */
	protected String validateInternalNumberKey(String internalNumberKey) {
    	if (getPrefix()!=null) {
    		return getPrefix().toString();
    	}
    	return internalNumberKey;
	}
	
    public int getStartNumber() {
    	return 1;
    }

	/**
	 * Required by {@link Sequenceable}.
	 */
	public Long getInternalNumber() {
		return this.internalNumber;
	}
	public void setInternalNumber(Long internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	/**
	 * The number pattern, if available, or null.
	 * @deprecated
	 */
	protected String getNumberPattern() {
		if (getFolder()!=null) {
			return getFolder().getNumberPattern();
		}
		return "";
	}
	
    /**
     * Apply a number pattern to build a docCode.
     */
	public void applyNumberPattern() {
		setDocCode(new DecimalFormat(getNumberPattern()).format(internalNumber));
	}

    /**
     * Category.
     * @see {@link Category}
     */
    public Category getCategory() {
		return getInternalCategory(category);
	}
    public void setCategory(Category category) {
		this.category = category;
	}
    
    /**
     * <<Transient>> category id.
     */
    public Integer getCategoryId() {
		return getCategory()!=null ? getCategory().getId() : categoryId;
	}
    public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
    
	/**
	 * <<Transient>> Optionally delegate to subclasses a method to replace the private field.
	 * 
	 * <p>
	 * Default implementation does not replace the private field.
	 * </p>
	 */
	protected Category getInternalCategory(Category category) {
		return category;
	}
	
    /**
     * Merger.
     * 
     * @param command
     */
    public void merge(AbstractCustomDocument command) {
		super.merge(command);
		setInternalNumber(command.getInternalNumber());
    }
    
	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractCustomDocument) ) return false;
		 return super.equals(other);
    }

}
