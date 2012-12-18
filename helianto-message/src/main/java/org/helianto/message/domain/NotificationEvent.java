package org.helianto.message.domain;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.Uploadable;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.number.Sequenceable;
import org.helianto.document.base.AbstractEvent;

/**
 * Tracks notification events to the recipient (owner).
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="msg_notification",
	   uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "internalNumber"})}
)
public class NotificationEvent 

	extends AbstractEvent 
	
	implements Sequenceable, Uploadable 

{

	private static final long serialVersionUID = 1L;
	private long internalNumber;
	private byte[] content;
    private String encoding;
    private String multipartFileContentType;
	private String textContent;

    /**
     * Default constructor.
     */
    public NotificationEvent() {
    	super();
		setContent("");
		setEncoding("iso_88591");
    	setMultipartFileContentType("text/html");
	}
    
    /**
     * Key constructor.
     * 
     * @param entity
     * @param internalNumber
     */
    public NotificationEvent(Entity entity, long internalNumber) {
		this();
		setEntity(entity);
		setInternalNumber(internalNumber);
	}
    
    /**
     * Form constructor.
     * 
     * @param entity
     * @param owner
     */
    public NotificationEvent(Entity entity, Identity owner) {
		this(entity, 0);
		setOwner(owner);
	}
    
    @Transient
    public String getInternalNumberKey() {
    	return "NOTIF";
    }
    
    @Transient
    public int getStartNumber() {
    	return 1;
    }

    public long getInternalNumber() {
		return internalNumber;
	}
    public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
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
    
	@Column(length=32)
	public String getEncoding() {
		return this.encoding;
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
	
    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "text".
     */
    @Transient
    public boolean isText() {
    	if (getMultipartFileContentType().startsWith("text")) {
    		return true;
    	}
    	return false;
    }

    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "text/html".
     */
    @Transient
    public boolean isHtml() {
    	if (getMultipartFileContentType().startsWith("text/html")) {
    		return true;
    	}
    	return false;
    }

    /**
     * True if {@link #afterInternalNumberSet(long)} starts with "image".
     */
    @Transient
    public boolean isImage() {
    	if (getMultipartFileContentType().startsWith("image")) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Text content.
     */
    @Lob
    public String getTextContent() {
		return textContent;
	}
    public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NotificationEvent) ) return false;
		 NotificationEvent castOther = ( NotificationEvent ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
}
