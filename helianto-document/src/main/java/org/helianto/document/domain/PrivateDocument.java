package org.helianto.document.domain;

import java.io.IOException;

import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.classic.enums.Uploadable;
import org.helianto.core.domain.Entity;
import org.helianto.document.DocumentContentType;
import org.helianto.document.internal.AbstractDocument;
import org.helianto.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A document visible only to the entity.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="doc_private",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "docCode"})}
)
public class PrivateDocument 
	extends AbstractDocument 
	implements Comparable<PrivateDocument>
	, Uploadable {

    private static final long serialVersionUID = 1L;
    
    @Lob
    private byte[] content;
    
    private char contentType;
    
	@Transient
    private transient MultipartFile file;

	/** 
	 * Default constructor 
	 */
    public PrivateDocument() {
    	super();
    	setContentType(' ');
    }

	/** 
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
    public PrivateDocument(Entity entity, String docCode) {
    	super(entity, docCode);
    	setContentType(' ');
    }

	/** 
	 * Form constructor.
	 * 
	 * @param user
	 */
    public PrivateDocument(User user) {
    	super(user.getEntity(), "");
    	setOwner(user.getIdentity());
    	setResolution('D');
    }

    public byte[] getContent() {
        return this.content;
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
//    @Transient
    public String getContentAsString() {
    	if (getContent()!=null && isText()) {
    		return new String(getContent());
    	}
    	return "";
    }
    public void setContentAsString(String contentAsString) {
		setContent(contentAsString);
	}
    
//    @Transient
    public int getContentSize() {
    	if (getContent()!=null) {
    		return getContent().length;
    	}
    	return 0;
    }
    
	/**
	 * Content type filter.
	 */
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	public void setContentTypeAsEnum(DocumentContentType contentType) {
		this.contentType = contentType.getValue();
	}
	
    /**
     * Allow subclasses to override how multipartFileContentType is determined.
     */
//    @Transient
    protected String internalMultipartFileContentType(String multipartFileContentType) {
    	if (getContentType()==DocumentContentType.TEXT.getValue()) {
    		return DocumentContentType.TEXT.getMultipartFileContentType();
    	}
    	if (getContentType()==DocumentContentType.HTML.getValue()) {
    		return DocumentContentType.HTML.getMultipartFileContentType();
    	}
    	if (getContentType()==DocumentContentType.CSS.getValue()) {
    		return DocumentContentType.CSS.getMultipartFileContentType();
    	}
    	return multipartFileContentType;
    }

	/**
	 * <<Transient>> Convenience property to hold uploaded data.
	 */
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * <<Transient>> Convenience method to read uploaded data.
	 */
//	@Transient
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
