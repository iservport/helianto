package org.helianto.process;
// Generated 24/10/2006 13:50:48 by Hibernate Tools 3.1.0.beta5



/**
 * 				
 * <p>
 * A base class to add attachments to documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class Attachment  implements java.io.Serializable {

    // Fields    

     private long id;
     private Document document;
     private int sequence;
     private byte[] content;
     private String attachDesc;

     // Constructors

    /** default constructor */
    public Attachment() {
    }

	/** minimal constructor */
    public Attachment(Document document, int sequence) {
        this.document = document;
        this.sequence = sequence;
    }
    /** full constructor */
    public Attachment(Document document, int sequence, byte[] content, String attachDesc) {
       this.document = document;
       this.sequence = sequence;
       this.content = content;
       this.attachDesc = attachDesc;
    }
    
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Document getDocument() {
        return this.document;
    }
    
    public void setDocument(Document document) {
        this.document = document;
    }
    public int getSequence() {
        return this.sequence;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    public byte[] getContent() {
        return this.content;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
    }
    public String getAttachDesc() {
        return this.attachDesc;
    }
    
    public void setAttachDesc(String attachDesc) {
        this.attachDesc = attachDesc;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Attachment) ) return false;
		 Attachment castOther = ( Attachment ) other; 
         
		 return ( (this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ) )
 && (this.getSequence()==castOther.getSequence());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         result = 37 * result + this.getSequence();
         
         
         return result;
   }   


}


