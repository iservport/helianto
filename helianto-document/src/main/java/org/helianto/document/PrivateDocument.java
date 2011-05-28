package org.helianto.document;

import java.io.IOException;

import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.Entity;
import org.helianto.document.base.AbstractDocument;
import org.springframework.web.multipart.MultipartFile;

/**
 * A document visible only to the entity.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="doc_private",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
public class PrivateDocument extends AbstractDocument implements Comparable<PrivateDocument>, Uploadable {

    private static final long serialVersionUID = 1L;
    private byte[] content;
    // transient
    private transient MultipartFile file;

	/** 
	 * Default constructor 
	 */
    public PrivateDocument() {
    	super();
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
    public PrivateDocument(Entity entity, String docCode) {
    	super(entity, docCode);
    }



    @Lob
    public byte[] getContent() {
        return this.content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
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
    
    @Transient
    public int getContentSize() {
    	return this.content.length;
    }
    
	/**
	 * <<Transient>> Convenience property to hold uploaded data.
	 */
	@Transient
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * <<Transient>> Convenience method to read uploaded data.
	 */
	@Transient
	public void processFile() throws IOException {
		setContent(file.getBytes());
		setMultipartFileContentType(file.getContentType());
		setDocFile(file.getOriginalFilename());
	}
	
	/**
	 * Sort by docCode.
	 */
	public int compareTo(PrivateDocument next) {
		return getDocCode().compareTo(next.getDocCode());
	}

	@Override
    public boolean equals(Object other) {
		 if ( !(other instanceof PrivateDocument) ) return false;
		 return super.equals(other);
    }

}
