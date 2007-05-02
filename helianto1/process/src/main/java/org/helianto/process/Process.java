package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8


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

    // Property accessors
    public long getInternalNumber() {
        return this.internalNumber;
    }
    
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }




}


