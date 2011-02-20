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

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.helianto.core.Entity;
import org.helianto.core.number.Sequenceable;

/**
 * Extends <code>AbstractDocument</code> to control how docCode
 * is created.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractCustomDocument extends AbstractDocument implements Customizable {

	private static final long serialVersionUID = 1L;
	private Serializer series;
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
	public Serializer getSeries() {
		return series;
	}
	/**
	 * Series setter, also sets superclass entity.
	 * 
	 * @param serializer
	 */
	public void setSeries(Serializer series) {
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
		return new StringBuilder(getSeries().getBuilderCode());
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
	}

	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof AbstractCustomDocument) ) return false;
		 return super.equals(other);
    }

}
