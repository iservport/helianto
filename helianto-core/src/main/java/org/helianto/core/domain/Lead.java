package org.helianto.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * An individual who has provided contact information and, in doing so, pointed toward a 
 * potential sales opportunity.
 * 
 * @author Eldevan Nery Junior
 */
@javax.persistence.Entity
@Table(name="core_lead",
	uniqueConstraints = {@UniqueConstraint(columnNames={"token"})}
)
public class Lead implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
	@Column(length=64)
	private String principal = "";
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate = new Date();

    @Column(length=64)
    private String firstName = "";
    
    @Column(length=64)
    private String lastName = "";
    
    @Column(length=36)
    private String token;
    
    @Column(length=39)
    private String ipAddress;
    
	/**
	 * Default constructor.
	 */
	public Lead() {
		super();
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param principal
	 */
	public Lead(String principal, Date issueDate) {
		this();
		this.principal = principal;
		this.issueDate = issueDate;
	}

	/**
	 * Form constructor.
	 * 
	 * @param id
	 * @param principal
	 * @param issueDate
	 */
	public Lead(int id, String principal, Date issueDate) {
		this(principal, issueDate);
		this.id = id;
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
     * E-mail or other contact information.
     */
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * Issue date.
	 */
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

    /**
     * First name.
     */
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name.
     */
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Token.
     */
    public String getToken() {
		return token;
	}
    public void setToken(String token) {
		this.token = token;
	}
    
    /**
     * Ip address.
     */
    public String getIpAddress() {
		return ipAddress;
	}
    public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		Lead other = (Lead) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lead [id=" + id + ", principal=" + principal + ", issueDate="
				+ issueDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", token=" + token + "]";
	}
	
}
