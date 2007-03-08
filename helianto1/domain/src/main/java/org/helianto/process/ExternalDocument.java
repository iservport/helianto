package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8


import java.util.List;
import java.util.Set;
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

     // Constructors

    /** default constructor */
    public ExternalDocument() {
    }

	/** minimal constructor */
    public ExternalDocument(Entity entity, String docCode) {
        super(entity, docCode);        
    }
    /** full constructor */
    public ExternalDocument(Entity entity, String docCode, String docName, Set<Tree> parentAssociations, List<Tree> childAssociations, String docUrl, char docType) {
        super(entity, docCode, docName, parentAssociations, childAssociations);        
       this.docUrl = docUrl;
       this.docType = docType;
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


