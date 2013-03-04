package org.helianto.core;

import java.util.List;

import org.helianto.core.domain.type.TrunkEntity;


/**
 * Interface to classes defining a script array.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Programmable extends TrunkEntity {
	
    /**
     * Key-value pair list of scripts.
     * 
     * <p>
     * Likely a persistent key-value pair list of script 
     * codes, separated by comma. Key codes may be used to load the actual domain specific scripts used 
     * to read and write parseable content.
     * </p>
     */
	String getScriptItems();
	
    /**
     * Key-value pair list of scripts as an array.
     * 
     * <p>
     * Likely to be a transient field, read from a {@link #getScriptItems()}
     * </p>
     */
    String[] getScriptItemsAsArray();
    
    /**
     * Script list, likely to be loaded at runtime.
     */
    List<String> getScriptList();

}
