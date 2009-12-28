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

import java.util.Date;

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
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.document.AbstractDocument;
import org.helianto.partner.Partner;

/**
 * Cash flow.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="fin_cashflow",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("C")
public class CashFlow extends AbstractDocument implements Comparable<CashFlow> {

	private static final long serialVersionUID = 1L;
	private Partner partner;
    private Identity owner;
    private Date issueDate;
    private Date dueDate;

	
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
		this(partner.getPartnerRegistry().getEntity());
		setPartner(partner);
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
	 * The owner.
	 */
	@ManyToOne
	@JoinColumn(name="ownerId")
	public Identity getOwner() {
		return owner;
	}
	public void setOwner(Identity owner) {
		this.owner = owner;
	}
	
	/**
	 * Cash flow issue date
	 */
    @Temporal(TemporalType.TIMESTAMP)
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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
	 * Implements comparable using <code>docCode</code>.
	 */
	public int compareTo(CashFlow next) {
		return this.getDocCode().compareTo(next.getDocCode());
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof CashFlow) {
			return super.equals(other);
		}
		return false;
	}

}
