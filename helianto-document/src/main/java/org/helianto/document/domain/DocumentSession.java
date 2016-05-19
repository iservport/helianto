package org.helianto.document.domain;

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

import org.helianto.user.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Document session, required to avoid race conditions during persistence.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="doc_session",
    uniqueConstraints = {@UniqueConstraint(columnNames={"userId", "lastEventDate"})}
)
public class DocumentSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    
    @Transient
    private Integer userId;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEventDate;
    
    private Integer externalId;
    
    @Column(length=12)
    private String sessionType;
    
    /**
     * Constructor
     */
	public DocumentSession() {
		super();
	}    

    /**
     * Key constructor.
     * 
     * @param user
     * @param lastEventDate
     */
	public DocumentSession(User user, Date lastEventDate) {
		this();
		this.user = user;
		this.lastEventDate = lastEventDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * <<Transient>> user id.
	 */
	public Integer getUserId() {
		if (getUser()!=null) {
			return getUser().getId();
		}
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLastEventDate() {
		return lastEventDate;
	}
	public void setLastEventDate(Date lastEventDate) {
		this.lastEventDate = lastEventDate;
	}

	public Integer getExternalId() {
		return externalId;
	}
	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}

	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastEventDate == null) ? 0 : lastEventDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentSession other = (DocumentSession) obj;
		if (lastEventDate == null) {
			if (other.lastEventDate != null)
				return false;
		} else if (!lastEventDate.equals(other.lastEventDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
    
}
