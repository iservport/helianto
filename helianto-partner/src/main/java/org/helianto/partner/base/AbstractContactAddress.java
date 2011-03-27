package org.helianto.partner.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.helianto.core.Entity;

/**
 * Base class to instances having a contact address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
@javax.persistence.MappedSuperclass
public abstract class AbstractContactAddress implements java.io.Serializable {

    private int id;
    private int version;
    private Entity entity;
    private String uniqueString;
    private String comment;
    private char privacyLevel;
    
    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
    /**
     * Optimistic lock version control.
     */
    @Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Entity.
	 */
	@ManyToOne
	@JoinColumn(name = "entityId")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Normalized string having complete phone numbers, e-mail addresses, etc..
	 */
	@Column(length=40)
	public String getUniqueString() {
		return uniqueString;
	}
	public void setUniqueString(String uniqueString) {
		this.uniqueString = uniqueString;
	}
	
	/**
	 * Comment.
	 */
	@Column(length=128)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * Privacy level.
	 */
	public char getPrivacyLevel() {
		return privacyLevel;
	}
	public void setPrivacyLevel(char privacyLevel) {
		this.privacyLevel = privacyLevel;
	}

}
