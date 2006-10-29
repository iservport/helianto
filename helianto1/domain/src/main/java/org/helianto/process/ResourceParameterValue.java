package org.helianto.process;
// Generated 29/10/2006 20:02:34 by Hibernate Tools 3.1.0.beta5


import java.math.BigDecimal;

/**
 * 			
 * <p>
 * A class to represent parameters values.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class ResourceParameterValue  implements java.io.Serializable {

    // Fields    

     private int id;
     private ResourceGroup resource;
     private ResourceParameter parameter;
     private BigDecimal parameterNumericValue;
     private String parameterTextValue;
     private boolean suppressed;

     // Constructors

    /** default constructor */
    public ResourceParameterValue() {
    }

	/** minimal constructor */
    public ResourceParameterValue(ResourceGroup resource, ResourceParameter parameter, boolean suppressed) {
        this.resource = resource;
        this.parameter = parameter;
        this.suppressed = suppressed;
    }
    /** full constructor */
    public ResourceParameterValue(ResourceGroup resource, ResourceParameter parameter, BigDecimal parameterNumericValue, String parameterTextValue, boolean suppressed) {
       this.resource = resource;
       this.parameter = parameter;
       this.parameterNumericValue = parameterNumericValue;
       this.parameterTextValue = parameterTextValue;
       this.suppressed = suppressed;
    }
    
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public ResourceGroup getResource() {
        return this.resource;
    }
    
    public void setResource(ResourceGroup resource) {
        this.resource = resource;
    }
    public ResourceParameter getParameter() {
        return this.parameter;
    }
    
    public void setParameter(ResourceParameter parameter) {
        this.parameter = parameter;
    }
    public BigDecimal getParameterNumericValue() {
        return this.parameterNumericValue;
    }
    
    public void setParameterNumericValue(BigDecimal parameterNumericValue) {
        this.parameterNumericValue = parameterNumericValue;
    }
    public String getParameterTextValue() {
        return this.parameterTextValue;
    }
    
    public void setParameterTextValue(String parameterTextValue) {
        this.parameterTextValue = parameterTextValue;
    }
    public boolean isSuppressed() {
        return this.suppressed;
    }
    
    public void setSuppressed(boolean suppressed) {
        this.suppressed = suppressed;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("resource").append("='").append(getResource()).append("' ");			
      buffer.append("parameter").append("='").append(getParameter()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResourceParameterValue) ) return false;
		 ResourceParameterValue castOther = ( ResourceParameterValue ) other; 
         
		 return ( (this.getResource()==castOther.getResource()) || ( this.getResource()!=null && castOther.getResource()!=null && this.getResource().equals(castOther.getResource()) ) )
 && ( (this.getParameter()==castOther.getParameter()) || ( this.getParameter()!=null && castOther.getParameter()!=null && this.getParameter().equals(castOther.getParameter()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getResource() == null ? 0 : this.getResource().hashCode() );
         result = 37 * result + ( getParameter() == null ? 0 : this.getParameter().hashCode() );
         
         
         
         return result;
   }   


}


