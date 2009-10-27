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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
 */
@javax.persistence.Entity
@Table(name="core_operator",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorName"})}
)
public class Operator implements java.io.Serializable {

    /**
     * Factory method.
     *  
     * @param operatorName
     * @param locale if null, default is Locale.getDefault()
     * 
     * @see OperationMode
     */
    public static Operator operatorFactory(String operatorName, Locale locale) {
        Operator operator = new Operator();
        operator.setOperatorName(operatorName);
        if (locale==null) {
            operator.setLocale(Locale.getDefault());
        } else {
            operator.setLocale(locale);
        }
        return operator;
    }

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
    private Set<KeyType> keyTypes = new HashSet<KeyType>();
    private Set<Province> provinces = new HashSet<Province>();
	private Map<String, Service> serviceMap = new HashMap<String, Service>();

    /** default constructor */
    public Operator() {
    	setOperationMode(OperationMode.LOCAL);
        setOperatorSourceMailAddress("operator@helianto.org");
        setDefaultEncoding("ISO-8859-1");
        setOperatorHostAddress("http://www.helianto.org");
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
     * Operator name.
     */
    @Column(length=20)
    public String getOperatorName() {
        return this.operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * Parent operator, if any.
     */
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parentId", nullable=true)
    public Operator getParent() {
        return this.parent;
    }
    public void setParent(Operator parent) {
        this.parent = parent;
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
    public void setOperationMode(OperationMode operationMode) {
        this.operationMode = operationMode.getValue();
    }

    /**
     * Operator host address.
     */
    @Column(length=64)
    public String getOperatorHostAddress() {
        return this.operatorHostAddress;
    }
    public void setOperatorHostAddress(String operatorHostAddress) {
        this.operatorHostAddress = operatorHostAddress;
    }

    /**
     * Operator source mail address.
     */
    @Column(length=64)
    public String getOperatorSourceMailAddress() {
        return this.operatorSourceMailAddress;
    }
    public void setOperatorSourceMailAddress(String operatorSourceMailAddress) {
        this.operatorSourceMailAddress = operatorSourceMailAddress;
    }

    /**
     * Default encoding.
     */
    @Column(length=20)
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    /**
     * Preferred date format.
     */
    @Column(length=12)
    public String getPreferredDateFormat() {
        return this.preferredDateFormat;
    }
    public void setPreferredDateFormat(String preferredDateFormat) {
        this.preferredDateFormat = preferredDateFormat;
    }

    /**
     * Preferred time format.
     */
    @Column(length=12)
    public String getPreferredTimeFormat() {
        return this.preferredTimeFormat;
    }
    public void setPreferredTimeFormat(String preferredTimeFormat) {
        this.preferredTimeFormat = preferredTimeFormat;
    }

    /**
     * Rfc822 time zone.
     */
    @Column(length=5)
    public String getRfc822TimeZone() {
        return this.rfc822TimeZone;
    }
    public void setRfc822TimeZone(String rfc822TimeZone) {
        this.rfc822TimeZone = rfc822TimeZone;
    }

    /**
     * Key type set.
     */
    @OneToMany(mappedBy="operator", cascade={ CascadeType.PERSIST, CascadeType.MERGE },
    		fetch=FetchType.LAZY)
    public Set<KeyType> getKeyTypes() {
		return keyTypes;
	}
	public void setKeyTypes(Set<KeyType> keyTypes) {
		this.keyTypes = keyTypes;
	}

    /**
     * Province set.
     */
    @OneToMany(mappedBy="operator", cascade={ CascadeType.PERSIST, CascadeType.MERGE },
    		fetch=FetchType.LAZY)
    public Set<Province> getProvinces() {
		return provinces;
	}
	public void setProvinces(Set<Province> provinces) {
		this.provinces = provinces;
	}

	/**
	 * Service map, eagerly loaded.
	 */
	@OneToMany(mappedBy="operator", cascade={ CascadeType.PERSIST, CascadeType.MERGE },
		fetch=FetchType.EAGER)
	@MapKey(name="serviceName")
    public Map<String, Service> getServiceMap() {
		return serviceMap;
	}
	public void setServiceMap(Map<String, Service> serviceMap) {
		this.serviceMap = serviceMap;
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