package org.helianto.partner;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.RootEntity;
import org.helianto.core.base.AbstractAddress;
import org.helianto.partner.base.AbstractPhone;

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
public class PublicEntity extends AbstractAddress implements RootEntity, BusinessUnit {

	private static final long serialVersionUID = 1L;
	private int version;
	private Operator operator;
	private Entity entity;
	private String newEntityAlias;
	private Identity newEntityManager;
	private String entityName;
	private char publicEntityType;
	private char publicEntityVisibility;
    private AbstractPhone mainPhone;
    private String mainEmail;
	private Set<PublicEntityKey> publicEntityKeys = new HashSet<PublicEntityKey>();

	/**
	 * Empty constructor.
	 */
	public PublicEntity() {
		super();
		setPublicEntityTypeEnum(PublicEntityType.NOT_INFORMED);
		setPublicEntityVisibility(PublicEntityVisibility.REGISTERED);
		setMainPhone(new AbstractPhone());
		setMainEmail("");
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
	 * <<Transient>> Entity alias.
	 */
	@Transient
	public String getEntityAlias() {
		if (getEntity()!=null) {
			return getEntity().getAlias();
		}
		return "";
	}
	
	/**
	 * <<Transient>> True if Entity has already been installed.
	 * 
	 * <p>
	 * Entity installation requires many steps. Please, check
	 * service layer for installation procedures.
	 * <p>
	 */
	@Transient
	public boolean isEntityInstalled() {
		if (getEntity()!=null) {
			return getEntity().isInstalled();
		}
		return false;
	}
	
	/**
	 * <<Transient>> Should be used only to install a new Entity.
	 * 
	 * @see PublicEntity#isEntityInstalled()
	 */
	@Transient
	public String getNewEntityAlias() {
		return this.newEntityAlias;
	}
	public void setNewEntityAlias(String newEntityAlias) {
		this.newEntityAlias = newEntityAlias;
	}
	
	/**
	 * <<Transient>> Should be used only to install a new Entity.
	 * 
	 * @see PublicEntity#isEntityInstalled()
	 */
	@Transient
	public Identity getNewEntityManager() {
		return newEntityManager;
	}
	public void setNewEntityManager(Identity newEntityManager) {
		this.newEntityManager = newEntityManager;
	}
	
	/**
	 * <<Transient>> Should be used only to install a new Entity.
	 * 
	 * @see PublicEntity#isEntityInstalled()
	 */
	@Transient
	public boolean preProcessEntityInstallation() {
		if (!isEntityInstalled()) {
			getEntity().setAlias(getNewEntityAlias());
			getEntity().setManager(getNewEntityManager());
			return true;
		}
		return false;
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
	public void setPublicEntityTypeEnum(PublicEntityType publicEntityType) {
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
	
    /**
     * Main phone.
     */
    @Embedded
    public AbstractPhone getMainPhone() {
		return mainPhone;
	}
    public void setMainPhone(AbstractPhone mainPhone) {
		this.mainPhone = mainPhone;
	}
    
    /**
     * Main e-mail.
     */
    @Column(length=40)
    public String getMainEmail() {
		return mainEmail;
	}
    public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

	@OneToMany(mappedBy="publicEntity")
	public Set<PublicEntityKey> getPublicEntityKeys() {
		return publicEntityKeys;
	}
	public void setPublicEntityKeys(Set<PublicEntityKey> publicEntityKeys) {
		this.publicEntityKeys = publicEntityKeys;
	}

	/**
	 * Update fields provided by <code>PublicAddress</code>.
	 * 
	 * @param publicAddress
	 */
	public void setPublicAddress(PublicAddress publicAddress) {
		if (publicAddress!=null) {
			setAddress1(publicAddress.getAddress1());
			setAddress2(publicAddress.getAddress2());
			setPostalCode(publicAddress.getPostalCode());
			setProvince(publicAddress.getProvince());
		}
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
