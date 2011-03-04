package org.helianto.core.criteria;

/**
 * Build an EJB-QL "select" and "from" strings, appropriate to be used
 * before the "where" keyword in a query.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SelectFromBuilder extends SelectBuilder {

    /**
     * Default constructor.
     */
    public SelectFromBuilder() {
        super();
    }
    
    /**
     * Class constructor.
     * 
     * @param clazz
     */
    public SelectFromBuilder(Class<?> clazz) {
    	super(clazz);
    }
    
    /**
     * Class alias constructor.
     * 
     * @param alias
     * @param clazz
     */
    public SelectFromBuilder(Class<?> clazz, String alias) {
        super(clazz, alias);
    }
    
    /**
     * Select from builder.
     * 
     * @param fieldNames
     */
    public SelectFromBuilder createSelectFrom(String... fieldNames) {
    	SelectFromBuilder builder = (SelectFromBuilder) super.createSelect(fieldNames);
    	builder.appendFrom();
    	return builder;
    }
    
    /**
     * From appender.
     */
    public SelectFromBuilder appendFrom() {
    	append("from")
    	    .append(getClazz().getSimpleName())
    	    .append(getAlias());
    	return this;
    }
    
    /**
     * Join appender.
     * 
     * @param joinType
     * @param propertyName
     * @param joinedClassAlias
     */
    public SelectFromBuilder appendJoin(String joinType, String propertyName, String joinedClassAlias) {
    	append(joinType)
    	    .append("join")
    	    .appendWithPrefix(propertyName)
    	    .append("as")
    	    .append(joinedClassAlias);
    	return this;
    }
    
    /**
     * Inner join appender.
     * 
     * @param propertyName
     * @param joinedClassAlias
     */
    public SelectFromBuilder appendInnerJoin(String propertyName, String joinedClassAlias) {
    	return appendJoin("inner", propertyName, joinedClassAlias);
    }
    
    /**
     * Parent association inner join appender.
     */
    public SelectFromBuilder appendParentInnerJoin() {
    	return appendInnerJoin("parentAssociations", "parentAssociations");
    }
    
    /**
     * Child association inner join appender.
     */
    public SelectFromBuilder appendChildInnerJoin() {
    	return appendInnerJoin("childAssociations", "childAssociations");
    }
    
}
