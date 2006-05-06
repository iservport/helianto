package org.helianto.process;
// Generated 05/05/2006 22:23:15 by Hibernate Tools 3.1.0.beta4

import org.helianto.core.Entity;


/**
 * 			
 * <p>
 * A class to represent document versions.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process0.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 		
 */

public class DocumentVersion  implements java.io.Serializable {


    // Fields    

     private long id;
     private Entity Release;
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
    public DocumentVersion(Entity Release, Document document, int majorNumber, int minorNumber, int releaseAction, char activityCode) {
        this.Release = Release;
        this.document = document;
        this.majorNumber = majorNumber;
        this.minorNumber = minorNumber;
        this.releaseAction = releaseAction;
        this.activityCode = activityCode;
    }
    
    /** full constructor */
    public DocumentVersion(Entity Release, Document document, int majorNumber, int minorNumber, int releaseAction, char activityCode, String changeSummary) {
        this.Release = Release;
        this.document = document;
        this.majorNumber = majorNumber;
        this.minorNumber = minorNumber;
        this.releaseAction = releaseAction;
        this.activityCode = activityCode;
        this.changeSummary = changeSummary;
    }
    

   
    // Property accessors

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Entity getRelease() {
        return this.Release;
    }
    
    public void setRelease(Entity Release) {
        this.Release = Release;
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
