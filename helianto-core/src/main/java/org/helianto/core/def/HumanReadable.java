package org.helianto.core.def;

import java.io.Serializable;

/**
 * A ser implementado por classes que podem ter texto lido por humanos.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface HumanReadable extends Serializable {

    /**
     * <<Transient>> True if is a text document.
     */
	public boolean isText();
	
    /**
     * <<Transient>> True if is a text/html document.
     */
	public boolean isHtml();
	
    /**
     * <<Transient>> Content as String.
     */
    public String getContentAsString();
    
    /**
     * Content as String setter.
     */
    public void setContentAsString(String contentAsString);
    
    /**
     * Codificação dos caracteres, quando texto.
     */
	public String getEncoding() ;
	
}
