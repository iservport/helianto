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

/**
 * An individual who has provided contact information and, in doing so, pointed toward a 
 * potential sales opportunity.
 * 
 * @author Eldevan Nery Junior
 */
@javax.persistence.Entity
@Table(name="core_lead")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result
				+ ((principal == null) ? 0 : principal.hashCode());
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
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (principal == null) {
			if (other.principal != null)
				return false;
		} else if (!principal.equals(other.principal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdentityTemp [id=" + id + ", principal=" + principal
				+ ", issueDate=" + issueDate + "]";
	}
	
}
