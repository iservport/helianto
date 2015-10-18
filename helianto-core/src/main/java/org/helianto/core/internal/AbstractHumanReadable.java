package org.helianto.core.internal;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

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
	
	@Lob
	private byte[] content;
	
	@Column(length=32)
    private String encoding;
    
	@Column(length=32)
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
     * Constructor.
     * 
     * @param content
     * @param encoding
     * @param multipartFileContentType
     */
    public AbstractHumanReadable(byte[] content
    		, String encoding
    		, String multipartFileContentType) {
		super();
		this.content = content;
		this.encoding = encoding;
		this.multipartFileContentType = multipartFileContentType;
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
    public String getContentAsString() {
    	if (getContent()!=null && isText()) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString);
	}
    
    public String getEncoding() {
		return encoding;
	}
    public void setEncoding(String encoding) {
    	this.encoding = encoding;
    }
    
	public String getMultipartFileContentType() {
		return multipartFileContentType;
	}
	public void setMultipartFileContentType(String multipartFileContentType) {
		this.multipartFileContentType = multipartFileContentType;
	}
	
    public boolean isText() {
    	if (getMultipartFileContentType()!=null && getMultipartFileContentType().startsWith("text")) {
    		return true;
    	}
    	return false;
    }

    public boolean isHtml() {
    	if (getMultipartFileContentType()!=null && getMultipartFileContentType().startsWith("text/html")) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Merger.
     * 
     * @param command
     */
    protected void merge(AbstractHumanReadable command) {
    	setContent(command.getContent());
    	setEncoding(command.getEncoding());
    	setMultipartFileContentType(command.getMultipartFileContentType());
    }

}
