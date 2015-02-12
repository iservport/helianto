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

package org.helianto.order.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractTrunkEntity;
import org.helianto.order.def.TokenState;

/**
 * Tokens are companions to transactions.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="ord_token",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "tokenLabel"})}
)
public class Token 
	extends AbstractTrunkEntity
{
	
    private static final long serialVersionUID = 1L;
    
    @Column(length=15)
    private String tokenLabel = "";
    
	private char tokenState = TokenState.EMPTY.getValue();

    /** 
     * Internal constructor.
     * 
     * @param tokenLabel
     */
	protected Token(String tokenLabel) {
    	super();
        setTokenLabel(tokenLabel);
    }

    /** 
     * Default constructor.
     */
	public Token() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param series
     * @param tokenLabel
     */
    public Token(Entity entity, String tokenLabel) {
    	this();
    	setEntity(entity);
    	setTokenLabel(tokenLabel);
     }

    /**
     * Token label.
     */
    public String getTokenLabel() {
		return tokenLabel;
	}
    public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}
    
	/**
     * Token state.
     */
	public char getTokenState() {
		return tokenState;
	}
	public void setTokenState(char tokenState) {
		this.tokenState = tokenState;
	}
	
	/**
	 * Convenience to set token state as enum.
	 * 
	 * @param tokenState
	 */
	public void setTokenStateAsEnum(TokenState tokenState) {
		this.tokenState = tokenState.getValue();
	}

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("tokenLabel").append("='").append(getTokenLabel()).append("' ");			
    	buffer.append("]");
    	return buffer.toString();
     }

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Token) ) return false;
		 Token castOther = ( Token ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && ( (this.getTokenLabel()==castOther.getTokenLabel()) || ( this.getTokenLabel()!=null && castOther.getTokenLabel()!=null && this.getTokenLabel().equals(castOther.getTokenLabel()) ) );
    }
   
    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getTokenLabel() == null ? 0 : this.getTokenLabel().hashCode() );
         return result;
   }

}
