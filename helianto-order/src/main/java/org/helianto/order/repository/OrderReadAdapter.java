package org.helianto.order.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Order adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class OrderReadAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	protected Integer id;
	
	protected Long internalNumber; 
	
	protected String docCode; 
	
	protected String docName; 
	
	protected Date issueDate;
	
	protected Integer ownerId;
	
	protected String ownerDisplayName = ""; 
	
	protected String ownerImageUrl = ""; 
	
	protected Date checkOutTime;
	
	protected Integer categoryId;
	
	protected String categoryCode = "";
    
	protected String categoryName = "";
	
	protected String remarks = "";
	
	protected Integer currencyId;
	
	protected BigDecimal faceValue;
	
	/**
	 * Constructor.
	 */
	public OrderReadAdapter() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param internalNumber
	 * @param docCode
	 * @param docName
	 * @param issueDate
	 * @param ownerId
	 * @param ownerDisplayName
	 * @param ownerImageUrl
	 * @param checkOutTime
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param remarks
	 * @param currencyId
	 */
	public OrderReadAdapter(int id
			, Long internalNumber
			, String docCode
			, String docName
			, Date issueDate
			, Integer ownerId
			, String ownerDisplayName
			, String ownerImageUrl
			, Date checkOutTime
			, Integer categoryId
			, String categoryCode
			, String categoryName
		    , String remarks 
			, Integer currencyId
			, BigDecimal faceValue
		    ) {
		this();
		this.id = id;
		this.internalNumber = internalNumber;
		this.docCode = docCode;
		this.docName = docName;
		this.issueDate = issueDate;
		this.ownerId = ownerId;
		this.ownerDisplayName = ownerDisplayName;
		this.ownerImageUrl = ownerImageUrl;
		this.checkOutTime = checkOutTime; 
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.remarks = remarks;
		this.currencyId = currencyId;
		this.faceValue = faceValue;
	}

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Long getInternalNumber() {
		return internalNumber;
	}

	public String getDocCode() {
		return docCode;
	}

	public String getDocName() {
		return docName;
	}

	public Date getIssueDate() {
		return issueDate;
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}
	
	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}
	
	public String getOwnerImageUrl() {
		return ownerImageUrl;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public Integer getCurrencyId() {
		return currencyId;
	}
	
	public BigDecimal getFaceValue() {
		return faceValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		OrderReadAdapter other = (OrderReadAdapter) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderAdapter [id=" + id 
				+ ", internalNumber=" + internalNumber
				+ ", docCode=" + docCode
				+ ", docName=" + docName
				+ ", issueDate=" + issueDate
				+ ", ownerId=" + ownerId
				+ ", ownerDisplayName=" + ownerDisplayName
				+ ", ownerImageUrl=" + ownerImageUrl
				+ ", checkOutTime=" + checkOutTime
				+ ", categoryId=" + categoryId
				+ ", categoryCode=" + categoryCode
				+ ", categoryName=" + categoryName
				+ ", remarks=" + remarks
				+ ", currencyId=" + currencyId
				+ ", faceValue=" + faceValue
				+ "]";
	}

}
