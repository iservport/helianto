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
	boolean isText();
	
    /**
     * <<Transient>> True if is a text/html document.
     */
	boolean isHtml();
	
    /**
     * <<Transient>> Content as String.
     */
    String getContentAsString();
    
    /**
     * Content as String setter.
     */
    void setContentAsString(String contentAsString);
    
    /**
     * Codificação dos caracteres, quando texto.
     */
	String getEncoding() ;
	
    /**
     * Content type, like text/plain, text/html, etc.
     */
	String getMultipartFileContentType();
	
}
