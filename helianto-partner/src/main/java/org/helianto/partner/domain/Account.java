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

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractTrunkEntity;
import org.helianto.partner.def.AccountType;
/**
 * Represents accounts.  
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_account",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "accountCode"})}
)
public class Account 
	extends AbstractTrunkEntity 
{

    private static final long serialVersionUID = 1L;
    
    @Column(length=20)
    private String accountCode = "";
    
    @Column(length=64)
    private String accountName = "";
    
    private char accountType = AccountType.ASSET.getValue();

    /** 
     * Default constructor.
     */
    public Account() {
        super();
    }

    /** 
     * Key constructor.
     * 
     * @param entity
     * @param accountCode
     */
    public Account(Entity entity, String accountCode) {
        this();
        setEntity(entity);
        setAccountCode(accountCode);
    }

    /**
     * Account code.
     */
    public String getAccountCode() {
        return this.accountCode;
    }
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * Account name.
     */
    public String getAccountName() {
        return this.accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Account type.
     */
    public char getAccountType() {
        return this.accountType;
    }
    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }
    public void setAccountTypeAsEnum(AccountType accountType) {
        this.accountType = AccountType.ASSET.getValue();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("accountCode").append("='").append(getAccountCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Account) ) return false;
         Account castOther = (Account) other; 
         
         return ((this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ))
             && ((this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         return result;
   }   

}
