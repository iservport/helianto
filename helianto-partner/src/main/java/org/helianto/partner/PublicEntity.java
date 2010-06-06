package org.helianto.partner;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Entity;
import org.helianto.core.Operator;

/**
 * A registry for public entities.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name = "prtnr_publicentity", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"operatorId", "entityId" }) })
public class PublicEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int version;
	private Operator operator;
	private Entity entity;

	/**
	 * Empty constructor.
	 */
	public PublicEntity() {
	}

	/**
	 * Operator constructor.
	 * 
	 * @param operator
	 */
	public PublicEntity(Operator operator) {
		this();
		setOperator(operator);
	}

	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public PublicEntity(Entity entity) {
		this(entity.getOperator());
		setEntity(entity);
	}

	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Optimistic locking version.
	 */
	@Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Operator.
	 */
	@ManyToOne
	@JoinColumn(name = "operatorId")
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * Entity.
	 */
	@ManyToOne
	@JoinColumn(name = "entityId")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("entity").append("='").append(getEntity()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

	/**
	 * equals
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PublicEntity))
			return false;
		PublicEntity castOther = (PublicEntity) other;

		return ((this.getOperator() == castOther.getOperator()) || (this
				.getOperator() != null
				&& castOther.getOperator() != null && this.getOperator()
				.equals(castOther.getOperator())))
				&& ((this.getEntity() == castOther.getEntity()) || (this
						.getEntity() != null
						&& castOther.getEntity() != null && this.getEntity()
						.equals(castOther.getEntity())));
	}

	/**
	 * hashCode
	 */
	public int hashCode() {
		int result = 17;
		result = 37 * result + (getOperator() == null ? 0 : this.getOperator().hashCode());
		result = 37 * result + (getEntity() == null ? 0 : this.getEntity().hashCode());
		return result;
	}

}
