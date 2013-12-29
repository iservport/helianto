package org.helianto.partner.base;

import javax.persistence.Column;

import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.internal.AbstractTrunkEntity;

/**
 * Base class to instances having a contact address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
@javax.persistence.MappedSuperclass
public abstract class AbstractContactAddress
	extends AbstractTrunkEntity
{

	@Column(length=40)
    private String uniqueString = "";
    
	@Column(length=128)
    private String comment = "";
    
    private char privacyLevel = PrivacyLevel.PUBLIC.getValue();
    
	/**
	 * Normalized string having complete phone numbers, e-mail addresses, etc..
	 */
	public String getUniqueString() {
		return uniqueString;
	}
	public void setUniqueString(String uniqueString) {
		this.uniqueString = uniqueString;
	}
	
	/**
	 * Comment.
	 */
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
