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

	private static final int DEFAULT_TTL = 60*1000;

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
    
    @Column(length=20)
    private String sessionType;
    
    @Column(length=36)
    private String sessionToken;
    
    private Integer ttl = DEFAULT_TTL;
    
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
	
    /**
     * Type constructor.
     * 
     * @param user
     * @param lastEventDate
     * @param sessionType
     */
	public DocumentSession(User user, Date lastEventDate, String sessionType) {
		this(user, lastEventDate);
		this.sessionType = sessionType;
	}
	
    /**
     * Token constructor.
     * 
     * @param user
     * @param lastEventDate
     * @param sessionType
     * @param sessionToken
     */
	public DocumentSession(User user, Date lastEventDate, String sessionType, String sessionToken) {
		this(user, lastEventDate, sessionType);
		this.sessionToken = sessionToken;
	}
	
    /**
     * External id constructor.
     * 
     * @param user
     * @param externalId
     * @param sessionType
     * @param sessionToken
     */
	public DocumentSession(User user, Integer externalId, String sessionType, String sessionToken) {
		this(user, new Date(), sessionType, sessionToken);
		setExternalId(externalId);
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

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * User.
	 */
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

	/**
	 * Last event date.
	 */
	public Date getLastEventDate() {
		if (this.lastEventDate==null) {
			return new Date();
		}
		return lastEventDate;
	}
	public void setLastEventDate(Date lastEventDate) {
		this.lastEventDate = lastEventDate;
	}

	/**
	 * External id.
	 */
	public Integer getExternalId() {
		return externalId;
	}
	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}

	/**
	 * Session type.
	 */
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	
	/**
	 * Session token.
	 */
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	/**
	 * Time to live.
	 */
	public Integer getTtl() {
		if (ttl==null) {
			return DEFAULT_TTL;
		}
		return ttl;
	}
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}
	
	/**
	 * True if user owns this session.
	 * 
	 * @param sessionToken
	 * @param userId
	 */
	public Boolean isSessionHolder(String sessionToken, int userId) {
		return sessionToken.equals(getSessionToken()) && userId == getUserId().intValue();
	}
	
	/**
	 * True if session is not expired.
	 */
	public Boolean isAlive() {
		return (new Date().getTime() - getLastEventDate().getTime()) < getTtl();
	}
	
	/**
	 * True if session is not expired.
	 * 
	 * @param ttl
	 */
	public Boolean isAlive(int ttl) {
		return (new Date().getTime() - getLastEventDate().getTime()) < ttl;
	}
	
	/**
	 * True if session is locked.
	 * 
	 * @param sessionToken
	 * @param userId
	 */
	public Boolean isLocked(String sessionToken, int userId) {
		return isAlive() && !isSessionHolder(sessionToken, userId);
	}
	
	/**
	 * True if session is locked.
	 * 
	 * @param sessionToken
	 * @param userId
	 * @param ttl
	 */
	public Boolean isLocked(String sessionToken, int userId, int ttl) {
		return !isSessionHolder(sessionToken, userId) && isAlive(ttl);
	}
	
	/**
	 * Merge and update key with current date.
	 * 
	 * @param command
	 */
	public DocumentSession mergeAndUpdateKey(DocumentSession command) {
		setLastEventDate(new Date());
		setExternalId(command.getExternalId());
		setSessionType(command.getSessionType());
		setSessionToken(command.getSessionToken());
		setTtl(command.getTtl());
		return this;
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
