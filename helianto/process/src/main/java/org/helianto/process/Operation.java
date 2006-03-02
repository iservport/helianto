package org.helianto.process;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * An operation.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class Operation extends Document implements Serializable {

    /** persistent field */
    private int operationType;

    /** persistent field */
    private long operationTime;

    /** persistent field */
    private List setups;

    /** full constructor */
    public Operation(String docCode, String docName, Entity entity, List children, int operationType, long operationTime, List setups) {
        super(docCode, docName, entity, children);
        this.operationType = operationType;
        this.operationTime = operationTime;
        this.setups = setups;
    }

    /** default constructor */
    public Operation() {
    }

    /** minimal constructor */
    public Operation(String docCode, List children, int operationType, long operationTime, List setups) {
      super(docCode, children);
        this.operationType = operationType;
        this.operationTime = operationTime;
        this.setups = setups;
    }

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

    public List getSetups() {
        return this.setups;
    }

    public void setSetups(List setups) {
        this.setups = setups;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
