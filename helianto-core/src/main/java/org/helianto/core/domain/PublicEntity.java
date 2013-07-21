package org.helianto.core.domain;

import java.util.Arrays;
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

import org.helianto.core.EntityAddress;
import org.helianto.core.def.PhoneType;
import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.internal.AbstractAddress;

/**
 * A registry for public entities.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name = "core_public"
	, uniqueConstraints = { @UniqueConstraint(columnNames = {"entityId", "entityAlias", "type"}) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("P")
public class PublicEntity 
	extends AbstractAddress 
	implements 
	  RootEntity
	, EntityAddress {
	
	/**
	 * Exposes the discriminator.
	 */
	@Transient
	public char getDiscriminator() {
		return 'P';
	}

	private static final long serialVersionUID = 1L;
	private int version;
	private Entity entity;
	private String entityAlias = "";
	private String entityName = "";
    private String nature = "";
    private Phone mainPhone;
    private String mainEmail = "";
	private Set<PublicEntityKey> publicEntityKeys = new HashSet<PublicEntityKey>();

	/**
	 * Empty constructor.
	 */
	public PublicEntity() {
		super();
		setMainPhone(new Phone());
		setMainEmail("");
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 */
	public PublicEntity(Entity entity) {
		this();
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
	@Transient
	public Operator getOperator() {
		if (getEntity()!=null) {
			return getEntity().getOperator();
		}
		return null;
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
	 * Entity alias
	 */
	@Column(length=20)
	public String getEntityAlias() {
		if (getDiscriminator()=='P' && getEntity()!=null) {
			return getEntity().getAlias();
		}
		return getInternalEntityAlias();
	}
	public void setEntityAlias(String entityAlias) {
		this.entityAlias = entityAlias;
	}
	
	/**
	 * Subclasses overriding this can control how the alias is formed.
	 * 
	 * <p>
	 * Default implementation forces entity alias to follow the owning entity alias.
	 * </p>
	 */
	@Transient
	protected String getInternalEntityAlias() {
		return entityAlias;
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
     * Short name.
     */
    @Transient
    public String getShortName() {
    	if (getEntityName().length() > 20) {
            return getEntityName().substring(0, 20)+"...";
    	}
        return getEntityName();
    }
    
    /**
     * Private entity nature determining Partners to be maintained as aggregates, as a keyword csv.
     */
    @Column(length=128)
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	@Transient
	public String[] getNatureAsArray() {
		if (getNature()!=null && getNature().length()>0) {
			return getNature().replace(" ", "").split(",");
		}
		return new String[] {};
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(Arrays.deepToString(natureArray).replace("[", "").replace("]", "").replace(" ", ""));
	}
    
//	/**
//	 * Type of public entity.
//	 */
//	public char getPublicEntityType() {
//		return publicEntityType;
//	}
//	public void setPublicEntityType(char publicEntityType) {
//		this.publicEntityType = publicEntityType;
//	}
//	public void setPublicEntityTypeEnum(PublicEntityType publicEntityType) {
//		this.publicEntityType = publicEntityType.getValue();
//	}
//	
//	/**
//	 * Visibility of public entity.
//	 */
//	public char getPublicEntityVisibility() {
//		return publicEntityVisibility;
//	}
//	public void setPublicEntityVisibility(char publicEntityVisibility) {
//		this.publicEntityVisibility = publicEntityVisibility;
//	}
//	public void setPublicEntityVisibility(PublicEntityVisibility publicEntityVisibility) {
//		this.publicEntityVisibility = publicEntityVisibility.getValue();
//	}
//	
    /**
     * Main phone.
     */
    @Embedded
    public Phone getMainPhone() {
		return mainPhone;
	}
    public void setMainPhone(Phone mainPhone) {
		this.mainPhone = mainPhone;
	}
    
    // delegate phone
    
    /**
     * Phone number.
     */
    @Transient
    public String getPhoneNumber() {
    	if (getMainPhone()!=null) {
    		return getMainPhone().getPhoneNumber();
    	}
        return "";
    }
    public void setPhoneNumber(String phoneNumber) {
    	if (getMainPhone()!=null) {
    		getMainPhone().setPhoneNumber(phoneNumber);
    	}
    }

    /**
     * Area code.
     */
    @Transient
    public String getAreaCode() {
    	if (getMainPhone()!=null) {
    		return getMainPhone().getAreaCode();
    	}
        return "";
    }
    public void setAreaCode(String areaCode) {
    	if (getMainPhone()!=null) {
    		getMainPhone().setAreaCode(areaCode);
    	}
    }
    
    /**
     * Branch.
     */
    @Transient
    public String getBranch() {
    	if (getMainPhone()!=null) {
    		return getMainPhone().getBranch();
    	}
        return "";
	}
    public void setBranch(String branch) {
    	if (getMainPhone()!=null) {
    		getMainPhone().setBranch(branch);
    	}
	}

    /**
     * Phone type.
     */
    @Transient
    public char getPhoneType() {
    	if (getMainPhone()!=null) {
    		return getMainPhone().getPhoneType();
    	}
        return PhoneType.MAIN.getValue();
    }
    public void setPhoneType(char phoneType) {
    	if (getMainPhone()!=null) {
    		getMainPhone().setPhoneType(phoneType);
    	}
    }
    public void setPhoneTypeAsEnum(PhoneType phoneType) {
    	if (getMainPhone()!=null) {
    		getMainPhone().setPhoneTypeAsEnum(phoneType);
    	}
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

    /**
     * Keys.
     */
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
			setAddressClassifier(publicAddress.getAddressClassifier());
			setAddress1(publicAddress.getAddress1());
			setAddress2(publicAddress.getAddress2());
			setPostalCode(publicAddress.getPostalCode());
			setCity(publicAddress.getCity());
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
