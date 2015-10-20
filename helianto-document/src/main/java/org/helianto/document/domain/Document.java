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

package org.helianto.document.domain;

import java.util.Date;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.document.internal.AbstractCustomDocument;


/**
 * Concrete documents.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_doc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.JOINED)
public class Document 
	extends AbstractCustomDocument 
	implements Comparable<Document> 
{

    private static final long serialVersionUID = 1L;
    
    @Transient
    private boolean visited;
    
    @Transient
    private long views;
    
	/** 
	 * Default constructor 
	 */
    public Document() {
    	super();
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
    public Document(Entity entity, String docCode) {
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
    public Document(Integer id, Integer ownerId, Date issueDate, Character resolution
    	    , String docCode, String docName, String docFile, String docAbstract, Character priority
    	    , Integer folderId, Long internalNumber, Integer categoryId) {
    	super(id, ownerId, issueDate, resolution, docCode, docName, docFile, docAbstract, priority, 
    			folderId, internalNumber, categoryId);
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
	public Document(Integer id, Integer ownerId, String ownerDisplayName,
			String ownerFirstName, String ownerLastName, Character ownerGender,
			String ownerImageUrl, Date issueDate, Character resolution,
			String docCode, String docName, String docFile, String docAbstract,
			Character priority, Integer folderId, String folderCode, String folderName,
			char contentType, Long internalNumber, Integer categoryId) {
		super(id, ownerId, ownerDisplayName, ownerFirstName, ownerLastName,
				ownerGender, ownerImageUrl, issueDate, resolution, docCode, docName,
				docFile, docAbstract, priority, folderId, folderCode, folderName, 
				contentType, internalNumber, categoryId);
	}

	/**
	 * Sort by docCode.
	 */
	public int compareTo(Document next) {
		return getDocCode().compareTo(next.getDocCode());
	}

	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof Document) ) return false;
		 return super.equals(other);
    }

    /**
     * Optional non-persistent flag to be true when user has visited.
     */
    public boolean isVisited() {
		return visited;
	}
    public void setVisited(boolean visited) {
		this.visited = visited;
	}
    
    /**
     * Optional non-persistent counter to views.
     */
    public long getViews() {
		return views;
	}
    public void setViews(long views) {
		this.views = views;
	}
    
    /**
     * Merger.
     * 
     * @param command
     */
    public Document merge(Document command) {
    	super.merge(command);
    	return this;
    }

}
