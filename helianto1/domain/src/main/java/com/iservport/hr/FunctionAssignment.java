package com.iservport.hr;
// Generated 16/02/2007 15:31:38 by Hibernate Tools 3.2.0.beta8


import org.helianto.core.Identity;

/**
 * 				
 * <p>
 * Represents assignments between functions and identities.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class FunctionAssignment  implements java.io.Serializable {

    // Fields    

     private int id;
     private Function function;
     private Identity identity;

     // Constructors

    /** default constructor */
    public FunctionAssignment() {
    }

    /** full constructor */
    public FunctionAssignment(Function function, Identity identity) {
       this.function = function;
       this.identity = identity;
    }
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Function getFunction() {
        return this.function;
    }
    
    public void setFunction(Function function) {
        this.function = function;
    }
    public Identity getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("function").append("='").append(getFunction()).append("' ");			
      buffer.append("identity").append("='").append(getIdentity()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FunctionAssignment) ) return false;
		 FunctionAssignment castOther = ( FunctionAssignment ) other; 
         
		 return ( (this.getFunction()==castOther.getFunction()) || ( this.getFunction()!=null && castOther.getFunction()!=null && this.getFunction().equals(castOther.getFunction()) ) )
 && ( (this.getIdentity()==castOther.getIdentity()) || ( this.getIdentity()!=null && castOther.getIdentity()!=null && this.getIdentity().equals(castOther.getIdentity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getFunction() == null ? 0 : this.getFunction().hashCode() );
         result = 37 * result + ( getIdentity() == null ? 0 : this.getIdentity().hashCode() );
         return result;
   }   


}


