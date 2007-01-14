package org.helianto.process;
// Generated 14/01/2007 10:50:26 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * A class to define relationships between different document types.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Tree  implements java.io.Serializable {

    // Fields    

     private long id;
     private Document parent;
     private Document child;
     private int sequence;
     private double coefficient;
     private int associationType;
     private Method method;

     // Constructors

    /** default constructor */
    public Tree() {
    }

	/** minimal constructor */
    public Tree(Document parent, Document child, int sequence, double coefficient, int associationType) {
        this.parent = parent;
        this.child = child;
        this.sequence = sequence;
        this.coefficient = coefficient;
        this.associationType = associationType;
    }
    /** full constructor */
    public Tree(Document parent, Document child, int sequence, double coefficient, int associationType, Method method) {
       this.parent = parent;
       this.child = child;
       this.sequence = sequence;
       this.coefficient = coefficient;
       this.associationType = associationType;
       this.method = method;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Document getParent() {
        return this.parent;
    }
    
    public void setParent(Document parent) {
        this.parent = parent;
    }
    public Document getChild() {
        return this.child;
    }
    
    public void setChild(Document child) {
        this.child = child;
    }
    public int getSequence() {
        return this.sequence;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    public double getCoefficient() {
        return this.coefficient;
    }
    
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
    public int getAssociationType() {
        return this.associationType;
    }
    
    public void setAssociationType(int associationType) {
        this.associationType = associationType;
    }
    public Method getMethod() {
        return this.method;
    }
    
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("parent").append("='").append(getParent()).append("' ");			
      buffer.append("child").append("='").append(getChild()).append("' ");			
      buffer.append("sequence").append("='").append(getSequence()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Tree) ) return false;
		 Tree castOther = ( Tree ) other; 
         
		 return ( (this.getParent()==castOther.getParent()) || ( this.getParent()!=null && castOther.getParent()!=null && this.getParent().equals(castOther.getParent()) ) )
 && ( (this.getChild()==castOther.getChild()) || ( this.getChild()!=null && castOther.getChild()!=null && this.getChild().equals(castOther.getChild()) ) )
 && (this.getSequence()==castOther.getSequence());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getParent() == null ? 0 : this.getParent().hashCode() );
         result = 37 * result + ( getChild() == null ? 0 : this.getChild().hashCode() );
         result = 37 * result + this.getSequence();
         
         
         
         return result;
   }   


}


