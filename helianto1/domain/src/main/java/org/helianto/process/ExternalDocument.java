package org.helianto.process;
// Generated 09/09/2006 21:08:17 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 			
 * <p>
 * Documents to be stored appart from the datastore.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class ExternalDocument extends org.helianto.process.Document implements java.io.Serializable {


    // Fields    

     private String docUrl;
     private char docType;
     private ExternalDocument parent;


    // Constructors

    /** default constructor */
    public ExternalDocument() {
    }

	/** minimal constructor */
    public ExternalDocument(Entity entity, String docCode) {
        super(entity, docCode);        
    }
    
    /** full constructor */
    public ExternalDocument(Entity entity, String docCode, String docName, List<Tree> children, String docUrl, char docType, ExternalDocument parent) {
        super(entity, docCode, docName, children);        
        this.docUrl = docUrl;
        this.docType = docType;
        this.parent = parent;
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

    public ExternalDocument getParent() {
        return this.parent;
    }
    
    public void setParent(ExternalDocument parent) {
        this.parent = parent;
    }
   








}
