package org.helianto.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Simple signup registration.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="core_signup",
	uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "principal"})}
)
public class Signup 
	implements Serializable
{

	private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
	private Integer contextId = 1;
	
	private Integer identityId = 0;
	
    @Column(length=36)
    private String token;
    
	@NotEmpty
	@Column(length=64)
	private String principal;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate = new Date();
	
	@Column(length=128)
	private String firstName = "";
	
	@Column(length=128)
	private String lastName = "";
	
	@Column(length=128)
	private String domain = "";
	
	@Column(length=128)
	private String entityAliasSource = "";
	
	@AssertTrue
	private Boolean licenseAccepted;
	
	private Integer cityId;
	
	/**
	 * Constructor.
	 */
	public Signup() {
		super();
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param contextId
	 * @param principal
	 */
	public Signup(Integer contextId, String principal) {
		this();
		setContextId(contextId);
		setPrincipal(principal);
	}
	
	/**
	 * Name constructor.
	 * 
	 * @param contextId
	 * @param principal
	 * @param firstName
	 * @param lastName
	 */
	public Signup(Integer contextId, String principal, String firstName, String lastName) {
		this(contextId, principal);
		setFirstName(firstName);
		setLastName(lastName);
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
	
	public Integer getContextId() {
		return contextId;
	}
	public void setContextId(Integer contextId) {
		this.contextId = contextId;
	}
	
	public Integer getIdentityId() {
		return identityId;
	}
	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getEntityAliasSource() {
		return entityAliasSource;
	}
	public void setEntityAliasSource(String entityAliasSource) {
		this.entityAliasSource = entityAliasSource;
	}

	public Boolean getLicenseAccepted() {
		return licenseAccepted;
	}
	public void setLicenseAccepted(Boolean licenseAccepted) {
		this.licenseAccepted = licenseAccepted;
	}

	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	/**
	 * Create identity from form.
	 */
	public Identity createIdentityFromForm() {
		Identity identity= new Identity(principal);
		identity.setDisplayName(firstName);
		identity.getPersonalData().setFirstName(firstName);
		identity.getPersonalData().setLastName(lastName);
		identity.setOptionalSourceAlias(domain);
		return identity;
	}
	
	/**
	 * Create map from form.
	 */
	public Map<String, Object> createMapFromForm() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("principal", principal);
		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("domain", domain);
		return map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contextId == null) ? 0 : contextId.hashCode());
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
		Signup other = (Signup) obj;
		if (contextId == null) {
			if (other.contextId != null)
				return false;
		} else if (!contextId.equals(other.contextId))
			return false;
		if (principal == null) {
			if (other.principal != null)
				return false;
		} else if (!principal.equals(other.principal))
			return false;
		return true;
	}

}
