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

package org.helianto.core.domain;

import org.helianto.core.domain.enums.OperationMode;

import javax.persistence.*;
import java.util.Locale;

/**
 * The <code>Operator</code> domain class represents a mandatory
 * parent entity to any Helianto based installation. Every domain
 * object is traceable to one (or a chain of) <code>Operator</code>.
 * 
 * <p>
 * As business <code>Entit</code>ies have a direct association to one
 * <code>Operator</code> they
 * may be taken as controllers of a set of <code>Entit</code>ies. The
 * <code>Operator</code> is the connection between <code>Entit</code>ies
 * and objects that implement common business services, like <code>Server</code>s
 * (pop, smtp, etc) or <code>Service</code>s. This is referred as a namespace.
 * </p>
 * <p>
 * Where Helianto based installations are used locally, there should be only
 * one <code>Operator</code> instance, created transparently after the first
 * run. This is the minimum requirement and is appropriate for most cases.
 * </p>
 * <p>
 * If the installation is accessed in a larger network, a greater number of business
 * <code>Entit</code>ies may be expected. The <code>operationMode</code> field may be 
 * assigned to <code>OperationMode.ENTERPRISE</code>. Enterprise <code>Operator</code>s
 * may delegate groups of <code>Entit</code>ies to child <code>Operator</code>s, where
 * <code>operationMode</code> field is assigned to <code>OperationMode.DELEGATED</code>.
 * This is a more complex situation, designed to allow extended control.
 * For example, <code>Operator</code>s may be also useful to represent a geographical
 * limit, bound to a java <code>Locale</code>, or according to any territory
 * arrangement.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see Context
 */
@javax.persistence.Entity
@Table(name="core_operator",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorName"})}
)
public class Operator implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(length=20)
    private String operatorName;
    
    private Locale locale;
    
    private Character operationMode = OperationMode.LOCAL.getValue();
    
    @Column(length=64)
    private String operatorHostAddress = "http://www.helianto.org";
    
    @Column(length=64)
    private String operatorSourceMailAddress = "operator@helianto.org";
    
    @Column(length=20)
    private String defaultEncoding = "ISO-8859-1";
    
    @Column(length=12)
    private String preferredDateFormat;
    
    @Column(length=12)
    private String preferredTimeFormat;
    
    @Column(length=5)
    private String rfc822TimeZone;
    
    /**
     * Default constructor.
     */
    public Operator() {
    	this("");
    }

    /** 
     * Key constructor.
     * 
     * @param operatorName
     */
    public Operator(String operatorName) {
    	this(operatorName, Locale.getDefault());
    }

    /** 
     * Locale constructor.
     * 
     * @param operatorName
     * @param locale
     */
    public Operator(String operatorName, Locale locale) {
    	setOperatorName(operatorName);
        setLocale(locale);
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
     * Operator name.
     */
    public String getOperatorName() {
        return this.operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * Locale.
     */
    public Locale getLocale() {
        return this.locale;
    }
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Operation mode.
     */
    public char getOperationMode() {
        return this.operationMode;
    }
    public void setOperationMode(char operationMode) {
        this.operationMode = operationMode;
    }
    public void setOperationModeAsEnum(OperationMode operationMode) {
        this.operationMode = operationMode.getValue();
    }

    /**
     * Operator host address.
     */
    public String getOperatorHostAddress() {
        return this.operatorHostAddress;
    }
    public void setOperatorHostAddress(String operatorHostAddress) {
        this.operatorHostAddress = operatorHostAddress;
    }

    /**
     * Operator source mail address.
     */
    public String getOperatorSourceMailAddress() {
        return this.operatorSourceMailAddress;
    }
    public void setOperatorSourceMailAddress(String operatorSourceMailAddress) {
        this.operatorSourceMailAddress = operatorSourceMailAddress;
    }

    /**
     * Default encoding.
     */
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    /**
     * Preferred date format.
     */
    public String getPreferredDateFormat() {
        return this.preferredDateFormat;
    }
    public void setPreferredDateFormat(String preferredDateFormat) {
        this.preferredDateFormat = preferredDateFormat;
    }

    /**
     * Preferred time format.
     */
    public String getPreferredTimeFormat() {
        return this.preferredTimeFormat;
    }
    public void setPreferredTimeFormat(String preferredTimeFormat) {
        this.preferredTimeFormat = preferredTimeFormat;
    }

    /**
     * Rfc822 time zone.
     */
    public String getRfc822TimeZone() {
        return this.rfc822TimeZone;
    }
    public void setRfc822TimeZone(String rfc822TimeZone) {
        this.rfc822TimeZone = rfc822TimeZone;
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
