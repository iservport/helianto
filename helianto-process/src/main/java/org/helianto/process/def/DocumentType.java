package org.helianto.process.def;

/**
 * Represents <code>ExternalDocument</code> types.
 * @author Mauricio Fernandes de Castro
 *
 */
public enum DocumentType {
	
    /**
     * The document is category.
     */
	CATEGORY('C'),
    /**
     * The document is a file.
     */
    FILE('A'),
    /**
     * The document is a folder.
     */
    FOLDER('F');
    
    private char value;
    
    private DocumentType(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}