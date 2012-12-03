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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Unit;

/**
 * Represent a material type.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="proc_material",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
public class MaterialType  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
    private Entity entity;
    private long internalNumber;
    private int version;
    private MaterialType parent;
    private Unit materialUnit;
    private String materialName;

    /** default constructor */
    public MaterialType() {
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Entity.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
    public MaterialType getParent() {
        return this.parent;
    }
    public void setParent(MaterialType parent) {
        this.parent = parent;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="unitId", nullable=true)
    public Unit getMaterialUnit() {
        return this.materialUnit;
    }
    public void setMaterialUnit(Unit materialUnit) {
        this.materialUnit = materialUnit;
    }
    
    public String getMaterialName() {
        return this.materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public static MaterialType materialTypeFactory(Unit unit, String materialName) {
        MaterialType MaterialType = new MaterialType();
        MaterialType.setEntity(unit.getEntity());
        MaterialType.setMaterialUnit(unit);
        MaterialType.setMaterialName(materialName);
        return MaterialType;
    }

    public static MaterialType materialTypeFactory(MaterialType parent, Unit unit, String materialName) {
        MaterialType MaterialType = materialTypeFactory(unit, materialName);
        MaterialType.setParent(parent);
        return MaterialType;
    }


	/**
     * toString
     * @return String
     */
	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        builder.append("internalNumber").append("='").append(getInternalNumber()).append("' ");
        builder.append("]");
        return builder.toString();
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MaterialType) ) return false;
		 MaterialType castOther = ( MaterialType ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }   

}


