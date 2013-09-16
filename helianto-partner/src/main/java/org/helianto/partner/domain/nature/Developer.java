package org.helianto.partner.domain.nature;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Represents a relationship to a developer. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@DiscriminatorValue("V")
public class Developer 
	extends Partner 
	implements java.io.Serializable 
{

    private static final long serialVersionUID = 1L;
    private String clientSecret = "";
    private String redirectUri = "";
	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;

    /**
     * <<Transient>> Discriminator.
     */
    @Transient
    public char getDiscriminator() {
    	return 'V';
    }

	/**
	 *  Empty constructor
	 */
    public Developer() {
    	super();
    }

	/**
     * Key constructor.
     * 
     * @param partnerRegistry
     */
    public Developer(PrivateEntity partnerRegistry) {
    	this();
    	setPrivateEntity(partnerRegistry);
    }

	/**
     * Combined constructor, creates also a partnerRegistry.
     * 
     * @param entity
     * @param partnerAlias
     */
    public Developer(Entity entity, String partnerAlias) {
    	this();
    	setPrivateEntity(new PrivateEntity(entity, partnerAlias));
    }

    /**
     * Partner constructor.
     * 
	 * <p>
	 * Read the backing {@link PrivateEntity} from a partner to associate the new instance to it.
	 * </p>
	 * 
     * @param partner
     */
    public Developer(Partner partner) {
    	this(partner.getPrivateEntity());
    	if (partner.getClass().isAssignableFrom(getClass())) {
    		throw new IllegalArgumentException("Not allowed to create a partner from this source.");
    	}
    }

    /**
     * Protected client secret.
     */
	@Column(length=60)
    public String getClientSecret() {
		return clientSecret;
	}
    public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
    
    /**
     * Link to OAuth redirection endpoint URI (like http://mysite/callback).
     */
	@Column(length=256)
    public String getRedirectUri() {
		return redirectUri;
	}
    public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
    
	/**
	 * The access token validity period for this developer.
	 */
    public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}
    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}
    
	/**
	 * The refresh token validity period for this developer. 
	 * 
	 * Zero or negative for default value set by token service.
	 */
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

    /**
     * equals
     */
    public boolean equals(Object other) {
          if ( !(other instanceof Developer) ) return false;
          return super.equals(other);
    }
    
}
