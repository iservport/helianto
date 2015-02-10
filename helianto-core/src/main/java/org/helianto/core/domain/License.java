package org.helianto.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.def.HumanReadable;

/**
 * License agreement content.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="lic_license",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "licenseCode"})}
)
public class License 
	implements HumanReadable
{

	private static final long serialVersionUID = 1L;
	
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
	@ManyToOne
	@JoinColumn(name="operatorId")
	private Operator operator;
	
	@Column(length=12)
	private String licenseCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	private Identity owner;
	
	@Lob
	private byte[] content;
	
	/**
	 * Constructor.
	 */
	public License() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param operator
	 * @param licenseCode
	 */
	public License(Operator operator, String licenseCode) {
		this();
		setOperator(operator);
		setLicenseCode(licenseCode);
		
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Context.
	 */
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * License code.
	 */
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
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
	 * Owner identity.
	 */
	public Identity getOwner() {
		return owner;
	}
	public void setOwner(Identity owner) {
		this.owner = owner;
	}

	/**
	 * Content.
	 */
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	/**
	 * Content as string.
	 */
	public String getContentAsString() {
		if (getContent()!=null) {
			return new String(getContent());
		}
		return "";
	}
	public void setContentAsString(String content) {
		if (getContent()!=null) {
			setContent(content.getBytes());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((licenseCode == null) ? 0 : licenseCode.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
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
		if (!(obj instanceof License)) {
			return false;
		}
		License other = (License) obj;
		if (licenseCode == null) {
			if (other.licenseCode != null) {
				return false;
			}
		} else if (!licenseCode.equals(other.licenseCode)) {
			return false;
		}
		if (operator == null) {
			if (other.operator != null) {
				return false;
			}
		} else if (!operator.equals(other.operator)) {
			return false;
		}
		return true;
	}
	
	/*
	 * Para satisfazer a interface HumanReadable com HTML/iso-8859-1 
	 */

	@Override
	public String getEncoding() {
		return "iso-8859-1";
	}

	@Override
	public String getMultipartFileContentType() {
		return "text/html";
	}

	@Override
	public boolean isHtml() {
		return true;
	}

	@Override
	public boolean isText() {
		return true;
	}

}
