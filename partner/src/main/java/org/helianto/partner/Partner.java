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

package org.helianto.partner;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
/**
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_partner",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerRegistryId", "type"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("P")
public class Partner implements java.io.Serializable {

    /**
     * <code>Partner</code> factory.
     * 
     * @param partnerRegistry
     * @param sequence
     */
    protected static <T extends Partner> T internalPartnerFactory(Class<T> clazz, PartnerRegistry partnerRegistry) {
        T partner = null;
        try {
        	partner = clazz.newInstance();
        } 
        catch (Exception e) {
        	throw new RuntimeException("Unable to instantiate partner or descendant.");
        }
        partner.setPartnerRegistry(partnerRegistry);
        partnerRegistry.getPartners().add(partner);
        partner.setPartnerState(PartnerState.IDLE.getValue());
        partner.setPriority('0');
        return partner;
    }

    /**
     * <code>Partner</code> factory.
     * 
     * @param partnerRegistry
     */
    public static Partner partnerFactory(PartnerRegistry partnerRegistry) {
        return internalPartnerFactory(Partner.class, partnerRegistry);
    }

    private static final long serialVersionUID = 1L;
    private int id;
    private PartnerRegistry partnerRegistry;
    private Account account;
    private char priority;
    private char partnerState;
    private Set<PartnerKey> partnerKeys = new HashSet<PartnerKey>(0);

	/** default constructor */
    public Partner() {
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

    /**
     * PartnerRegistry.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="partnerRegistryId", nullable=true)
    public PartnerRegistry getPartnerRegistry() {
        return this.partnerRegistry;
    }
    @Transient
    public String getPartnerAlias() {
    	return getPartnerRegistry().getPartnerAlias();
    }
    @Transient
    public String getPartnerName() {
    	return getPartnerRegistry().getPartnerName();
    }
    public void setPartnerRegistry(PartnerRegistry partnerRegistry) {
        this.partnerRegistry = partnerRegistry;
    }

    /**
     * Account.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="accountId", nullable=true)
    public Account getAccount() {
        return this.account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Priority.
     */
    public char getPriority() {
        return this.priority;
    }
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * Partner state.
     */
    public char getPartnerState() {
        return this.partnerState;
    }
    public void setPartnerState(char partnerState) {
        this.partnerState = partnerState;
    }
    public void setPartnerState(PartnerState partnerState) {
        this.partnerState = partnerState.getValue();
    }

    /**
     * Partner keys.
     */
    @OneToMany(mappedBy="partner")
    public Set<PartnerKey> getPartnerKeys() {
		return partnerKeys;
	}
	public void setPartnerKeys(Set<PartnerKey> partnerKeys) {
		this.partnerKeys = partnerKeys;
	}

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerRegistry").append("='").append(getPartnerRegistry()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Partner) ) return false;
         Partner castOther = (Partner) other; 
         
         return ((this.getPartnerRegistry()==castOther.getPartnerRegistry()) || ( this.getPartnerRegistry()!=null && castOther.getPartnerRegistry()!=null && this.getPartnerRegistry().equals(castOther.getPartnerRegistry()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerRegistry() == null ? 0 : this.getPartnerRegistry().hashCode() );
         return result;
   }

}
