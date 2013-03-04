package org.helianto.document.form;

import org.helianto.core.domain.Entity;
import org.helianto.document.domain.DocumentFolder;


/**
 * Abstract custom document form.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractCustomDocumentForm extends AbstractDocumentForm implements CustomDocumentForm {
	
	private static final long serialVersionUID = 1L;
	private DocumentFolder series;
	private String builderCode;
	private char contentType;
	
	public AbstractCustomDocumentForm() {
		super();
	}
	
	public AbstractCustomDocumentForm(Entity entity) {
		this();
		setEntity(entity);
	}
	
	public void reset() {
		super.reset();
		setContentType(' ');
	}
	
	public DocumentFolder getSeries() {
		return series;
	}
	public void setSeries(DocumentFolder series) {
		this.series = series;
	}
	
	public String getFolderCode() {
		return builderCode;
	}
	public void setBuilderCode(String builderCode) {
		this.builderCode = builderCode;
	}
	
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	
}
