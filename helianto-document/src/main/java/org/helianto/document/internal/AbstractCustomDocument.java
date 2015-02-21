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

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

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
	
	private long internalNumber;
	
	@Column(length=48)
	private String internalNumberKey;
	
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    private Category category;
	
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
	 * Subclasses may override this method to change how the prefix is created.
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
	public long getInternalNumber() {
		return this.internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	/**
	 * The number pattern, if available, or null.
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
	 * <<Transient>> Optionally delegate to subclasses a method to replace the private field.
	 * 
	 * <p>
	 * Default implementation does not replace the private field.
	 * </p>
	 */
//	@Transient
	protected Category getInternalCategory(Category category) {
		return category;
	}
	
	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractCustomDocument) ) return false;
		 return super.equals(other);
    }

}
