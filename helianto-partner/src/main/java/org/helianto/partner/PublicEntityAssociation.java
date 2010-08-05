package org.helianto.partner;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.AbstractAssociation;

/**
 * Association between public entities.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="prtnr_publicEntityAssoc",
    uniqueConstraints = {@UniqueConstraint(columnNames={"parentId", "childId"})}
)
public class PublicEntityAssociation extends AbstractAssociation<PublicEntity, PublicEntity> {

	private static final long serialVersionUID = 1L;

    /**
     * Parent public entity (lazy loaded).
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", nullable=true)
	public PublicEntity getParent() {
        return this.parent;
	}

    /**
     * Child public entity, require two phase store.
     */
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="childId", nullable=true)
	public PublicEntity getChild() {
		return this.child;
	}

	public boolean isKeyEmpty() {
    	if (this.getChild()!=null) {
    		return true;
    	}
    	throw new IllegalArgumentException("Natural key must not be null");
	}

}
