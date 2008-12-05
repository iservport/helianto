/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

/**
 * The association type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum AssociationType {
    
    /**
     * Association to an operation describing a process.
     * 
     */
    PROCESS_OPERATION('F', Process.class, Operation.class, true),
    
    /**
     * Association to a control plan of a process.
     */
    PROCESS_CONTROLPLAN('G', Process.class, ControlPlan.class, true),

    /**
     * Association to a required sub-process within a process operation.
     */
    OPERATION_PROCESS('I', Operation.class, Process.class, true),

    /**
     * An operation may have specifications.
     * 
     * <p>The specification may be a descendant of an
     * operation since it is also descendant of a 
     * charcteristic.</p>
     */
    OPERATION_SPECIFICATION('J', Operation.class, Specification.class, true),
    
    /**
     * Association to a required characteristic within a process operation.
     */
    OPERATION_CHARACTERISTIC('K', Operation.class, Characteristic.class, true),
    
    /**
     * A characteristic should have a specification for 
     * each process development phase.
     * 
     */
    CHARACTERISTIC_SPECIFICATION('L', Characteristic.class, Specification.class, true),
    
    /**
     * A control plan may have a specification.
     * 
     */
    CONTROLPLAN_SPECIFICATION('M', ControlPlan.class, Specification.class, true),
    
    /**
     * General association.
     * 
     */
    GENERAL('X', ProcessDocument.class, ProcessDocument.class, true);
    
    
    private char value;
    private Class<ProcessDocument> parentType;
    private Class<ProcessDocument> childType;
    private boolean recommended;
    
    @SuppressWarnings("unchecked")
	private AssociationType(char value, Class parentType, Class childType, boolean recommended) {
        this.value = value;
        this.parentType = parentType;
        this.childType = childType;
        this.recommended = recommended;
    }
    
    public char getValue() {
        return value;
    }

    public boolean isRecommended() {
        return recommended;
    }
    
    /**
     * Resolve the <code>AssociationType</code> from given association
     * classes.
     * 
     * @param parentType
     * @param childType
     */
    @SuppressWarnings("unchecked")
	public static AssociationType resolveAssociationType(Class parentType, Class childType) {
    	for (AssociationType associationType: AssociationType.values()) {
    		if (associationType.parentType.equals(parentType) 
    				&& associationType.childType.equals(childType)) {
    			return associationType;
    		}
    	}
    	return null;
    }

}
