package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A class to represent a setup.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Setup implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private int priority;

    /** persistent field */
    private long setupTime;

    /** persistent field */
    private long transportTime;

    /** nullable persistent field */
    private org.helianto.process.Resource resource;

    /** persistent field */
    private org.helianto.process.Operation operation;

    /** full constructor */
    public Setup(int priority, long setupTime, long transportTime, org.helianto.process.Resource resource, org.helianto.process.Operation operation) {
        this.priority = priority;
        this.setupTime = setupTime;
        this.transportTime = transportTime;
        this.resource = resource;
        this.operation = operation;
    }

    /** default constructor */
    public Setup() {
    }

    /** minimal constructor */
    public Setup(int priority, long setupTime, long transportTime, org.helianto.process.Operation operation) {
        this.priority = priority;
        this.setupTime = setupTime;
        this.transportTime = transportTime;
        this.operation = operation;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getSetupTime() {
        return this.setupTime;
    }

    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }

    public long getTransportTime() {
        return this.transportTime;
    }

    public void setTransportTime(long transportTime) {
        this.transportTime = transportTime;
    }

    public org.helianto.process.Resource getResource() {
        return this.resource;
    }

    public void setResource(org.helianto.process.Resource resource) {
        this.resource = resource;
    }

    public org.helianto.process.Operation getOperation() {
        return this.operation;
    }

    public void setOperation(org.helianto.process.Operation operation) {
        this.operation = operation;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
