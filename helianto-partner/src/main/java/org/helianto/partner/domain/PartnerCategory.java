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

package org.helianto.partner.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Category;
/**
 * The category associated to the partner.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_category",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerId", "categoryId"})}
)
public class PartnerCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int version;
    private Partner partner;
    private Category category;

    /** 
     * Default constructor.
     */
    public PartnerCategory() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param partner
     * @param category
     */
    public PartnerCategory(Partner partner, Category category) {
    	this();
        setPartner(partner);
        setCategory(category);
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Version
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Partner.
     */
    @ManyToOne
    @JoinColumn(name="partnerId", nullable=true)
    public Partner getPartner() {
        return this.partner;
    }
    public void setPartner(Partner partner) {
        this.partner = partner;
    }
    
    /**
     * Category.
     */
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    public Category getCategory() {
		return category;
	}
    public void setCategory(Category category) {
		this.category = category;
	}

    /**
     * equals
     */
    public boolean equals(Object other) {
          if ( (this == other ) ) return true;
          if ( (other == null ) ) return false;
          if ( !(other instanceof PartnerCategory) ) return false;
          PartnerCategory castOther = (PartnerCategory) other; 
          
          return ((this.getPartner()==castOther.getPartner()) || ( this.getPartner()!=null && castOther.getPartner()!=null && this.getPartner().equals(castOther.getPartner()) ))
              && ((this.getCategory()==castOther.getCategory()) || ( this.getCategory()!=null && castOther.getCategory()!=null && this.getCategory().equals(castOther.getCategory()) ));
    }
    
    /**
     * hashCode
     */
    public int hashCode() {
          int result = 17;
          result = 37 * result + ( getPartner() == null ? 0 : this.getPartner().hashCode() );
          result = 37 * result + ( getCategory() == null ? 0 : this.getCategory().hashCode() );
          return result;
    }   

}
