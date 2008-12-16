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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
/**
 * <p>
 * Represents accounts.  
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_account",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "accountCode"})}
)
public class Account implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Entity entity;
    private String accountCode;
    private String accountName;
    private char accountType;

    /** default constructor */
    public Account() {
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
     * Entity getter.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="entityId", nullable=true)
    public Entity getEntity() {
        return this.entity;
    }
    /**
     * Entity setter.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * AccountCode getter.
     */
    @Column(length=20)
    public String getAccountCode() {
        return this.accountCode;
    }
    /**
     * AccountCode setter.
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * AccountName getter.
     */
    @Column(length=32)
    public String getAccountName() {
        return this.accountName;
    }
    /**
     * AccountName setter.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * AccountType getter.
     */
    public char getAccountType() {
        return this.accountType;
    }
    /**
     * AccountType setter.
     */
    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }

    /**
     * <code>Account</code> factory. Default as AccountType.ASSET.
     * 
     * @param entity
     * @param accountCode
     */
    public static Account accountFactory(Entity entity, String accountCode) {
        Account account = new Account();
        account.setEntity(entity);
        account.setAccountCode(accountCode);
        account.setAccountType(AccountType.ASSET.getValue());
        return account;
    }

    /**
     * <code>Account</code> natural id query.
     */
    @Transient
    public static String getAccountNaturalIdQueryString() {
        return "select account from Account account where account.entity = ? and account.accountCode = ? ";
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
