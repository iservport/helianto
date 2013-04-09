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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.helianto.core.base.AbstractFolder;
import org.helianto.core.domain.Entity;

/**
 * Base class to wrap a number pattern to be used to generate a sequence of documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractSerializer<D> 
	extends AbstractFolder {

	private static final long serialVersionUID = 1L;
	private String numberPattern;
    private char contentType;
    private Set<D> documents = new HashSet<D>();
    
    /** 
     * Key constructor.
     * 
     * @param entity
     * @param folderCode
     */
    public AbstractSerializer(Entity entity, String folderCode) {
    	super(entity, folderCode);
    	setNumberPattern("0000");
    	setContentType(' ');
    }

    /**
     * Pattern used in association with <code>SequenceMgr</code>.
     * 
     * <p>
     * Patterns like "P0000" will produce document codes
     * like P0001, P0002, etc., while 0000/'09' builds
     * 0001/09, 0002/09, etc.
     * </p>
     */
    @Column(length=20)
	public String getNumberPattern() {
		return numberPattern;
	}
	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
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
	@OneToMany(mappedBy="series")
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
