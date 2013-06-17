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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Temporary tokens to reset consumer secrets. 
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_connection",
    uniqueConstraints = @UniqueConstraint(columnNames={"resetToken"})
)
public class ConnectionDataReset implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private long id;
    private int version;
    private ConnectionData connectionData;
    private String resetToken;
    private Date issueDate;
    private Date expirationDate;
    
    /**
     * Default constructor.
     */
    public ConnectionDataReset() {
		super();
	}

    /**
     * Connection data constructor.
     * 
     * @param connectionData
     */
    public ConnectionDataReset(ConnectionData connectionData) {
		this();
	}
    
    //TODO move to ConnectionDataMgr.
    @Transient
    public void initToken(PasswordEncoder encoder) {
    	encoder.encode(String.valueOf(getConnectionData().getId()));
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
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
    @ManyToOne
    @JoinColumn(name="connectionDataId")
	public ConnectionData getConnectionData() {
		return connectionData;
	}
	public void setConnectionData(ConnectionData connectionData) {
		this.connectionData = connectionData;
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
		if (!(obj instanceof ConnectionDataReset)) {
			return false;
		}
		ConnectionDataReset other = (ConnectionDataReset) obj;
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
