package org.helianto.core.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Aliases for identities.
 * 
 * @author Mauricio Fernandes de Castro              
 */
@javax.persistence.Entity
@Table(name="core_alias",
    uniqueConstraints = {@UniqueConstraint(columnNames={"identityId", "identityAlias"})}
)
public class IdentityAlias implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private int version;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="identityId")
    private Identity identity;
    
    @Column(length=64)
	private String identityAlias = "";
    
    /**
     * Constructor.
     */
    public IdentityAlias() {
		super();
	}

    /**
     * Constructor.
     * 
     * @param identity
     * @param identityAlias
     */
    public IdentityAlias(Identity identity, String identityAlias) {
		this();
		setIdentity(identity);
		setIdentityAlias(identityAlias);
	}

    /**
     * Primary key.
     */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Consistency.
	 */
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Identity.
	 */
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	/**
	 * Identity alias.
	 */
	public String getIdentityAlias() {
		return identityAlias;
	}
	public void setIdentityAlias(String identityAlias) {
		this.identityAlias = identityAlias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identity == null) ? 0 : identity.hashCode());
		result = prime * result
				+ ((identityAlias == null) ? 0 : identityAlias.hashCode());
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
		if (!(obj instanceof IdentityAlias)) {
			return false;
		}
		IdentityAlias other = (IdentityAlias) obj;
		if (identity == null) {
			if (other.identity != null) {
				return false;
			}
		} else if (!identity.equals(other.identity)) {
			return false;
		}
		if (identityAlias == null) {
			if (other.identityAlias != null) {
				return false;
			}
		} else if (!identityAlias.equals(other.identityAlias)) {
			return false;
		}
		return true;
	}

}
