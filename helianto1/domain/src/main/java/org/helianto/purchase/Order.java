package org.helianto.purchase;
// Generated 15/08/2006 12:04:34 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import org.helianto.core.Entity;
import org.helianto.core.Partner;


/**
 * 				
 * <p>
 * A domain object to represent a purchase order.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Order  implements java.io.Serializable {


    // Fields    

     private int id;
     private Entity entity;
     private int internalNumber;
     private Partner partner;
     private Date dateIssued;
     private BigDecimal orderValue;


    // Constructors

    /** default constructor */
    public Order() {
    }

    
    /** full constructor */
    public Order(Entity entity, int internalNumber, Partner partner, Date dateIssued, BigDecimal orderValue) {
        this.entity = entity;
        this.internalNumber = internalNumber;
        this.partner = partner;
        this.dateIssued = dateIssued;
        this.orderValue = orderValue;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public int getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    public Partner getPartner() {
        return this.partner;
    }
    
    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Date getDateIssued() {
        return this.dateIssued;
    }
    
    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public BigDecimal getOrderValue() {
        return this.orderValue;
    }
    
    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Order) ) return false;
		 Order castOther = ( Order ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && (this.getInternalNumber()==castOther.getInternalNumber());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + this.getInternalNumber();
         
         
         
         return result;
   }   





}
