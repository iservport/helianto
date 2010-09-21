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

package org.helianto.finance;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.document.AbstractRecord;
import org.helianto.partner.Partner;

/**
 * Cash flow.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="fin_cashflow",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("C")
public class CashFlow extends AbstractRecord implements Comparable<CashFlow> {

	private static final long serialVersionUID = 1L;
	private Entity entity;
	private Partner partner;
    private Date dueDate;
    private BigDecimal amount;
    private int balance;

    @Transient
    public String getInternalNumberKey() {
    	return "CASHFLOW";
    }
	
	/**
	 * Empty constructor.
	 */
	public CashFlow() {
		super();
	}
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public CashFlow(Entity entity) {
		this();
		setEntity(entity);
	}
	
	/**
	 * Partner constructor.
	 * 
	 * @param partner
	 */
	public CashFlow(Partner partner) {
		this(partner.getPrivateEntity().getEntity());
		setPartner(partner);
	}
	
	/**
	 * Owning entity
	 */
	@ManyToOne
	@JoinColumn(name="entityId")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Partner owning the cash flow.
	 */
	@ManyToOne
	@JoinColumn(name="partnerId")
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	
	/**
	 * Cash flow due date
	 */
    @Temporal(TemporalType.TIMESTAMP)
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * The amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * Balance, either CREDIT (1) or DEBIT  (-1)
	 */
	@Column(precision=2, scale=0)
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setBalance(Balance balance) {
		this.balance = balance.getValue();
	}
	
	/**
	 * Resolution
	 */
	public void setResolution(char resolution) {
		super.setResolution(resolution);
	}
	public void setResolution(CashFlowResolution resolution) {
		super.setResolution(resolution.getValue());
	}
	
	/**
	 * Implements comparable using <code>docCode</code>.
	 */
	public int compareTo(CashFlow next) {
		return this.getDueDate().compareTo(next.getDueDate());
	}
	
	   /**
	    * equals
	    */
	   @Override
	   public boolean equals(Object other) {
	         if ( (this == other ) ) return true;
	         if ( (other == null ) ) return false;
	         if ( !(other instanceof CashFlow) ) return false;
	         CashFlow castOther = (CashFlow) other; 
	         
	         return ( ( this.getEntity()==castOther.getEntity()) 
	        		    || ( this.getEntity()!=null 
	        			     && castOther.getEntity()!=null 
	        			     && this.getEntity().equals(castOther.getEntity()
	        			   ) 
	        		))
	             && ( this.getInternalNumber()==castOther.getInternalNumber());
	   }
	   
	   /**
	    * hashCode
	    */
	   @Override
	   public int hashCode() {
	         int result = 17;
	         result = 37 * result + (int) this.getInternalNumber();
	         return result;
	   }   

}
