package org.helianto.core.domain;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.type.RootEntity;
import org.helianto.core.internal.AbstractAddress;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Address databases in a common environment searchable by postal code.  
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_publicaddress",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "postalCode"})}
)
public class PublicAddress 
	extends AbstractAddress 
	implements RootEntity {

	private static final long serialVersionUID = 1L;
	private Operator operator;
	
	/**
	 * Empty constructor.
	 */
	public PublicAddress() {
		super();
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
	 * State constructor.
	 * 
	 * @param state
	 * @param postalCode
	 */
	public PublicAddress(State state, String postalCode) {
		this(state.getContext(), postalCode);
		setState(state);
	}
	
	/**
	 * Operator that holds the address database.
	 */
	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="operatorId")
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
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
