package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8



/**
 * 			
 * <p>
 * Documents to be stored appart from the datastore.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class ExternalDocument extends Document implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String docUrl;
    private char docType;

     // Constructors

    /** default constructor */
    public ExternalDocument() {
    }

    // Property accessors
    public String getDocUrl() {
        return this.docUrl;
    }
    
    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
    public char getDocType() {
        return this.docType;
    }
    
    public void setDocType(char docType) {
        this.docType = docType;
    }




}


