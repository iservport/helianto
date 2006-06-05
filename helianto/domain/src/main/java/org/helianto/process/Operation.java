package org.helianto.process;
// Generated Jun 5, 2006 4:10:13 PM by Hibernate Tools 3.1.0.beta4

import java.util.ArrayList;
import java.util.List;
import org.helianto.core.Entity;


/**
 * 				
 * <p>
 * An operation.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process1.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
 * 				
 * 			
 */

public class Operation extends org.helianto.process.Document implements java.io.Serializable {


    // Fields    

     private int operationType;
     private long operationTime;
     private List<Setup> setups = new ArrayList<Setup>(0);


    // Constructors

    /** default constructor */
    public Operation() {
    }

	/** minimal constructor */
    public Operation(Entity entity, String docCode, int operationType, long operationTime) {
        super(entity, docCode);        
        this.operationType = operationType;
        this.operationTime = operationTime;
    }
    
    /** full constructor */
    public Operation(Entity entity, String docCode, String docName, String docUrl, List<Tree> children, int operationType, long operationTime, List<Setup> setups) {
        super(entity, docCode, docName, docUrl, children);        
        this.operationType = operationType;
        this.operationTime = operationTime;
        this.setups = setups;
    }
    

   
    // Property accessors

    public int getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public long getOperationTime() {
        return this.operationTime;
    }
    
    public void setOperationTime(long operationTime) {
        this.operationTime = operationTime;
    }

    public List<Setup> getSetups() {
        return this.setups;
    }
    
    public void setSetups(List<Setup> setups) {
        this.setups = setups;
    }
   








}
