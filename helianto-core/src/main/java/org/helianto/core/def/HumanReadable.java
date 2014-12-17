package org.helianto.core.def;

import java.io.Serializable;

/**
 * Classes implementing this should have human readable content.
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
     * Encoding.
     */
	String getEncoding() ;
	
    /**
     * Content type, like text/plain, text/html, etc.
     */
	String getMultipartFileContentType();
	
}
