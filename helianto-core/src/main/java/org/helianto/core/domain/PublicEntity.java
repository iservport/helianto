package org.helianto.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.helianto.core.EntityAddress;
import org.helianto.core.domain.enums.PhoneType;
import org.helianto.core.internal.AbstractAddress;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
	implements EntityAddress
{
	
	/**
	 * Exposes the discriminator.
	 */
	public char getDiscriminator() {
		return 'P';
	}

	private static final long serialVersionUID = 1L;
	
    @Version
    private int version;
    
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "entityId")
	private Entity entity;
    
    @Transient
    private Integer entityId;
	
	@Column(length=32)
	private String entityAlias = "";
	
	@Column(length=128)
	private String entityName = "";
	
    @Column(length=128)
    private String nature = "";
    
    @Embedded
    private Phone mainPhone = new Phone();
    
    @Column(length=40)
    private String mainEmail = "";
    
	@JsonIgnore 
	@OneToMany(mappedBy="publicEntity")
	private Set<PublicEntityKey> publicEntityKeys = new HashSet<PublicEntityKey>();

	/**
	 * Empty constructor.
	 */
	public PublicEntity() {
		super();
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Entity.
	 */
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Integer getEntityId() {
		if (getEntity()!=null)  {
			return getEntity().getId();
		}
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	
	/**
	 * <<Transient>> True if Entity has already been installed.
	 * 
	 * <p>
	 * Entity installation requires many steps. Please, check
	 * service layer for installation procedures.
	 * <p>
	 */
	public boolean isEntityInstalled() {
		if (getEntity()!=null) {
			return getEntity().isInstalled();
		}
		return false;
	}
	
	/**
	 * Entity alias
	 */
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
	protected String getInternalEntityAlias() {
		return entityAlias;
	}
	
	/**
	 * Entity name.
	 */
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
    /**
     * Short name.
     */
    public String getShortName() {
    	if (getEntityName()!=null) {
        	if (getEntityName().length() > 20) {
                return getEntityName().substring(0, 20)+"...";
        	}
            return getEntityName();
    	}
    	return "";
    }
    
    /**
     * Private entity nature determining Partners to be maintained as aggregates, as a keyword csv.
     */
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public String[] getNatureAsArray() {
		if (getNature()!=null && getNature().length()>0) {
			return getNature().replace(" ", "").split(",");
		}
		return new String[] {};
	}
	public void setNatureAsArray(String[] natureArray) {
		setNature(Arrays.deepToString(natureArray).replace("[", "").replace("]", "").replace(" ", ""));
	}
    
    /**
     * Main phone.
     */
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
    public String getMainEmail() {
		return mainEmail;
	}
    public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

    /**
     * Keys.
     */
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
	 * Merger.
	 * 
	 * @param command
	 */
	public PublicEntity merge(PublicEntity command) {
		super.merge(command);
		setEntityName(command.getEntityName());
		setMainEmail(command.getMainEmail());
		setNature(command.getNature());
		return this;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PublicEntity)) {
			return false;
		}
		PublicEntity other = (PublicEntity) obj;
		if (entity == null) {
			if (other.entity != null) {
				return false;
			}
		} else if (!entity.equals(other.entity)) {
			return false;
		}
		return true;
	}

}
