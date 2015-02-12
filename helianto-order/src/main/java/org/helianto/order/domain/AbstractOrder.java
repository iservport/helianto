package org.helianto.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractEvent;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Classe base para ordens.
 * 
 * @author mauriciofernandesdecastro
 */

@javax.persistence.Entity
@Table(name="ord_abstract",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber", "type"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("P")

public class AbstractOrder 
	extends AbstractEvent
{
	
	private static final long serialVersionUID = 1L;
	
	private long internalNumber;
	
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="partId")
	private Part part;
	
	private BigDecimal faceValue = BigDecimal.ZERO;

	@DateTimeFormat(style="SS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOutTime;
	
	@Column(length=255)
	private String remarks;
	
	public String getDiscriminator() {
		return "P";
	}

	/**
	 * Default constructor.
	 */
	public AbstractOrder() { 
		super();
	}
	
	/**
	 * Key constructor.
	 */
	public AbstractOrder(Entity entity, long internalNumber) { 
		this();
		setEntity(entity);
		setInternalNumber(internalNumber);
	}
	
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	/**
	 * Hora de saída.
	 */
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getInternalNumberKey() {
		return "INTORD";
	}
	
    public int getStartNumber() {
    	return 1;
    }
    
    public Category getCategory() {
		return category;
	}
    public void setCategory(Category category) {
		this.category = category;
	}

	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	/**
	 * Conveniente para recuperar o código da peça.
	 */
	public String getPartCode() {
		if (getPart()!=null) {
			return getPart().getDocCode();
		}
		return "";
	}
	
	public BigDecimal getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    /**
     * Give subclasses a chance to validate next check date.
     */
    protected Date validateNextCheckDate(Date nextCheckDate) {
    	return nextCheckDate;
    }
    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof AbstractOrder) ) return false;
        AbstractOrder castOther = (AbstractOrder) other; 
        
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
         && (this.getInternalNumber()==castOther.getInternalNumber());
	}
    
    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }


}
