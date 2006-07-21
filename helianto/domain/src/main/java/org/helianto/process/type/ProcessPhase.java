/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.type;

/**
 * Process phases.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: ResourceType.java,v 1.1 2006/03/02 22:44:24 iserv Exp $
 */
public enum ProcessPhase {
    
    ALL(7),
    PRE_LAUNCH(2),
    PRE_LAUNCH_AND_PRODUCTION(6),
    PRODUCTION(4),
    PROTOTYPE(1),
    PROTOTYPE_AND_PRE_LAUNCH(3);
    
    private int value;
    
    private ProcessPhase(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}