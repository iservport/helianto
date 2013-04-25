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

import java.text.DecimalFormat;

import javax.persistence.CascadeType;
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
	extends AbstractDocument 
	implements Sequenceable {

	private static final long serialVersionUID = 1L;
	private DocumentFolder series;
	private long internalNumber;
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
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="serializerId")
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
    @Transient
    public DocumentFolder getFolder() {
		return getSeries();
	}
    public void setFolder(DocumentFolder folder) {
		setSeries(folder);
	}
    
	/**
	 * Subclasses may override this method to change how the prefix is created.
	 */
	@Transient
	public StringBuilder getPrefix() {
		return new StringBuilder(getSeries().getFolderCode());
	}

	/**
	 * Required by {@link Sequenceable}.
	 */
	@Transient
	public final String getInternalNumberKey() {
		return getPrefix().toString();
	}
	
    @Transient
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
		afterInternalNumberSet(internalNumber);
	}
	
	/**
	 * The number pattern, if available, or null.
	 */
	@Transient
	protected String getNumberPattern() {
		if (getFolder()!=null) {
			return getFolder().getNumberPattern();
		}
		return null;
	}
	
	/**
	 * True if a new internal number is required.
	 * <p>
	 * Default implementation checks if {@link #internalNumber} is 
	 * greater or equal than {@link #getStartNumber()} and {@link #isKeyEmpty()} is true .
	 * </p>
	 */
	@Transient
	protected boolean isNewNumberRequired() {
		return isKeyEmpty() && getInternalNumber() >= getStartNumber();
	}
	
	/**
	 * Reset docCode and internalNumber to allow the number pattern to be re-applied.
	 */
	@Transient
	public void resetDocCode() {
		setDocCode("");
		setInternalNumber(getStartNumber());
	}
	
	/**
	 * True if a pattern should be applied to docCode after internalNumber changed.
	 * 
	 * <p>
	 * Default implementation checks if {@link #isNewNumberRequired()} is true 
	 * and {@link #getNumberPattern()} is not null.
	 * </p>
	 */
	@Transient
	protected boolean isPatternRequired() {
		return isNewNumberRequired() && getNumberPattern()!=null;
	}
	
    /**
     * Apply a number pattern.
     * 
     * @see #afterInternalNumberSet(long)
     */
	@Transient
	protected String applyNumberPattern(long internalNumber) {
		return new DecimalFormat(getNumberPattern()).format(internalNumber);
	}

	/**
	 * Called after {@link #setInternalNumber(long)} if {@link #isPatternRequired()}. 
	 * 
	 * @param internalNumber
	 */
	@Transient
	protected void afterInternalNumberSet(long internalNumber) {
		if (isPatternRequired()) {
			setDocCode(applyNumberPattern(internalNumber));
		}
	}

    /**
     * Category.
     * @see {@link Category}
     */
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
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
	@Transient
	protected Category getInternalCategory(Category category) {
		return category;
	}
	
	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractCustomDocument) ) return false;
		 return super.equals(other);
    }

}
