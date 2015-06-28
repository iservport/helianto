package org.helianto.core.internal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Base class to persistent domain classes isolated by an {@link Entity}.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractTrunkEntity 
	implements TrunkEntity {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
    @ManyToOne
    @JoinColumn(name="entityId", nullable=true)
    private Entity entity;
    
    @Transient
    private Integer entityId;
    
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
     * Version.
     */
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * <<NaturalKey>> Entity owning the control.
     * 
     * @see {@link Entity}
     */
    public Entity getEntity() {
        return this.entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * <<Transient>> entity id.
     */
    public int getEntityId() {
    	return getEntity()!=null ? getEntity().getId() : entityId;
    }
    public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
    
}
