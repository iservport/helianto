package org.helianto.order.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.helianto.order.domain.AbstractOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Order adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class OrderReadAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private AbstractOrder adaptee;

	protected Integer id;
	
	protected Long internalNumber; 
	
	protected Integer partId; 
	
	protected String docCode; 
	
	protected String docName; 
	
	protected Date issueDate;
	
	protected Integer ownerId;
	
	protected String ownerDisplayName = ""; 
	
	protected String ownerImageUrl = ""; 
	
	protected Character resolution;
	
	protected Date nextCheckDate;
	
	protected Date checkOutTime;
	
	protected Integer categoryId;
	
	protected String categoryCode = "";
    
	protected String categoryName = "";
	
	protected String checkInData = "";
	
	protected String checkOutData = "";
	
	protected String remarks = "";
	
	protected Integer currencyId;
	
	protected BigDecimal faceValue;
	
	protected Character position;
	
	/**
	 * Constructor.
	 */
	public OrderReadAdapter() {
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param adaptee
	 */
	public OrderReadAdapter(AbstractOrder adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param internalNumber
	 * @param partId
	 * @param docCode
	 * @param docName
	 * @param issueDate
	 * @param ownerId
	 * @param ownerDisplayName
	 * @param ownerImageUrl
	 * @param resolution
	 * @param nextCheckDate
	 * @param checkOutTime
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param checkInData
	 * @param checkOutData
	 * @param remarks
	 * @param currencyId
	 * @param faceValue
	 * @deprecated
	 */
	public OrderReadAdapter(int id
			, Long internalNumber
			, Integer partId
			, String docCode
			, String docName
			, Date issueDate
			, Integer ownerId
			, String ownerDisplayName
			, String ownerImageUrl
			, Character resolution
			, Date nextCheckDate
			, Date checkOutTime
			, Integer categoryId
			, String categoryCode
			, String categoryName
			, String checkInData
			, String checkOutData
		    , String remarks 
			, Integer currencyId
			, BigDecimal faceValue
		    ) {
		this();
		this.id = id;
		this.internalNumber = internalNumber;
		this.partId = partId;
		this.docCode = docCode;
		this.docName = docName;
		this.issueDate = issueDate;
		this.ownerId = ownerId;
		this.ownerDisplayName = ownerDisplayName;
		this.ownerImageUrl = ownerImageUrl;
		this.resolution = resolution!=null ? resolution : 'P';
		this.nextCheckDate = nextCheckDate; 
		this.checkOutTime = checkOutTime; 
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.checkInData = checkInData;
		this.checkOutData = checkOutData;
		this.remarks = remarks;
		this.currencyId = currencyId;
		this.faceValue = faceValue;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param internalNumber
	 * @param partId
	 * @param docCode
	 * @param docName
	 * @param issueDate
	 * @param ownerId
	 * @param ownerDisplayName
	 * @param ownerImageUrl
	 * @param resolution
	 * @param nextCheckDate
	 * @param checkOutTime
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param checkInData
	 * @param checkOutData
	 * @param remarks
	 * @param currencyId
	 * @param faceValue
	 * @param position
	 */
	public OrderReadAdapter(int id
			, Long internalNumber
			, Integer partId
			, String docCode
			, String docName
			, Date issueDate
			, Integer ownerId
			, String ownerDisplayName
			, String ownerImageUrl
			, Character resolution
			, Date nextCheckDate
			, Date checkOutTime
			, Integer categoryId
			, String categoryCode
			, String categoryName
			, String checkInData
			, String checkOutData
		    , String remarks 
			, Integer currencyId
			, BigDecimal faceValue
			, Character position
		    ) {
		this();
		this.id = id;
		this.internalNumber = internalNumber;
		this.partId = partId;
		this.docCode = docCode;
		this.docName = docName;
		this.issueDate = issueDate;
		this.ownerId = ownerId;
		this.ownerDisplayName = ownerDisplayName;
		this.ownerImageUrl = ownerImageUrl;
		this.resolution = resolution!=null ? resolution : 'P';
		this.nextCheckDate = nextCheckDate; 
		this.checkOutTime = checkOutTime; 
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.checkInData = checkInData;
		this.checkOutData = checkOutData;
		this.remarks = remarks;
		this.currencyId = currencyId;
		this.faceValue = faceValue;
		this.position = position;
	}

	@JsonIgnore
	public AbstractOrder getAdaptee() {
		return adaptee;
	}
	public OrderReadAdapter setAdaptee(AbstractOrder adaptee) {
		this.adaptee = adaptee;
		return this;
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
	
	public Integer getPartId() {
		return partId;
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
	
	public Character getResolution() {
		return resolution;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public Date getNextCheckDate() {
		return nextCheckDate;
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
	
	public String getCheckInData() {
		return checkInData;
	}
	
	public String[] getCheckInDataAsArray() {
		if (getCheckInData()!=null) {
			return getCheckInData().split(" ");
		}
		return new String[0];
	}
	
	public String getCheckOutData() {
		return checkOutData;
	}
	
	public String[] getCheckOutDataAsArray() {
		if (getCheckOutData()!=null) {
			return getCheckOutData().split(" ");
		}
		return new String[0];
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
	
	public Character getPosition() {
		return position;
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
				+ ", resolution=" + resolution
				+ ", nextCheckDate=" + nextCheckDate
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
