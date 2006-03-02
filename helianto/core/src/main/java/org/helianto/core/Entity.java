package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Domain object to represent different business units.
 * </p>
 * <p>
 * For example, if two equipment sets must be kept in different 
 * logical spaces to avoid identity collision, they
 * must be tied to different entities. This is also applicable for many
 * ohter domain classes, like accounts, statements, parts, processes, etc.
 * The <code>Entity</code> is the root for many of such objects and allow
 * for the desirable isolation between two or more organizations, or even
 * smaller units within one organization. In other words, an <code>Entity</code>
 * 'controls' a whole group of domain object instances, or if there is only one,
 * it controls the whole application.
 * </p>
 * <p>
 * A real world entity usually has many related properties, like 
 * address or trade mark. An <code>Entity</code> here, though, is 
 * designed not to hold much information, namely only an unique 
 * alias and a reference to its <code>Supervisor</code>. That makes 
 * it flexible enough to be associated to virtually any real world 
 * entity, even individuals. 
 * </p>
 * <p>
 * A small footprint is also desirable for some serialization strategies
 * like Hibernate's (www.hibernate.org) non-lazy loading.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Entity implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String alias;

    /** nullable persistent field */
    private org.helianto.core.Supervisor supervisor;

    /** full constructor */
    public Entity(String alias, org.helianto.core.Supervisor supervisor) {
        this.alias = alias;
        this.supervisor = supervisor;
    }

    /** default constructor */
    public Entity() {
    }

    /** minimal constructor */
    public Entity(String alias) {
        this.alias = alias;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public org.helianto.core.Supervisor getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(org.helianto.core.Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
