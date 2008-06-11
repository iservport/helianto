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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


/**
 * <p>
 * Characteristics associated to documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_char",
    uniqueConstraints = {@UniqueConstraint(columnNames={"documentId", "sequence"})}
)
public class Characteristic implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    private int id;
	private Document document;
	private int sequence;
	private String characteristicName;
	private int classification;

    /** default constructor */
    public Characteristic() {
    }
    
    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Document.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="documentId", nullable=true)
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}

    /**
     * Sequence.
     */
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

    /**
     * Characteristic name.
     */
	@Column(length=32)
	public String getCharacteristicName() {
		return characteristicName;
	}
	public void setCharacteristicName(String characteristicName) {
		this.characteristicName = characteristicName;
	}
	
    /**
     * Classification.
     */
    public int getClassification() {
        return this.classification;
    }
    public void setClassification(int classification) {
        this.classification = classification;
    }
    
    private static Characteristic internalCharacteristicFactory(Document document, int sequence) {
    	Characteristic characteristic = new Characteristic();
        characteristic.setDocument(document);
        characteristic.setSequence(sequence);
        return characteristic;
    }

    /**
     * <code>Characteristic</code> factory.
     * 
     * @param part
     * @param sequence
     */
    public static Characteristic characteristicFactory(Part part, int sequence) {
        return internalCharacteristicFactory(part, sequence);
    }

    /**
     * <code>Characteristic</code> factory.
     * 
     * @param process
     * @param sequence
     */
    public static Characteristic characteristicFactory(Process process, int sequence) {
        return internalCharacteristicFactory(process, sequence);
    }

    /**
     * <code>Characteristic</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getCharacteristicQueryStringBuilder() {
        return new StringBuilder("select characteristic from Characteristic characteristic ");
    }

    /**
     * <code>Characteristic</code> natural id query.
     */
    @Transient
    public static String getCharacteristicNaturalIdQueryString() {
        return getCharacteristicQueryStringBuilder().append("where characteristic.document = ? and characteristic.sequence = ? ").toString();
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("document").append("='").append(getDocument()).append("' ");
        buffer.append("sequence").append("='").append(getSequence()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Characteristic) ) return false;
         Characteristic castOther = (Characteristic) other; 
         
         return ((this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ))
         && (this.getSequence()==castOther.getSequence());
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         result = 37 * result + (int) this.getSequence();
         return result;
   }   

}
