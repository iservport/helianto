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

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
/**
 *              
 * <p>
 * Domain object to represent the logical namespace of a business
 * organization or individual.
 * </p>
 * <p>
 * For example, if two equipment sets must be didtinguished in 
 * logical spaces to avoid identity collision, they
 * must be associated to different entities. This is also applicable for many
 * ohter domain classes, like accounts, statements, parts, processes, etc.
 * The <code>Entity</code> is the root for many of such objects and allow
 * for the desirable isolation between two or more organizations, or even
 * smaller units within one organization. In other words, an <code>Entity</code>
 * 'controls' a whole group of domain object instances.
 * </p>
 * <p>
 * A real world entity usually has many related properties, like 
 * address or trade mark. An <code>Entity</code> here, though, is 
 * designed not to hold much information, namely only an unique name. That makes 
 * it flexible enough to be associated to virtually any real world 
 * entity, even individuals. 
 * </p>
 * <p>
 * A small footprint is also desirable for some serialization strategies
 * like Hibernate's (www.hibernate.org) non-lazy loading.
 * </p>
 * @author Mauricio Fernandes de Castro
 *              
 *      
 */
@javax.persistence.Entity
@Table(name="core_entity",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "alias"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("0")
public class Entity implements java.io.Serializable {

    /**
     * <code>Entity</code> factory.
     * 
     * @param operator
     * @param alias
     */
    public static Entity entityFactory(Operator operator, String alias) {
        Entity entity = new Entity();
        entity.setOperator(operator);
        entity.setAlias(alias);
        return entity;
    }

    private static final long serialVersionUID = 1L;
    private long id;
    private int version;
    private Operator operator;
    private String alias;
    private Set<UserGroup> users = new HashSet<UserGroup>(0);
    private List<UserGroup> userList;

    /** 
     * Default constructor.
     */
    public Entity() {
    }

    /** 
     * Operator constructor.
     * 
     * @param operator
     */
    public Entity(Operator operator) {
    	this();
    	setOperator(operator);
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Operator, lazy loaded.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    @Transient
    public Locale getLocale() {
    	// TODO create locale field.
    	return Locale.getDefault();
    }

    /**
     * Alias.
     */
    @Column(length=20)
    public String getAlias() {
        return this.alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * User group set.
     */
    @OneToMany(mappedBy="entity")
    public Set<UserGroup> getUsers() {
		return users;
	}
	public void setUsers(Set<UserGroup> users) {
		this.users = users;
	}
    /**
     * <<Transient>> User list.
     */
    @Transient
    public List<UserGroup> getUserList() {
		return userList;
	}
	public void setUserList(List<UserGroup> userList) {
		this.userList = userList;
	}

	/**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("alias").append("='").append(getAlias()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof Entity) ) return false;
         Entity castOther = (Entity) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         return result;
   }   

}
