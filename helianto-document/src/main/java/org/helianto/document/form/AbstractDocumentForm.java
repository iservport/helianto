package org.helianto.document.form;

import org.helianto.core.Entity;

/**
 * Abstract document form.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractDocumentForm implements DocumentForm {
	
	private static final long serialVersionUID = 1L;
	private Entity entity;
	private String searchString;
	private String docCode;
	private String docName;
	private char priority;
	
	/**
	 * Default constructor.
	 */
	public AbstractDocumentForm() {
		reset();
	}
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public AbstractDocumentForm(Entity entity) {
		this();
		setEntity(entity);
	}
	
	public void reset() {
		setDocName("");
		setPriority(' ');
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}
}