package org.helianto.process;
// Generated 24/09/2006 12:54:25 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 			
 * <p>
 * A process.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Process extends org.helianto.process.Document implements java.io.Serializable {


    // Fields    

     private long internalNumber;


    // Constructors

    /** default constructor */
    public Process() {
    }

	/** minimal constructor */
    public Process(Entity entity, String docCode) {
        super(entity, docCode);        
    }
    
    /** full constructor */
    public Process(Entity entity, String docCode, String docName, Document parent, List<Tree> children, long internalNumber) {
        super(entity, docCode, docName, parent, children);        
        this.internalNumber = internalNumber;
    }
    

   
    // Property accessors

    public long getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }
   








}
