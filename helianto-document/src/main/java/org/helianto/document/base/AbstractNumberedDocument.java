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

import org.helianto.core.number.Sequenceable;
import org.helianto.document.domain.DocumentFolder;

/**
 * Extends <code>AbstractDocument</code> to control how docCode
 * is created.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see AbstractCustomDocument
 */
@MappedSuperclass
public class AbstractNumberedDocument extends AbstractDocument implements Sequenceable {

	private static final long serialVersionUID = 1L;
	private DocumentFolder documentCodeBuilder;
	private long internalNumber;
	
	/**
	 * Default constructor.
	 */
	public AbstractNumberedDocument() {
		super();
	}
	
	/**
	 * The document code builder.
	 */
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="documentCodeBuilderId")
	public DocumentFolder getDocumentCodeBuilder() {
		return documentCodeBuilder;
	}
	public void setDocumentCodeBuilder(DocumentFolder documentCodeBuilder) {
		this.documentCodeBuilder = documentCodeBuilder;
	}

	/**
	 * Delegate the number key creation to {@link #getInternalDocCodeKey()}.
	 */
	@Transient
	public final String getInternalNumberKey() {
		return new StringBuilder(getInternalDocCodeKey())
		.append(getDocumentCodeBuilder().getFolderCode())
		.toString();
	}
	
	@Transient
	public String getInternalDocCodeKey() {
		return "NDOC_";
	}

	/**
	 * The internal number.
	 */
	public long getInternalNumber() {
		return this.internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

}
