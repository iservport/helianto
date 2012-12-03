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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.Customizable;
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
	
	implements Customizable 
	
{

	private static final long serialVersionUID = 1L;
	private DocumentFolder series;
	private long internalNumber;
	
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
	
	/**
	 * Required by {@link Sequenceable}.
	 */
	public long getInternalNumber() {
		return this.internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
		if (internalNumber>0 && isKeyEmpty() && getSeries()!=null) {
			afterInternalNumberSet(internalNumber);
		}
	}
	
	/**
	 * Called after {@link #setInternalNumber(long)}, when {@link #internalNumber} is 
	 * set with a positive number, {@link #isKeyEmpty()} is true and {@link #getSeries()} 
	 * is not null.
	 * 
	 * @param internalNumber
	 */
	@Transient
	protected void afterInternalNumberSet(long internalNumber) {
		setDocCode(getSeries().buildCode(internalNumber));
	}

	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractCustomDocument) ) return false;
		 return super.equals(other);
    }

}
