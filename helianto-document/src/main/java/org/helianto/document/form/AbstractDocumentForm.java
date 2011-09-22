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
	private String docCode;
	private String docName;
	private char priority;
	
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
