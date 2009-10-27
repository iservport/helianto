package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * A base class to add details to documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class DocumentDetail  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
    private ProcessDocument document;
    private int sequence;
    private String detailDesc;

     // Constructors

    /** default constructor */
    public DocumentDetail() {
    }

	/** minimal constructor */
    public DocumentDetail(int sequence) {
        this.sequence = sequence;
    }
    /** full constructor */
    public DocumentDetail(ProcessDocument document, int sequence, String detailDesc) {
       this.document = document;
       this.sequence = sequence;
       this.detailDesc = detailDesc;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public ProcessDocument getDocument() {
        return this.document;
    }
    
    public void setDocument(ProcessDocument document) {
        this.document = document;
    }
    public int getSequence() {
        return this.sequence;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    public String getDetailDesc() {
        return this.detailDesc;
    }
    
    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DocumentDetail) ) return false;
		 DocumentDetail castOther = ( DocumentDetail ) other; 
         
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

