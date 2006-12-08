package org.helianto.process;
// Generated 08/12/2006 09:46:04 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * A class to represent document versions.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class DocumentVersion  implements java.io.Serializable {

    // Fields    

     private int id;
     private Release release;
     private Document document;
     private int majorNumber;
     private int minorNumber;
     private int releaseAction;
     private char activityCode;
     private String changeSummary;

     // Constructors

    /** default constructor */
    public DocumentVersion() {
    }

	/** minimal constructor */
    public DocumentVersion(Release release, Document document, int majorNumber, int minorNumber, int releaseAction, char activityCode) {
        this.release = release;
        this.document = document;
        this.majorNumber = majorNumber;
        this.minorNumber = minorNumber;
        this.releaseAction = releaseAction;
        this.activityCode = activityCode;
    }
    /** full constructor */
    public DocumentVersion(Release release, Document document, int majorNumber, int minorNumber, int releaseAction, char activityCode, String changeSummary) {
       this.release = release;
       this.document = document;
       this.majorNumber = majorNumber;
       this.minorNumber = minorNumber;
       this.releaseAction = releaseAction;
       this.activityCode = activityCode;
       this.changeSummary = changeSummary;
    }
   
    // Property accessors
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Release getRelease() {
        return this.release;
    }
    
    public void setRelease(Release release) {
        this.release = release;
    }
    public Document getDocument() {
        return this.document;
    }
    
    public void setDocument(Document document) {
        this.document = document;
    }
    public int getMajorNumber() {
        return this.majorNumber;
    }
    
    public void setMajorNumber(int majorNumber) {
        this.majorNumber = majorNumber;
    }
    public int getMinorNumber() {
        return this.minorNumber;
    }
    
    public void setMinorNumber(int minorNumber) {
        this.minorNumber = minorNumber;
    }
    public int getReleaseAction() {
        return this.releaseAction;
    }
    
    public void setReleaseAction(int releaseAction) {
        this.releaseAction = releaseAction;
    }
    public char getActivityCode() {
        return this.activityCode;
    }
    
    public void setActivityCode(char activityCode) {
        this.activityCode = activityCode;
    }
    public String getChangeSummary() {
        return this.changeSummary;
    }
    
    public void setChangeSummary(String changeSummary) {
        this.changeSummary = changeSummary;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DocumentVersion) ) return false;
		 DocumentVersion castOther = ( DocumentVersion ) other; 
         
		 return ( (this.getRelease()==castOther.getRelease()) || ( this.getRelease()!=null && castOther.getRelease()!=null && this.getRelease().equals(castOther.getRelease()) ) )
 && ( (this.getDocument()==castOther.getDocument()) || ( this.getDocument()!=null && castOther.getDocument()!=null && this.getDocument().equals(castOther.getDocument()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getRelease() == null ? 0 : this.getRelease().hashCode() );
         result = 37 * result + ( getDocument() == null ? 0 : this.getDocument().hashCode() );
         
         
         
         
         
         return result;
   }   


}


