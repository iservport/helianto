/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.creation;

/**
 * The association type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum AssociationType {
    
    /**
     * A characteristic should have a specification for 
     * each process development phase.
     * 
     * <p>The specification is the natural descendant of a
     * characteristic.</p>
     * 
     * <p>The specification should be also descendant of a 
     * control plan to provide for phase resolution.</p>
     * 
     */
    CHARACTERISTIC_SPECIFICATION(0),
    /**
     * A control plan may have a specification.
     * 
     * <p>The specification may be a descendant of a
     * characteristic since it is also descendant of a 
     * charcteristic.</p>
     */
    CONTROLPLAN_SPECIFICATION(1),
    /**
     * An operation may have characteristics.
     * 
     * <p>The specification is the natural descendant of an
     * operation that includes inspection.</p>
     */
    OPERATION_CHARACTERISTIC(2),
    /**
     * An operation may have specifications.
     * 
     * <p>The specification may be a descendant of an
     * operation since it is also descendant of a 
     * charcteristic.</p>
     */
    OPERATION_SPECIFICATION(3),
    /**
     * The association is part to characterisitic.
     * 
     * <p>If a characterisitic is a descendant of a part, it should be 
     * later associated with one or more processes.</p>
     */
    PART_CHARACTERISTIC(4),
    /**
     * The association is part to a component part.
     */
    PART_PART(5),
    /**
     * The association is part to process.
     */
    PART_PROCESS(6),
    /**
     * The association is part to specification.
     * 
     * <p>If a specification is a descendant of a part, it should be 
     * later associated with a characterisitic.</p>
     */
    PART_SPECIFICATION(7),
    /**
     * The association is process to control plan.
     */
    PROCESS_CONTROLPLAN(8),
    /**
     * The association is process to operation.
     * 
     * <p>The operation is the natural descendant of a
     * process.</p>
     */
    PROCESS_OPERATION(9),
    /**
     * The association is process to part.
     * 
     * <p>Such associations allow the creation of process
     * families.</p>
     */
    PROCESS_PART(10);
    
    private int value;
    
    private AssociationType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
