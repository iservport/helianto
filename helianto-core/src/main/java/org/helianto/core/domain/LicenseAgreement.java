package org.helianto.core.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.internal.AbstractEvent;

/**
 * A class to represent the actual agreement acceptance.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="lic_agreement",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "licenseId"})
    , @UniqueConstraint(columnNames={"providerId", "licenseId"})}
)
public class LicenseAgreement 
	extends AbstractEvent
{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="licenseId")
	private License license;

	@ManyToOne
	@JoinColumn(name="providerId")
	private Entity provider;
	
	@Transient
	private int authenticatedEntityId;

	@Transient
	private int authenticatedUserId;

	@Transient
	private int authenticatedIdentityId;

	@Transient
	private int authenticatedProviderId;

	/**
	 * Constructor.
	 */
	public LicenseAgreement() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param entity
	 * @param license
	 */
	public LicenseAgreement(Entity entity, License license) {
		this();
		setEntity(entity);
		setLicense(license);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param entity
	 * @param license
	 * @param provider
	 */
	public LicenseAgreement(Entity entity, License license, Entity provider) {
		this();
		setEntity(entity);
		setLicense(license);
		setProvider(provider);
	}
	
	/**
	 * The license to agree with.
	 */
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	
	/**
	 * The agreeing entity.
	 */
	public Entity getProvider() {
		return provider;
	}
	public void setProvider(Entity provider) {
		this.provider = provider;
	}
	
	public int getAuthenticatedEntityId() {
		return authenticatedEntityId;
	}
	public void setAuthenticatedEntityId(int authenticatedEntityId) {
		this.authenticatedEntityId = authenticatedEntityId;
	}
	
	public int getAuthenticatedUserId() {
		return authenticatedUserId;
	}
	public void setAuthenticatedUserId(int authenticatedUserId) {
		this.authenticatedUserId = authenticatedUserId;
	}
	
	public int getAuthenticatedIdentityId() {
		return authenticatedIdentityId;
	}
	public void setAuthenticatedIdentityId(int authenticatedIdentityId) {
		this.authenticatedIdentityId = authenticatedIdentityId;
	}
	
	public int getAuthenticatedProviderId() {
		return authenticatedProviderId;
	}
	public void setAuthenticatedProviderId(int authenticatedProviderId) {
		this.authenticatedProviderId = authenticatedProviderId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEntity() == null) ? 0 : getEntity().hashCode());
		result = prime * result + ((getLicense() == null) ? 0 : getLicense().hashCode());
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
		if (!(obj instanceof LicenseAgreement)) {
			return false;
		}
		LicenseAgreement other = (LicenseAgreement) obj;
		if (getEntity() == null) {
			if (other.getEntity() != null) {
				return false;
			}
		} else if (!getEntity().equals(other.getEntity())) {
			return false;
		}
		if (getLicense() == null) {
			if (other.getLicense() != null) {
				return false;
			}
		} else if (!getLicense().equals(other.getLicense())) {
			return false;
		}
		return true;
	}
	
}
