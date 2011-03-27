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

package org.helianto.process;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.document.base.AbstractVersion;

/**
 * <p>
 * A class to represent process document versions.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_version",
    uniqueConstraints = {@UniqueConstraint(columnNames={"releaseId", "documentId"})}
)
public class DocumentVersion extends AbstractVersion implements java.io.Serializable {

 	private static final long serialVersionUID = 1L;
 	private Release release;
    private ProcessDocument document;

    /** default constructor */
    public DocumentVersion() {
    }

    /**
     * Versions are grouped by release.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="releaseId", nullable=true)
    public Release getRelease() {
        return this.release;
    }
    public void setRelease(Release release) {
        this.release = release;
    }
    
    /**
     * Owning document.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="documentId", nullable=true)
    public ProcessDocument getDocument() {
        return this.document;
    }
    public void setDocument(ProcessDocument document) {
        this.document = document;
    }
    
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DocumentVersion) ) return false;
		 DocumentVersion castOther = ( DocumentVersion ) other; 
         
		 return ( (this.getRelease()==castOther.getRelease()) || ( this.getRelease()!=null && castOther.getRelease()!=null && this.getRelease().equals(castOther.getRelease()) ) )
		     && ( (this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getRelease() == null ? 0 : this.getRelease().hashCode() );
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         return result;
   }   


}


