package org.helianto.order.repository;

import java.io.Serializable;
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

	private int id;
	
	private long internalNumber; 
	
	private String docCode; 
	
	private String docName; 
	
	private Date issueDate;
	
	private Date checkOutTime;
	
	private int categoryId;
	
    private String categoryCode = "";
    
    private String categoryName = "";
	
	public OrderReadAdapter() {
		super();
	}
	
	public OrderReadAdapter(int id, long internalNumber, String docCode, String docName, Date issueDate, Date checkOutTime, int categoryId, String categoryCode, String categoryName ) {
		super();
		this.id = id;
		this.internalNumber = internalNumber;
		this.docCode = docCode;
		this.docName = docName;
		this.issueDate = issueDate;
		this.checkOutTime = checkOutTime; 
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		return "OrderAdapter [id=" + id + ", internalNumber=" + internalNumber
				+ ", docCode=" + docCode + ", docName=" + docName
				+ ", issueDate=" + issueDate + ", checkOutTime=" + checkOutTime
				+ ", categoryId=" + categoryId + ", categoryCode="
				+ categoryCode + ", categoryName=" + categoryName + "]";
	}

	
	
	

}
