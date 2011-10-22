package org.helianto.document.form;

import org.helianto.core.Entity;

/**
 * Abstract serializer form.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractSerializerForm implements SerializerForm {
	
	private static final long serialVersionUID = 1L;
	private Entity entity;
	private String searchString;
	private char contentType;
	private String builderCode;
	
	/**
	 * Default constructor.
	 */
	public AbstractSerializerForm() {
		reset();
	}
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public AbstractSerializerForm(Entity entity) {
		this();
		setEntity(entity);
	}
	
	public void reset() {
		setBuilderCode("");
		setContentType(' ');
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
	
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	
	public String getBuilderCode() {
		return builderCode;
	}
	public void setBuilderCode(String builderCode) {
		this.builderCode = builderCode;
	}
	
}
