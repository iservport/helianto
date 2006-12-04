package org.helianto.core;
// Generated 04/12/2006 19:22:32 by Hibernate Tools 3.2.0.beta8


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
     private String operatorHostAddress;
     private String operatorSourceMailAddress;
     private String defaultEncoding;
     private String preferredDateFormat;
     private String preferredTimeFormat;
     private String rfc822TimeZone;

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
    public Operator(String operatorName, Operator parent, Locale locale, char operationMode, String operatorHostAddress, String operatorSourceMailAddress, String defaultEncoding, String preferredDateFormat, String preferredTimeFormat, String rfc822TimeZone) {
       this.operatorName = operatorName;
       this.parent = parent;
       this.locale = locale;
       this.operationMode = operationMode;
       this.operatorHostAddress = operatorHostAddress;
       this.operatorSourceMailAddress = operatorSourceMailAddress;
       this.defaultEncoding = defaultEncoding;
       this.preferredDateFormat = preferredDateFormat;
       this.preferredTimeFormat = preferredTimeFormat;
       this.rfc822TimeZone = rfc822TimeZone;
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
    public String getOperatorHostAddress() {
        return this.operatorHostAddress;
    }
    
    public void setOperatorHostAddress(String operatorHostAddress) {
        this.operatorHostAddress = operatorHostAddress;
    }
    public String getOperatorSourceMailAddress() {
        return this.operatorSourceMailAddress;
    }
    
    public void setOperatorSourceMailAddress(String operatorSourceMailAddress) {
        this.operatorSourceMailAddress = operatorSourceMailAddress;
    }
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }
    
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }
    public String getPreferredDateFormat() {
        return this.preferredDateFormat;
    }
    
    public void setPreferredDateFormat(String preferredDateFormat) {
        this.preferredDateFormat = preferredDateFormat;
    }
    public String getPreferredTimeFormat() {
        return this.preferredTimeFormat;
    }
    
    public void setPreferredTimeFormat(String preferredTimeFormat) {
        this.preferredTimeFormat = preferredTimeFormat;
    }
    public String getRfc822TimeZone() {
        return this.rfc822TimeZone;
    }
    
    public void setRfc822TimeZone(String rfc822TimeZone) {
        this.rfc822TimeZone = rfc822TimeZone;
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


