package org.helianto.core.internal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.helianto.core.def.HumanReadable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class to HumanReadable implementations.
 * 
 * @author mauriciofernandesdecastro
 */
@MappedSuperclass
public class AbstractHumanReadable implements HumanReadable {

	private static final long serialVersionUID = 1L;
	private byte[] content;
    private String encoding;
    private String multipartFileContentType;
    
    /**
     * Default constructor.
     */
    public AbstractHumanReadable() {
		super();
		setContent("");
		setEncoding("iso_88591");
		setMultipartFileContentType("text/plain");
	}
    
    /**
     * Content.
     */
    public byte[] getContent() {
		return content;
	}
    public void setContent(byte[] content) {
		this.content = content;
	}
    @JsonIgnore
    public void setContent(String content) {
    	this.content = content.getBytes();
    }
    
    /**
     * Helper method to get text content as String.
     */
    @Transient
    public String getContentAsString() {
    	if (getContent()!=null && isText()) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString);
	}
    
	@Column(length=32)
    public String getEncoding() {
		return encoding;
	}
    public void setEncoding(String encoding) {
    	this.encoding = encoding;
    }
    
	@Column(length=32)
	public String getMultipartFileContentType() {
		return multipartFileContentType;
	}
	public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
	}
	
    @Transient
    public boolean isText() {
    	if (getMultipartFileContentType()!=null && getMultipartFileContentType().startsWith("text")) {
    		return true;
    	}
    	return false;
    }

    @Transient
    public boolean isHtml() {
    	if (getMultipartFileContentType()!=null && getMultipartFileContentType().startsWith("text/html")) {
    		return true;
    	}
    	return false;
    }

}
