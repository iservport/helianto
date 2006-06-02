package org.helianto.process;
// Generated 02/06/2006 13:28:21 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 				
 * <p>
 * A process characteristic.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process1.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 			
 */

public class Characteristic extends org.helianto.process.Document implements java.io.Serializable {


    // Fields    

     private int classification;


    // Constructors

    /** default constructor */
    public Characteristic() {
    }

	/** minimal constructor */
    public Characteristic(Entity entity, String docCode, int classification) {
        super(entity, docCode);        
        this.classification = classification;
    }
    
    /** full constructor */
    public Characteristic(Entity entity, String docCode, String docName, String docUrl, List<Tree> children, int classification) {
        super(entity, docCode, docName, docUrl, children);        
        this.classification = classification;
    }
    

   
    // Property accessors

    public int getClassification() {
        return this.classification;
    }
    
    public void setClassification(int classification) {
        this.classification = classification;
    }
   








}
