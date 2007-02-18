package com.iservport.hr;
// Generated 16/02/2007 15:31:38 by Hibernate Tools 3.2.0.beta8


import org.helianto.core.Entity;

/**
 * 			
 * <p>
 * Represents Education, Experience, Knowledge, Course or Skill.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Competence  implements java.io.Serializable {

    // Fields    

     private int id;
     private Integer version;
     private Entity entity;
     private String competenceCode;
     private char competenceType;
     private String competenceName;
     private char assessmentType;

     // Constructors

    /** default constructor */
    public Competence() {
    }

	/** minimal constructor */
    public Competence(Entity entity, String competenceCode) {
        this.entity = entity;
        this.competenceCode = competenceCode;
    }
    /** full constructor */
    public Competence(Entity entity, String competenceCode, char competenceType, String competenceName, char assessmentType) {
       this.entity = entity;
       this.competenceCode = competenceCode;
       this.competenceType = competenceType;
       this.competenceName = competenceName;
       this.assessmentType = assessmentType;
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
    public String getCompetenceCode() {
        return this.competenceCode;
    }
    
    public void setCompetenceCode(String competenceCode) {
        this.competenceCode = competenceCode;
    }
    public char getCompetenceType() {
        return this.competenceType;
    }
    
    public void setCompetenceType(char competenceType) {
        this.competenceType = competenceType;
    }
    public String getCompetenceName() {
        return this.competenceName;
    }
    
    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }
    public char getAssessmentType() {
        return this.assessmentType;
    }
    
    public void setAssessmentType(char assessmentType) {
        this.assessmentType = assessmentType;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("competenceCode").append("='").append(getCompetenceCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Competence) ) return false;
		 Competence castOther = ( Competence ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getCompetenceCode()==castOther.getCompetenceCode()) || ( this.getCompetenceCode()!=null && castOther.getCompetenceCode()!=null && this.getCompetenceCode().equals(castOther.getCompetenceCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getCompetenceCode() == null ? 0 : this.getCompetenceCode().hashCode() );
         
         
         
         return result;
   }   


}


