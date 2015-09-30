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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractFolder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class to wrap a number pattern to be used to generate a sequence of documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractSerializer<D> 
	extends AbstractFolder {

	private static final long serialVersionUID = 1L;
	
    @Column(length=20)
	private String patternPrefix = "F";
	
	private Integer numberOfDigits = 3;
	
    private char contentType = ' ';
    
    @JsonIgnore
	@OneToMany(mappedBy="series")
    private Set<D> documents = new HashSet<D>();
    
    /** 
     * Key constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractSerializer(Entity entity, String folderCode) {
    	super(entity, folderCode);
    }

    /**
     * Pattern to generate new docCode.
     */
	public String getNumberPattern() {
		return new StringBuilder("'")
		.append(getPatternPrefix())
		.append("'")
		.append("00000000000".substring(0, getNumberOfDigits()))
		.toString();
	}
	
	/**
	 * Pattern prefix.
	 */
	public String getPatternPrefix() {
		return patternPrefix;
	}
	public void setPatternPrefix(String patternPrefix) {
		this.patternPrefix = patternPrefix;
	}
	
	/**
	 * Number of digits.
	 */
	public Integer getNumberOfDigits() {
		return numberOfDigits;
	}
	public void setNumberOfDigits(Integer numberOfDigits) {
		this.numberOfDigits = numberOfDigits;
	}
	
    /**
     * Content type.
     */
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Document set.
	 */
	public Set<D> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<D> documents) {
		this.documents = documents;
	}
	
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("folderCode").append("='").append(getFolderCode()).append("' ");
        buffer.append("numberPattern").append("='").append(getNumberPattern()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

}
