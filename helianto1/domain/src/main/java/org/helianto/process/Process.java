package org.helianto.process;
// Generated 03/03/2007 08:02:04 by Hibernate Tools 3.2.0.beta8


import java.util.List;
import java.util.Set;
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
    public Process(Entity entity, String docCode, String docName, Set<Tree> parentAssociations, List<Tree> childAssociations, long internalNumber) {
        super(entity, docCode, docName, parentAssociations, childAssociations);        
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


