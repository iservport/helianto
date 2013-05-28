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


package org.helianto.inventory.domain;

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

import org.helianto.core.domain.Entity;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.inventory.CardType;
import org.helianto.inventory.InvalidCardException;

/**
 * Represents a range of cards.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_cardset",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
public class CardSet implements java.io.Serializable, Sequenceable {

    /**
     * <code>CardSet</code> factory.
     * 
     * @param entity
     * @param cardType
     * @param internalNumber
     */
    public static CardSet cardSetFactory(Entity entity, CardType cardType, long internalNumber) {
    	CardSet cardSet = new CardSet();
    	cardSet.setEntity(entity);
        cardSet.setInternalNumber(internalNumber);
        cardSet.setCardType(cardType.getPrefix());
        return cardSet;
    }

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private long internalNumber;
    private ProcessDocument process;
    private int cardRange;
    private char cardType;

    /** 
     * Default constructor.
     */
    CardSet() {
    	super();
    	setCardRange(50);
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param internalNumber
     */
    public CardSet(Entity entity, long internalNumber) {
    	super();
    	setEntity(entity);
    	setInternalNumber(internalNumber);
    	setCardRange(50);
    }

    /** 
     * Process document constructor.
     * 
     * @param process
     * @param internalNumber
     */
    public CardSet(ProcessDocument process, long internalNumber) {
    	this(process.getEntity(), internalNumber);
    	setProcess(process);
    	setCardRange(50);
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
     * <<NaturalKey>>Owning entity.
     * @see {@link Entity}
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
     * <<NaturalKey>>Card set serial number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    @Transient
	public static long getInternalNumber(String cardSetLabel) {
		try {
			return Long.parseLong(cardSetLabel.substring(1, 5));
		}
		catch (Exception e) {
			throw new InvalidCardException(cardSetLabel, "unable to resolve card set");
		}
	}
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
    @Transient
	public String getInternalNumberKey() {
		return "CARDSET";
	}
    
    @Transient
    public int getStartNumber() {
    	return 1;
    }

    @Transient
    public String getCardSetLabel() {
    	return new StringBuilder()
    	.append(getCardType())
    	.append(String.format("%1$04d", getInternalNumber()))
    	.toString();
    }

    /**
	 * Card process.
	 */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="processId", nullable=true)
	public ProcessDocument getProcess() {
		return process;
	}
    @Transient
    public String getProcessCode() {
    	if (this.process!=null) {
    		return this.process.getDocCode();
    	}
    	return "";
    }
    @Transient
    public String getProcessName() {
    	if (this.process!=null) {
    		return this.process.getDocName();
    	}
    	return "";
    }
    @Transient
    public String[] getProcessColorChain() {
    	if (this.process!=null && process instanceof ProcessDocument) {
    		return ((ProcessDocument) this.process).getProcessColorChain();
    	}
    	return new String[]{""};
    }
	public void setProcess(ProcessDocument process) {
		this.process = process;
	}

    /**
	 * Card range.
	 */
	@Column(precision=6, scale=0)
	public int getCardRange() {
		return cardRange;
	}
	public void setCardRange(int cardRange) {
		this.cardRange = cardRange;
	}

    /**
	 * Card type.
	 */
	public char getCardType() {
		return cardType;
	}
	@Transient
	public CardType getCardType(char cardType) {
		return CardType.getCardType(cardType);
	}
	public void setCardType(char cardType) {
		this.cardType = cardType;
	}
	public void setCardSetType(CardType cardType) {
		this.cardType = cardType.getPrefix();
	}

	/**
	 * Card factory method.
	 * 
	 * @param cardLabel
	 */
    public Card cardFactory(String cardLabel) {
        return new Card(this, cardLabel);
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("internalNumber").append("='").append(getInternalNumber()).append("' ");			
    	buffer.append("cardType").append("='").append(getCardType()).append("' ");			
    	buffer.append("]");
    	return buffer.toString();
     }

    public String toStringShort() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("#").append(getId()).append(" [");
    	buffer.append(getInternalNumber()).append("] ");			
    	return buffer.toString();
     }

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CardSet) ) return false;
		 CardSet castOther = ( CardSet ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }

}
