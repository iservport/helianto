package org.helianto.document;




/**
 * 			
 * <p>
 * Documents to be stored appart from the datastore.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */
public class ExternalDocument extends AbstractDocument implements java.io.Serializable {

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


