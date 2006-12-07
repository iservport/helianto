package org.helianto.core;
// Generated 07/12/2006 07:44:31 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Provinces.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Province  implements java.io.Serializable {

    // Fields    

     private int id;
     private Operator operator;
     private String code;
     private String provinceName;

     // Constructors

    /** default constructor */
    public Province() {
    }

	/** minimal constructor */
    public Province(Operator operator, String code) {
        this.operator = operator;
        this.code = code;
    }
    /** full constructor */
    public Province(Operator operator, String code, String provinceName) {
       this.operator = operator;
       this.code = code;
       this.provinceName = provinceName;
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
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("operator").append("='").append(getOperator()).append("' ");			
      buffer.append("code").append("='").append(getCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Province) ) return false;
		 Province castOther = ( Province ) other; 
         
		 return ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
 && ( (this.getCode()==castOther.getCode()) || ( this.getCode()!=null && castOther.getCode()!=null && this.getCode().equals(castOther.getCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getCode() == null ? 0 : this.getCode().hashCode() );
         
         return result;
   }   


}


