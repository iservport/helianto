package org.helianto.process;
// Generated 16/07/2006 09:41:44 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 					
 * <p>
 * A control plan.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 				
 */

public class ControlPlan extends org.helianto.process.Process implements java.io.Serializable {


    // Fields    

     private int phase;


    // Constructors

    /** default constructor */
    public ControlPlan() {
    }

	/** minimal constructor */
    public ControlPlan(Entity entity, String docCode, int phase) {
        super(entity, docCode);        
        this.phase = phase;
    }
    
    /** full constructor */
    public ControlPlan(Entity entity, String docCode, String docName, String docUrl, List<Tree> children, long internalNumber, int phase) {
        super(entity, docCode, docName, docUrl, children, internalNumber);        
        this.phase = phase;
    }
    

   
    // Property accessors

    public int getPhase() {
        return this.phase;
    }
    
    public void setPhase(int phase) {
        this.phase = phase;
    }
   








}
