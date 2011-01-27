package org.helianto.core.criteria;

/**
 * Build an EJB-QL select string, appropriate to be used
 * before the "from" keyword in a query.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SelectBuilder {

	private Class<?> clazz;
    private String alias;
    private StringBuilder builder;

    /**
     * Default constructor.
     */
    public SelectBuilder() {
        setAlias(alias);
    }
    
    /**
     * Class constructor.
     * 
     * @param clazz
     */
    public SelectBuilder(Class<?> clazz) {
    	this(clazz, uncapFirst(clazz.getSimpleName()));
    }
    
    /**
     * Class alias constructor.
     * 
     * @param clazz
     * @param alias
     */
    public SelectBuilder(Class<?> clazz, String alias) {
        setClazz(clazz);
        setAlias(alias);
    }
    
    /**
     * Change first character to lower case.
     * 
     * @param name
     */
    public static String uncapFirst(String name) {
    	return new StringBuilder(name)
		.replace(0, 1, String.valueOf(name.charAt(0)).toLowerCase())
		.toString();
    }
    
    /**
     * Class.
     */
    public Class<?> getClazz() {
		return clazz;
	}
    public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
    
    /**
     * Alias.
     */
    public String getAlias() {
		return alias;
	}
    public void setAlias(String alias) {
		this.alias = alias;
	}
    
    /**
     * Protected access to internal builder.
     */
    protected StringBuilder getInternalBuilder() {
    	return builder;
    }
    
    /**
     * Protected access to a new internal builder.
     */
    protected StringBuilder getNewInternalBuilder() {
    	builder = new StringBuilder();
    	return builder;
    }
    
    /**
     * Expose the resulting select string.
     */
    public String getAsString() {
    	return getInternalBuilder().toString();
    }
    
    /**
     * Select builder.
     * 
     * @param fieldNames
     * @return "select ${alias}.${fieldName0}, ${alias}.${fieldName1} "
     */
    public SelectBuilder createSelect(String... fieldNames) {
    	String separator = "select";
    	getNewInternalBuilder();
    	if (fieldNames.length>0) {
        	for (String fieldName: fieldNames) {
        		append(separator);
        		appendWithPrefix(fieldName, "");
        		separator = ",";
        	}
        	append("");
    	}
    	else {
    		append(separator);
    		append(getAlias());
    	}
		return this;
    }
    
    /**
     * Simple string appender.
     * 
     * @param content
     */
    public SelectBuilder append(String content) {
    	getInternalBuilder()
    	    .append(content)
            .append(" ");
        return this;
    }

    /**
     * String formatted appender.
     * 
     * @param content
     */
    public SelectBuilder appendWithPrefix(String content) {
        return appendWithPrefix(content, " ");
    }
    
    /**
     * String formatted appender.
     * 
     * @param content
     * @param separator
     */
    public SelectBuilder appendWithPrefix(String content, String separator) {
    	if (getInternalBuilder().length()>0) {
    		getInternalBuilder().append(getAlias())
            .append(".");
    	}
    	getInternalBuilder().append(content)
            .append(separator);
        return this;
    }
    
}
