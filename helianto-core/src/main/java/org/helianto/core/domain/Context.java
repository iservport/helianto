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

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The <code>Context</code> domain class represents a mandatory
 * parent entity to any Helianto based installation. Every domain
 * object is traceable to one <code>Context</code> (except the class
 * <code>Identity</code>).
 * 
 * <p>
 * The most common use case will require one single <code>Context</code> instance, 
 * which is created transparently after the first run. If the installation is accessed in a 
 * larger network, a greater number of entities may be expected. In such cases, 
 * additional context instances may be created to isolate entities from each other.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_operator",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorName"})}
)
public class Context implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    /* legacy column */
    @Column(length=20, name="operatorName")
    private String contextName;
    
    private Locale locale;
    
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
    
    private Character operationMode = 'S';
    
    /** 
     * Default constructor.
     */
    public Context() {
    	super();
    }

    /** 
     * Key constructor.
     * 
     * @param contextName
     */
    public Context(String contextName) {
    	this(contextName, Locale.getDefault());
    }

    /** 
     * Locale constructor.
     * 
     * @param contextName
     * @param locale
     */
    public Context(String contextName, Locale locale) {
    	this();
    	setContextName(contextName);
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
     * Context name.
     */
    public String getContextName() {
        return this.contextName;
    }
    public void setContextName(String contextName) {
        this.contextName = contextName;
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
    
    public Character getOperationMode() {
		return operationMode;
	}
    public void setOperationMode(Character operationMode) {
		this.operationMode = operationMode;
	}

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("operatorName").append("='").append(getContextName()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Context) ) return false;
         Context castOther = (Context) other; 
         
         return (this.getContextName()==castOther.getContextName()) || ( this.getContextName()!=null && castOther.getContextName().equals(castOther.getContextName()) );
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getContextName() == null ? 0 : this.getContextName().hashCode() );
         return result;
   }   

}
