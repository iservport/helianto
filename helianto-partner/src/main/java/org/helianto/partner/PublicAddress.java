package org.helianto.partner;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.RootEntity;

/**
 * Address databases in a common environment searchable by postal code.  
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_publicaddress",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "postalCode"})}
)
public class PublicAddress extends AbstractPartialAddress implements RootEntity {

	private static final long serialVersionUID = 1L;
	private Operator operator;
	
	/**
	 * Empty constructor.
	 */
	public PublicAddress() {
		setAddress1("");
		setAddress2("");
		setPostalCode("");
	}

	/**
	 * Key constructor.
	 * 
	 * @param operator
	 * @param postalCode
	 */
	public PublicAddress(Operator operator, String postalCode) {
		this();
		setOperator(operator);
		setPostalCode(postalCode);
	}
	
	/**
	 * Province constructor.
	 * 
	 * @param operator
	 * @param postalCode
	 */
	public PublicAddress(Province province, String postalCode) {
		this(province.getOperator(), postalCode);
		setProvince(province);
	}
	
	/**
	 * Operator that holds the address database.
	 */
	@ManyToOne
	@JoinColumn(name="operatorId")
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("postalCode").append("='").append(getPostalCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof PublicAddress) ) return false;
         PublicAddress castOther = (PublicAddress) other; 
         
         return ((this.getOperator()==castOther.getOperator()) 
        		 || ( this.getOperator()!=null && castOther.getOperator()!=null 
        				 && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getPostalCode()==castOther.getPostalCode()) 
            		 || ( this.getPostalCode()!=null && castOther.getPostalCode()!=null 
            				 && this.getPostalCode().equals(castOther.getPostalCode()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getPostalCode() == null ? 0 : this.getPostalCode().hashCode() );
         return result;
   }   


}
