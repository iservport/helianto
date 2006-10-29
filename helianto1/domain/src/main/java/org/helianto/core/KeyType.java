package org.helianto.core;
// Generated 29/10/2006 20:02:34 by Hibernate Tools 3.1.0.beta5



/**
 * 			
 * <p>
 * Represent key types like customer, supplier or government assigned numbers.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class KeyType  implements java.io.Serializable {

    // Fields    

     private int id;
     private Operator operator;
     private String keyCode;
     private String purpose;

     // Constructors

    /** default constructor */
    public KeyType() {
    }

	/** minimal constructor */
    public KeyType(Operator operator, String keyCode) {
        this.operator = operator;
        this.keyCode = keyCode;
    }
    /** full constructor */
    public KeyType(Operator operator, String keyCode, String purpose) {
       this.operator = operator;
       this.keyCode = keyCode;
       this.purpose = purpose;
    }
    
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Operator getOperator() {
        return this.operator;
    }
    
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    public String getKeyCode() {
        return this.keyCode;
    }
    
    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
    public String getPurpose() {
        return this.purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("operator").append("='").append(getOperator()).append("' ");			
      buffer.append("keyCode").append("='").append(getKeyCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof KeyType) ) return false;
		 KeyType castOther = ( KeyType ) other; 
         
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
 && ( (this.getKeyCode()==castOther.getKeyCode()) || ( this.getKeyCode()!=null && castOther.getKeyCode()!=null && this.getKeyCode().equals(castOther.getKeyCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getKeyCode() == null ? 0 : this.getKeyCode().hashCode() );
         
         return result;
   }   


}


