package org.helianto.process;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * A process.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class Process extends Document implements Serializable {

    /** nullable persistent field */
    private Long internalNumber;

    /** full constructor */
    public Process(String docCode, String docName, Entity entity, List children, Long internalNumber) {
        super(docCode, docName, entity, children);
        this.internalNumber = internalNumber;
    }

    /** default constructor */
    public Process() {
    }

    /** minimal constructor */
    public Process(String docCode, List children) {
      super(docCode, children);
    }

    public Long getInternalNumber() {
        return this.internalNumber;
    }

    public void setInternalNumber(Long internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
