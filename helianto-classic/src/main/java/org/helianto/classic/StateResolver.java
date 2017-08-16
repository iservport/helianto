package org.helianto.classic;

/**
 * Summarize methods to resolve state.
 * 
 * @author mauriciofernandesdecastro
 */
public interface StateResolver {
	
    /**
     * True if the resolution indicates state ACTIVE.
     */
	public boolean isActive();
    
    /**
     * True if the resolution indicates state RUNNIG.
     */
    public boolean isRunning();

    /**
     * True if the resolution indicates state SUSPENDED.
     */
    public boolean isSuspended();

    /**
     * True if complete is 100%.
     */
    public boolean isComplete();

}
