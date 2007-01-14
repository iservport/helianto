package org.helianto.process;
// Generated 14/01/2007 10:50:26 by Hibernate Tools 3.2.0.beta8


import org.helianto.core.Entity;

/**
 * 			
 * <p>
 * A class to represent resource parameters.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class ResourceParameter  implements java.io.Serializable {

    // Fields    

     private int id;
     private Entity entity;
     private String parameterCode;
     private ResourceParameter parent;
     private Unit unit;
     private String paramName;
     private String paramDesc;
     private boolean textOnly;

     // Constructors

    /** default constructor */
    public ResourceParameter() {
    }

	/** minimal constructor */
    public ResourceParameter(Entity entity, String parameterCode, boolean textOnly) {
        this.entity = entity;
        this.parameterCode = parameterCode;
        this.textOnly = textOnly;
    }
    /** full constructor */
    public ResourceParameter(Entity entity, String parameterCode, ResourceParameter parent, Unit unit, String paramName, String paramDesc, boolean textOnly) {
       this.entity = entity;
       this.parameterCode = parameterCode;
       this.parent = parent;
       this.unit = unit;
       this.paramName = paramName;
       this.paramDesc = paramDesc;
       this.textOnly = textOnly;
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
    public String getParameterCode() {
        return this.parameterCode;
    }
    
    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }
    public ResourceParameter getParent() {
        return this.parent;
    }
    
    public void setParent(ResourceParameter parent) {
        this.parent = parent;
    }
    public Unit getUnit() {
        return this.unit;
    }
    
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public String getParamName() {
        return this.paramName;
    }
    
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamDesc() {
        return this.paramDesc;
    }
    
    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
    public boolean isTextOnly() {
        return this.textOnly;
    }
    
    public void setTextOnly(boolean textOnly) {
        this.textOnly = textOnly;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("parameterCode").append("='").append(getParameterCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResourceParameter) ) return false;
		 ResourceParameter castOther = ( ResourceParameter ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getParameterCode()==castOther.getParameterCode()) || ( this.getParameterCode()!=null && castOther.getParameterCode()!=null && this.getParameterCode().equals(castOther.getParameterCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getParameterCode() == null ? 0 : this.getParameterCode().hashCode() );
         
         
         
         
         
         return result;
   }   


}


