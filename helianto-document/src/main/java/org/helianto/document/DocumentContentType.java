package org.helianto.document;

/**
 * Document type enum.
 * 
 * @author mauriciofernandesdecastro
 */
public enum DocumentContentType {
	
	TEXT('T', "text/plain"),
	HTML('H', "text/html"),
	CSS('S', "text/css"),
	FREEMARKER('F', "text/plain"),
	OTHER('O', "text/plain");
	
	private DocumentContentType(char value, String multipartFileContentType) {
		this.value = value;
		this.multipartFileContentType = multipartFileContentType;
	}
	
	private char value = 'O';
	private String multipartFileContentType = "text/plain";
	
	public char getValue() {
		return value;
	}

	public String getMultipartFileContentType() {
		return multipartFileContentType;
	}

}
