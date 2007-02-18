package com.iservport.hr;
// Generated 16/02/2007 15:31:38 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * Represents requirement from function to competences.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class CompetenceRequirement  implements java.io.Serializable {

    // Fields    

     private int id;
     private Function function;
     private Competence competence;
     private int minimumTimeRequirement;
     private boolean lenient;
     private char timeUnit;

     // Constructors

    /** default constructor */
    public CompetenceRequirement() {
    }

	/** minimal constructor */
    public CompetenceRequirement(Function function, Competence competence) {
        this.function = function;
        this.competence = competence;
    }
    /** full constructor */
    public CompetenceRequirement(Function function, Competence competence, int minimumTimeRequirement, boolean lenient, char timeUnit) {
       this.function = function;
       this.competence = competence;
       this.minimumTimeRequirement = minimumTimeRequirement;
       this.lenient = lenient;
       this.timeUnit = timeUnit;
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
    public Competence getCompetence() {
        return this.competence;
    }
    
    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
    public int getMinimumTimeRequirement() {
        return this.minimumTimeRequirement;
    }
    
    public void setMinimumTimeRequirement(int minimumTimeRequirement) {
        this.minimumTimeRequirement = minimumTimeRequirement;
    }
    public boolean isLenient() {
        return this.lenient;
    }
    
    public void setLenient(boolean lenient) {
        this.lenient = lenient;
    }
    public char getTimeUnit() {
        return this.timeUnit;
    }
    
    public void setTimeUnit(char timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("function").append("='").append(getFunction()).append("' ");			
      buffer.append("competence").append("='").append(getCompetence()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CompetenceRequirement) ) return false;
		 CompetenceRequirement castOther = ( CompetenceRequirement ) other; 
         
		 return ( (this.getFunction()==castOther.getFunction()) || ( this.getFunction()!=null && castOther.getFunction()!=null && this.getFunction().equals(castOther.getFunction()) ) )
 && ( (this.getCompetence()==castOther.getCompetence()) || ( this.getCompetence()!=null && castOther.getCompetence()!=null && this.getCompetence().equals(castOther.getCompetence()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getFunction() == null ? 0 : this.getFunction().hashCode() );
         result = 37 * result + ( getCompetence() == null ? 0 : this.getCompetence().hashCode() );
         
         
         
         return result;
   }   


}


