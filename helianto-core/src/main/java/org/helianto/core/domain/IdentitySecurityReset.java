package org.helianto.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Temporary tokens to reset consumer secrets. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_reset",
    uniqueConstraints = @UniqueConstraint(columnNames={"resetToken"})
)
public class IdentitySecurityReset implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private int version;
    private IdentitySecurity identitySecurity;
    private String resetToken;
    private Date issueDate;
    private Date expirationDate;
    
    /**
     * Default constructor.
     */
    public IdentitySecurityReset() {
		super();
	}

    /**
     * Identity security constructor.
     * 
     * @param identitySecurity
     */
    public IdentitySecurityReset(IdentitySecurity identitySecurity) {
		this();
	}
    
    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Connection data.
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="identitySecurityId")
	public IdentitySecurity getIdentitySecurity() {
		return identitySecurity;
	}
    public void setIdentitySecurity(IdentitySecurity identitySecurity) {
		this.identitySecurity = identitySecurity;
	}

	/**
	 * Reset token.
	 */
	@Column(length=256)
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	/**
	 * Issue date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * Expiration date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resetToken == null) ? 0 : resetToken.hashCode());
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
		if (!(obj instanceof IdentitySecurityReset)) {
			return false;
		}
		IdentitySecurityReset other = (IdentitySecurityReset) obj;
		if (resetToken == null) {
			if (other.resetToken != null) {
				return false;
			}
		} else if (!resetToken.equals(other.resetToken)) {
			return false;
		}
		return true;
	}
	
	
    

}
