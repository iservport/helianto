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
/**
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 * @author Vlademir Teixeira
 */
@javax.persistence.Entity
@Table(name="prtnr_partner",
    uniqueConstraints = {@UniqueConstraint(columnNames={"partnerAssociationId", "sequence"})}
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private PartnerAssociation partnerAssociation;
    private int sequence;
    private Account account;
    private char priority;
    private char partnerState;

    /** default constructor */
    public Partner() {
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
     * PartnerAssociation getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="partnerAssociationId", nullable=true)
    public PartnerAssociation getPartnerAssociation() {
        return this.partnerAssociation;
    }
    /**
     * PartnerAssociation setter.
     */
    public void setPartnerAssociation(PartnerAssociation partnerAssociation) {
        this.partnerAssociation = partnerAssociation;
    }

    /**
     * Sequence getter.
     */
    public int getSequence() {
        return this.sequence;
    }
    /**
     * Sequence setter.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * Account getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="accountId", nullable=true)
    public Account getAccount() {
        return this.account;
    }
    /**
     * Account setter.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Priority getter.
     */
    public char getPriority() {
        return this.priority;
    }
    /**
     * Priority setter.
     */
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * PartnerState getter.
     */
    public char getPartnerState() {
        return this.partnerState;
    }
    /**
     * PartnerState setter.
     */
    public void setPartnerState(char partnerState) {
        this.partnerState = partnerState;
    }

    /**
     * <code>Partner</code> factory.
     * 
     * @param partnerAssociation
     * @param sequence
     */
    public static Partner partnerFactory(PartnerAssociation partnerAssociation, int sequence) {
        Partner partner = new Partner();
        partner.setPartnerAssociation(partnerAssociation);
        partner.setSequence(sequence);
        partnerAssociation.getPartners().add(partner);
        return partner;
    }

    /**
     * <code>Partner</code> natural id query.
     */
    @Transient
    public static String getPartnerNaturalIdQueryString() {
        return "select partner from Partner partner where partner.partnerAssociation = ? and partner.sequence = ? ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("partnerAssociation").append("='").append(getPartnerAssociation()).append("' ");
        buffer.append("sequence").append("='").append(getSequence()).append("' ");
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
         
         return ((this.getPartnerAssociation()==castOther.getPartnerAssociation()) || ( this.getPartnerAssociation()!=null && castOther.getPartnerAssociation()!=null && this.getPartnerAssociation().equals(castOther.getPartnerAssociation()) ))
             && ((this.getSequence()==castOther.getSequence()));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPartnerAssociation() == null ? 0 : this.getPartnerAssociation().hashCode() );
         result = 37 * result + (int) this.getSequence();
         return result;
   }   

}
