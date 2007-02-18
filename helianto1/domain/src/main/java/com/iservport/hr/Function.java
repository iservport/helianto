package com.iservport.hr;
// Generated 16/02/2007 15:31:38 by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.helianto.core.Entity;

/**
 * 				
 * <p>
 * Represents a process function.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Function  implements java.io.Serializable {

    // Fields    

     private int id;
     private Integer version;
     private Entity entity;
     private String functionCode;
     private String functionName;
     private String functionDesc;
     private Set<FunctionAssignment> assignees = new HashSet<FunctionAssignment>(0);
     private Set<Competence> requirements = new HashSet<Competence>(0);

     // Constructors

    /** default constructor */
    public Function() {
    }

	/** minimal constructor */
    public Function(Entity entity, String functionCode) {
        this.entity = entity;
        this.functionCode = functionCode;
    }
    /** full constructor */
    public Function(Entity entity, String functionCode, String functionName, String functionDesc, Set<FunctionAssignment> assignees, Set<Competence> requirements) {
       this.entity = entity;
       this.functionCode = functionCode;
       this.functionName = functionName;
       this.functionDesc = functionDesc;
       this.assignees = assignees;
       this.requirements = requirements;
    }
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public String getFunctionCode() {
        return this.functionCode;
    }
    
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }
    public String getFunctionName() {
        return this.functionName;
    }
    
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    public String getFunctionDesc() {
        return this.functionDesc;
    }
    
    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }
    public Set<FunctionAssignment> getAssignees() {
        return this.assignees;
    }
    
    public void setAssignees(Set<FunctionAssignment> assignees) {
        this.assignees = assignees;
    }
    public Set<Competence> getRequirements() {
        return this.requirements;
    }
    
    public void setRequirements(Set<Competence> requirements) {
        this.requirements = requirements;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("functionCode").append("='").append(getFunctionCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Function) ) return false;
		 Function castOther = ( Function ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getFunctionCode()==castOther.getFunctionCode()) || ( this.getFunctionCode()!=null && castOther.getFunctionCode()!=null && this.getFunctionCode().equals(castOther.getFunctionCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getFunctionCode() == null ? 0 : this.getFunctionCode().hashCode() );
         
         
         
         
         return result;
   }   


}


