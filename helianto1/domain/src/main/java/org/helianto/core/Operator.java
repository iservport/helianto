package org.helianto.core;
// Generated 24/09/2006 12:54:25 by Hibernate Tools 3.1.0.beta4

import java.util.Locale;


/**
 * 				
 * <p>
 * A domain object to represent a system operator, unique by a short name.
 * </p>
 * <p>
 * Operators can be created to represent a geographical
 * limit, bound to a java <code>Locale</code>, or according to any territory
 * arrangement.
 * </p>
 * <p>
 * At least one default (root) operatot must be defined per installation.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Operator  implements java.io.Serializable {


    // Fields    

     private int id;
     private String operatorName;
     private Operator parent;
     private Locale locale;
     private char operationMode;


    // Constructors

    /** default constructor */
    public Operator() {
    }

	/** minimal constructor */
    public Operator(String operatorName, char operationMode) {
        this.operatorName = operatorName;
        this.operationMode = operationMode;
    }
    
    /** full constructor */
    public Operator(String operatorName, Operator parent, Locale locale, char operationMode) {
        this.operatorName = operatorName;
        this.parent = parent;
        this.locale = locale;
        this.operationMode = operationMode;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getOperatorName() {
        return this.operatorName;
    }
    
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Operator getParent() {
        return this.parent;
    }
    
    public void setParent(Operator parent) {
        this.parent = parent;
    }

    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public char getOperationMode() {
        return this.operationMode;
    }
    
    public void setOperationMode(char operationMode) {
        this.operationMode = operationMode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("operatorName").append("='").append(getOperatorName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Operator) ) return false;
		 Operator castOther = ( Operator ) other; 
         
		 return ( (this.getOperatorName()==castOther.getOperatorName()) || ( this.getOperatorName()!=null && castOther.getOperatorName()!=null && this.getOperatorName().equals(castOther.getOperatorName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOperatorName() == null ? 0 : this.getOperatorName().hashCode() );
         
         
         
         return result;
   }   





}