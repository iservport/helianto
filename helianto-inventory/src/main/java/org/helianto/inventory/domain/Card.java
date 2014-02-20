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

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.inventory.CardState;
import org.helianto.inventory.InvalidCardException;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Cards are companions to transactions.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="inv_card",
    uniqueConstraints = {@UniqueConstraint(columnNames={"cardSetId", "cardLabel"})}
)
public class Card 
	implements java.io.Serializable 
{
	
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="cardSetId", nullable=true)
    private CardSet cardSet;
    
    @Column(length=15)
    private String cardLabel;
    
    @Column(precision=4, scale=0)
    private int cardNumber;
    
	private char cardState = CardState.EMPTY.getValue();

    /** 
     * Default constructor.
     */
    public Card() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param cardSet
     * @param cardLabel
     */
    public Card(CardSet cardSet, String cardLabel) {
    	super();
    	setCardSet(cardSet);
        setCardLabel(cardLabel);
    }

    /** 
     * Number constructor.
     * 
     * @param cardSet
     * @param cardNumber
     */
    public Card(CardSet cardSet, int cardNumber) {
    	super();
    	setCardSet(cardSet);
        setCardLabel(cardNumber);
    }

    /**
     * Validate card label.
     */
	public int validateCardLabel() {
		try {
			int cardNumber = Integer.parseInt(cardLabel.substring(5));
			if (cardNumber > cardSet.getCardRange()) {
				throw new InvalidCardException(cardSet.getCardSetLabel()+cardNumber, "out of range");
			}
			return cardNumber;
		}
		catch (Exception e) {
			System.out.println(e);
			throw new InvalidCardException(cardLabel, "unable to resolve card");
		}
	}
	

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

	/**
	 * <<NaturalKey>>Card set.
	 */
	public CardSet getCardSet() {
		return cardSet;
	}
    public void setCardSet(CardSet cardSet) {
		this.cardSet = cardSet;
	}

    /**
     * Card label.
     */
    public String getCardLabel() {
    	return this.cardLabel;
    }
    public void setCardLabel(String cardLabel) {
    	this.cardLabel = cardLabel;
    }
    @JsonIgnore
    protected void setCardLabel(int cardNumber) {
    	this.cardLabel = formatCardLabel(cardNumber);
    	this.cardNumber = cardNumber;
    }
    
    /**
     * Convenience to format card label using card number.
     * 
     * @param cardNumber
     */
    protected String formatCardLabel(int cardNumber) {
    	return new StringBuilder(cardSet.getCardSetLabel())
    	.append(String.format("%1$05d", cardNumber))
    	.toString();
    }
    
    /**
     * Owning entity.
     */
    public Entity getEntity() {
    	if (this.cardSet!=null) {
    		return this.cardSet.getEntity();
    	}
		return null;
    }
	/**
	 * Card process inherited from a card set.
	 */
	public ProcessDocument getProcess() {
    	if (this.cardSet!=null) {
    		return this.cardSet.getProcess();
    	}
		return null;
	}

    /**
	 * Card type inherited from a card set.
	 */
	public char getCardType() {
    	if (this.cardSet!=null) {
    		return this.cardSet.getCardType();
    	}
		return ' ';
	}
    /**
     * Card number.
     */
    public int getCardNumber() {
        return this.cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    /**
     * Convert the human readable representation of the card to 
     * an integer unique to the card range.
     */
	public static int getInternalNumber(String cardLabel) {
		try {
			return Integer.parseInt(cardLabel.substring(5));
		}
		catch (Exception e) {
			throw new InvalidCardException(cardLabel, "unable to resolve card");
		}
	}

	/**
     * Card state.
     */
    public char getCardState() {
        return this.cardState;
    }
    public void setCardState(char cardState) {
        this.cardState = cardState;
    }
    public void setCardStateAsEnum(CardState cardState) {
        this.cardState = cardState.getValue();
    }

	public void setKey(CardSet cardSet, String cardLabel) {
		setCardSet(cardSet);
		setCardLabel(cardLabel);
	}

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("cardLabel").append("='").append(getCardLabel()).append("' ");			
    	buffer.append("]");
    	return buffer.toString();
     }

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Card) ) return false;
		 Card castOther = ( Card ) other; 
         
		 return ( (this.getCardSet()==castOther.getCardSet()) || ( this.getCardSet()!=null && castOther.getCardSet()!=null && this.getCardSet().equals(castOther.getCardSet()) ) )
             && ( (this.getCardLabel()==castOther.getCardLabel()) || ( this.getCardLabel()!=null && castOther.getCardLabel()!=null && this.getCardLabel().equals(castOther.getCardLabel()) ) );
    }
   
    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + ( getCardSet() == null ? 0 : this.getCardSet().hashCode() );
         result = 37 * result + ( getCardLabel() == null ? 0 : this.getCardLabel().hashCode() );
         return result;
   }

}
