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
    CHARACTERISTIC_SPECIFICATION('A'),
    /**
     * A control plan may have a specification.
     * 
     * <p>The specification may be a descendant of a
     * characteristic since it is also descendant of a 
     * charcteristic.</p>
     */
    CONTROLPLAN_SPECIFICATION('B'),
    /**
     * An operation may have characteristics.
     * 
     * <p>The specification is the natural descendant of an
     * operation that includes inspection.</p>
     */
    OPERATION_CHARACTERISTIC('C'),
    /**
     * An operation may have associated parts.
     * 
     * <p>A part may be a descendant of an
     * operation.</p>
     */
    OPERATION_PART('D'),
    /**
     * An operation may have specifications.
     * 
     * <p>The specification may be a descendant of an
     * operation since it is also descendant of a 
     * charcteristic.</p>
     */
    OPERATION_SPECIFICATION('E'),
    /**
     * The association is part to characterisitic.
     * 
     * <p>If a characterisitic is a descendant of a part, it should be 
     * later associated with one or more processes.</p>
     */
    PART_CHARACTERISTIC('F'),
    /**
     * The association is part to a component part.
     */
    PART_PART('G'),
    /**
     * The association is part to process.
     */
    PART_PROCESS('H'),
    /**
     * The association is part to specification.
     * 
     * <p>If a specification is a descendant of a part, it should be 
     * later associated with a characterisitic.</p>
     */
    PART_SPECIFICATION('I'),
    /**
     * The association is process to control plan.
     */
    PROCESS_CONTROLPLAN('J'),
    /**
     * The association is process to operation.
     * 
     * <p>The operation is the natural descendant of a
     * process.</p>
     */
    PROCESS_OPERATION('K'),
    /**
     * The association is process to part.
     * 
     * <p>Such associations allow the creation of process
     * families.</p>
     */
    PROCESS_PART('L');
    
    private char value;
    
    private AssociationType(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
