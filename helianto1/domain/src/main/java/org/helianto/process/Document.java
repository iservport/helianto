package org.helianto.process;
// Generated 24/09/2006 12:54:24 by Hibernate Tools 3.1.0.beta4

import java.util.ArrayList;
import java.util.List;
import org.helianto.core.Entity;


/**
 * 			
 * <p>
 * A base class to any class that requires version control.
 * </p>
 * <p>
 * Tipical descendants are parts, processes and organizational process
 * documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Document  implements java.io.Serializable {


    // Fields    

     private long id;
     private Entity entity;
     private String docCode;
     private String docName;
     private Document parent;
     private List<Tree> children = new ArrayList<Tree>(0);


    // Constructors

    /** default constructor */
    public Document() {
    }

	/** minimal constructor */
    public Document(Entity entity, String docCode) {
        this.entity = entity;
        this.docCode = docCode;
    }
    
    /** full constructor */
    public Document(Entity entity, String docCode, String docName, Document parent, List<Tree> children) {
        this.entity = entity;
        this.docCode = docCode;
        this.docName = docName;
        this.parent = parent;
        this.children = children;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getDocCode() {
        return this.docCode;
    }
    
    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return this.docName;
    }
    
    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Document getParent() {
        return this.parent;
    }
    
    public void setParent(Document parent) {
        this.parent = parent;
    }

    public List<Tree> getChildren() {
        return this.children;
    }
    
    public void setChildren(List<Tree> children) {
        this.children = children;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("docCode").append("='").append(getDocCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Document) ) return false;
		 Document castOther = ( Document ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getDocCode()==castOther.getDocCode()) || ( this.getDocCode()!=null && castOther.getDocCode()!=null && this.getDocCode().equals(castOther.getDocCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getDocCode() == null ? 0 : this.getDocCode().hashCode() );
         
         
         
         return result;
   }   





}