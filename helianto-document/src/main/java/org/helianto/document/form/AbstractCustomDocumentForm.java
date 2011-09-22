package org.helianto.document.form;

import org.helianto.document.Serializer;


/**
 * Abstract custom document form.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractCustomDocumentForm extends AbstractDocumentForm implements CustomDocumentForm {
	
	private static final long serialVersionUID = 1L;
	private Serializer series;
	private String builderCode;
	private char contentType;
	
	public void reset() {
		super.reset();
		setContentType(' ');
	}
	
	public Serializer getSeries() {
		return series;
	}
	public void setSeries(Serializer series) {
		this.series = series;
	}
	
	public String getBuilderCode() {
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
