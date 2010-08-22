package org.helianto.partner;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "prtnr_public", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"operatorId", "entityId", "type" }) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("P")
public class PublicEntity extends AbstractAddress {

	private static final long serialVersionUID = 1L;
	private int version;
	private Operator operator;
	private Entity entity;
	private String entityName;
	private char publicEntityType;
	private char publicEntityVisibility;
	private Set<PublicEntityKey> publicEntityKeys = new HashSet<PublicEntityKey>();

	/**
	 * Empty constructor.
	 */
	public PublicEntity() {
		super();
		setPublicEntityType(PublicEntityType.NOT_INFORMED);
		setPublicEntityVisibility(PublicEntityVisibility.REGISTERED);
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
	 * Entity name.
	 */
	@Column(length=64)
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	/**
	 * Type of public entity.
	 */
	public char getPublicEntityType() {
		return publicEntityType;
	}
	public void setPublicEntityType(char publicEntityType) {
		this.publicEntityType = publicEntityType;
	}
	public void setPublicEntityType(PublicEntityType publicEntityType) {
		this.publicEntityType = publicEntityType.getValue();
	}
	
	/**
	 * Visibility of public entity.
	 */
	public char getPublicEntityVisibility() {
		return publicEntityVisibility;
	}
	public void setPublicEntityVisibility(char publicEntityVisibility) {
		this.publicEntityVisibility = publicEntityVisibility;
	}
	public void setPublicEntityVisibility(PublicEntityVisibility publicEntityVisibility) {
		this.publicEntityVisibility = publicEntityVisibility.getValue();
	}
	
	@OneToMany(mappedBy="publicEntity")
	public Set<PublicEntityKey> getPublicEntityKeys() {
		return publicEntityKeys;
	}
	public void setPublicEntityKeys(Set<PublicEntityKey> publicEntityKeys) {
		this.publicEntityKeys = publicEntityKeys;
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
