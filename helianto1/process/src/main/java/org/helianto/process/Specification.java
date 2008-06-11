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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Unit;


/**		
 * <p>
 * A process characteristic specification.
 * </p>
 * @author Mauricio Fernandes de Castro	
 */
@javax.persistence.Entity
@Table(name="proc_spec",
    uniqueConstraints = {@UniqueConstraint(columnNames={"documentId", "characteristicId"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Specification implements java.io.Serializable {
    
	private static final long serialVersionUID = 1L;
    private int id;
	private Document document;
	private Characteristic characteristic;
	private Unit unit;
    private SpecificationLimit specificationLimit;


    /** default constructor */
    public Specification() {
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
     * Characteristic.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="characteristicId", nullable=true)
	public Characteristic getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(Characteristic characteristic) {
		this.characteristic = characteristic;
	}

    /**
     * Unit.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="unitId", nullable=true)
    public Unit getUnit() {
        return this.unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Specification limit.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="specificationLimitId", nullable=true)
    public SpecificationLimit getSpecificationLimit() {
        return this.specificationLimit;
    }
    public void setSpecificationLimit(SpecificationLimit specificationLimit) {
        this.specificationLimit = specificationLimit;
    }
    
    /**
     * <code>Specification</code> factory.
     * 
     * @param document
     * @param characteristic
     */
    public static Specification characteristicFactory(Document document, Characteristic characteristic) {
    	Specification specification = new Specification();
        specification.setDocument(document);
        specification.setCharacteristic(characteristic);
        return specification;
    }

    /**
     * <code>Specification</code> query <code>StringBuilder</code>.
     */
    @Transient
    public static StringBuilder getSpecificationQueryStringBuilder() {
        return new StringBuilder("select specification from Specification specification ");
    }

    /**
     * <code>Specification</code> natural id query.
     */
    @Transient
    public static String getSpecificationNaturalIdQueryString() {
        return getSpecificationQueryStringBuilder().append("where specification.document = ? and specification.characteristic = ? ").toString();
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
        buffer.append("characteristic").append("='").append(getCharacteristic()).append("' ");
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
         if ( !(other instanceof Specification) ) return false;
         Specification castOther = (Specification) other; 
         
         return ((this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ))
             && ((this.getCharacteristic()==castOther.getCharacteristic()) || ( this.getCharacteristic()!=null && castOther.getCharacteristic()!=null && this.getCharacteristic().equals(castOther.getCharacteristic()) ));
   }
   
   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         result = 37 * result + ( getCharacteristic() == null ? 0 : this.getCharacteristic().hashCode() );
         return result;
   }   

}
