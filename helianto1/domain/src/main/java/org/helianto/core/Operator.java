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

package org.helianto.core;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The <code>Operator</code> domain class represents a mandatory
 * parent entity to any Helianto based installation. Every domain
 * object is traceable to one (or a chain of) <code>Operator</code>.
 *
 * As business <code>Entit</code>ies have a direct association to one
 * <code>Operator</code> they
 * may be taken as controllers of a set of <code>Entit</code>ies. The
 * <code>Operator</code> is the connection between <code>Entit</code>ies
 * and objects that implement common business services, like <code>Server</code>s
 * (pop, smtp, etc) or <code>Service</code>s.
 *
 * Where Helianto based installations are used locally, there should be only
 * one <code>Operator</code> instance, created transparently after the first
 * run. This is the minimum requirement and is appropriate for most cases.
 * The <code>operationMode</code> field may be assigned to <code>OperationMode.LOCAL</code>.
 *
 * If the installation is accessed in a larger network, a greater number of business
 * <code>Entit</code>ies may be expected. The <code>operationMode</code> field may be 
 * assigned to <code>OperationMode.ENTERPRISE</code>. Enterprise <code>Operator</code>s
 * may delegate groups of <code>Entit</code>ies to child <code>Operator</code>s, where
 * <code>operationMode</code> field is assigned to <code>OperationMode.DELEGATED</code>.
 * This is a more complex situation, designed to allow extended control.
 * For example, <code>Operator</code>s may be also useful to represent a geographical
 * limit, bound to a java <code>Locale</code>, or according to any territory
 * arrangement.
 *
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_operator")
public class Operator implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String operatorName;
    private Operator parent;
    private Locale locale;
    private char operationMode;
    private String operatorHostAddress;
    private String operatorSourceMailAddress;
    private String defaultEncoding;
    private String preferredDateFormat;
    private String preferredTimeFormat;
    private String rfc822TimeZone;

    /** default constructor */
    public Operator() {
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
     * OperatorName getter.
     */
    @Column(length=20, unique=true)
    public String getOperatorName() {
        return this.operatorName;
    }
    /**
     * OperatorName setter.
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * Parent getter.
     */
    @ManyToOne()
    @JoinColumn(name="parentId", nullable=true)
    public Operator getParent() {
        return this.parent;
    }
    /**
     * Parent setter.
     */
    public void setParent(Operator parent) {
        this.parent = parent;
    }

    /**
     * Locale getter.
     */
    public Locale getLocale() {
        return this.locale;
    }
    /**
     * Locale setter.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * OperationMode getter.
     */
    public char getOperationMode() {
        return this.operationMode;
    }
    /**
     * OperationMode setter.
     */
    public void setOperationMode(char operationMode) {
        this.operationMode = operationMode;
    }

    /**
     * OperatorHostAddress getter.
     */
    @Column(length=64)
    public String getOperatorHostAddress() {
        return this.operatorHostAddress;
    }
    /**
     * OperatorHostAddress setter.
     */
    public void setOperatorHostAddress(String operatorHostAddress) {
        this.operatorHostAddress = operatorHostAddress;
    }

    /**
     * OperatorSourceMailAddress getter.
     */
    @Column(length=64)
    public String getOperatorSourceMailAddress() {
        return this.operatorSourceMailAddress;
    }
    /**
     * OperatorSourceMailAddress setter.
     */
    public void setOperatorSourceMailAddress(String operatorSourceMailAddress) {
        this.operatorSourceMailAddress = operatorSourceMailAddress;
    }

    /**
     * DefaultEncoding getter.
     */
    @Column(length=20)
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }
    /**
     * DefaultEncoding setter.
     */
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    /**
     * PreferredDateFormat getter.
     */
    @Column(length=12)
    public String getPreferredDateFormat() {
        return this.preferredDateFormat;
    }
    /**
     * PreferredDateFormat setter.
     */
    public void setPreferredDateFormat(String preferredDateFormat) {
        this.preferredDateFormat = preferredDateFormat;
    }

    /**
     * PreferredTimeFormat getter.
     */
    @Column(length=12)
    public String getPreferredTimeFormat() {
        return this.preferredTimeFormat;
    }
    /**
     * PreferredTimeFormat setter.
     */
    public void setPreferredTimeFormat(String preferredTimeFormat) {
        this.preferredTimeFormat = preferredTimeFormat;
    }

    /**
     * Rfc822TimeZone getter.
     */
    @Column(length=5)
    public String getRfc822TimeZone() {
        return this.rfc822TimeZone;
    }
    /**
     * Rfc822TimeZone setter.
     */
    public void setRfc822TimeZone(String rfc822TimeZone) {
        this.rfc822TimeZone = rfc822TimeZone;
    }

    /**
     * <code>Operator</code> factory.
     * 
     * @param operatorName
     */
    public static Operator operatorFactory(String operatorName) {
        Operator operator = new Operator();
        operator.setOperatorName(operatorName);
        return operator;
    }

    /**
     * <code>Operator</code> natural id query.
     */
    @Transient
    public static String getOperatorNaturalIdQueryString() {
        return "select operator from Operator where operator.operatorName = :operatorName ";
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("operatorName").append("='").append(getOperatorName()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Operator) ) return false;
         Operator castOther = (Operator) other; 
         
         return (this.getOperatorName()==castOther.getOperatorName()) || ( this.getOperatorName()!=null && castOther.getOperatorName()!=null && this.getOperatorName().equals(castOther.getOperatorName()) );
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperatorName() == null ? 0 : this.getOperatorName().hashCode() );
         return result;
   }   

}
